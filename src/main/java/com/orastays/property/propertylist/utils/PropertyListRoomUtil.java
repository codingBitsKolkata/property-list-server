package com.orastays.property.propertylist.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.helper.Accommodation;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.utils.RoomFilter;

@Component
public class PropertyListRoomUtil {

	private static final Logger logger = LogManager.getLogger(FilterRoomsUtil.class);

	public static void populateAvailableRoomMap(Map<String, List<BookingVsRoomModel>> bookedRooms,
			Map<Integer, List<RoomFilter>> roomMap, List<RoomEntity> roomEntities) {

		if (logger.isInfoEnabled()) {
			logger.info("populateAvailableRoomMap -- START");
		}

		roomEntities.parallelStream().forEach(room -> {
			// check room type
			if (room.getAccomodationName().equalsIgnoreCase(Accommodation.PRIVATE.name())) {
				// room is private. check whether booking exists or not
				if (bookedRooms.containsKey(room.getOraRoomName())) {
					return;
				} else {
					// check if key is present. key will be number of beds + number of cots
					int numberOfBed = room.getNumOfBed() == null ? 0 : Integer.parseInt(room.getNumOfBed());
					int numberOfCot = room.getNumOfBed() == null ? 0 : Integer.parseInt(room.getNumOfCot());
					RoomFilter roomFilter = new RoomFilter();
					roomFilter.setConsidered(false);
					roomFilter.setMatchingSearchCriteria(true);
					roomFilter.setRoomEntity(room);
					if (!roomMap.containsKey(numberOfBed + numberOfCot)) {
						List<RoomFilter> roomFilters = new LinkedList<>();
						roomMap.put(numberOfBed + numberOfCot, roomFilters);
					}
					roomMap.get(numberOfBed + numberOfCot).add(roomFilter);
				}
			} else { // shared room
				if (bookedRooms.containsKey(room.getOraRoomName())) {
					// check whether any bed is available
					int bookedBeds = 0;
					for (BookingVsRoomModel bookingVsRoomModel : bookedRooms.get(room.getOraRoomName())) {
						bookedBeds += Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed())
								+ Integer.parseInt(bookingVsRoomModel.getNumOfSharedCot());
					}
					int numberOfBed = room.getNumOfBed() == null ? 0 : Integer.parseInt(room.getNumOfBed());
					int numberOfCot = room.getNumOfBed() == null ? 0 : Integer.parseInt(room.getNumOfCot());

					if (numberOfBed + numberOfCot <= bookedBeds) {
						return;
					} else {
						RoomFilter roomFilter = new RoomFilter();
						roomFilter.setConsidered(false);
						roomFilter.setMatchingSearchCriteria(true);
						roomFilter.setAvailableBeds(numberOfCot + numberOfBed - bookedBeds);
						roomFilter.setRoomEntity(room);
						if (!roomMap.containsKey(bookedBeds)) {
							List<RoomFilter> roomFilters = new LinkedList<>();
							roomMap.put(bookedBeds, roomFilters);
						}
						roomMap.get(bookedBeds).add(roomFilter);
					}
				}
			}
		});

		if (logger.isInfoEnabled()) {
			logger.info("populateAvailableRoomMap -- END");
		}
	}

	public Map<Integer, RoomSelector> populatePrivateRoomsForProperty(Map<String, List<BookingVsRoomModel>> bookedRooms,
			PropertyEntity propertyEntity) {

		if (logger.isInfoEnabled()) {
			logger.info("populatePrivateRoomsForProperty -- START");
		}

		List<RoomEntity> propertyRooms = propertyEntity.getRoomEntities();
		Map<Integer, RoomSelector> roomMap = new HashMap<>();
		if (!Objects.isNull(propertyRooms)) {
			propertyRooms.stream().forEach(room -> {
				if (room.getAccomodationName().equalsIgnoreCase(Accommodation.PRIVATE.name())) {
					// room is private. check whether booking exists or not
					if (bookedRooms.containsKey(room.getOraRoomName())) {
						return;
					} else {
						// check if key is present. key will be number of beds + number of cots
						int numberOfGuest = room.getNoOfGuest() == null ? 0 : Integer.parseInt(room.getNoOfGuest());
						int numberOfCot = room.getNumOfCot() == null ? 0 : Integer.parseInt(room.getNumOfCot());
						int noOfChild = room.getNoOfChild() == null ? 0 : Integer.parseInt(room.getNoOfChild());
						room.setNoOfChild(String.valueOf(noOfChild));
						RoomFilter roomFilter = new RoomFilter();
						roomFilter.setConsidered(false);
						roomFilter.setRoomEntity(room);
						if (!roomMap.containsKey(numberOfGuest)) {
							RoomSelector roomSelector = new RoomSelector();

							List<RoomFilter> roomFilters = new LinkedList<>();
							roomSelector.setAvailableRooms(roomFilters);
							roomMap.put(numberOfGuest, roomSelector);
						}
						if (!roomMap.containsKey(numberOfGuest + numberOfCot)) {
							RoomSelector roomSelector = new RoomSelector();

							List<RoomFilter> roomFilters = new LinkedList<>();
							roomSelector.setAvailableRooms(roomFilters);
							roomMap.put(numberOfGuest + numberOfCot, roomSelector);
						}
						roomMap.get(numberOfGuest).getAvailableRooms().add(roomFilter);
						if (numberOfCot > 0)
							roomMap.get(numberOfGuest + numberOfCot).getAvailableRooms().add(roomFilter);
					}
				}
			});
		}

		for (Map.Entry<Integer, RoomSelector> map : roomMap.entrySet()) {
			Collections.sort(map.getValue().getAvailableRooms(), new PrivateRoomComparator());
		}

		if (logger.isInfoEnabled()) {
			logger.info("populatePrivateRoomsForProperty -- END");
		}

		return roomMap;
	}

	public Map<Integer, RoomSelector> populateSharedRoomsForProperty(
			Map<String, List<BookingVsRoomModel>> bookedRooms, PropertyEntity propertyEntity, int bedRequired) {

		if (logger.isInfoEnabled()) {
			logger.info("populateSharedRoomsForProperty -- START");
		}

		List<RoomEntity> propertyRooms = propertyEntity.getRoomEntities();
		Map<Integer, RoomSelector> roomMap = new HashMap<>();
		List<RoomFilter> filteredRooms = new ArrayList<>();
		if (!Objects.isNull(propertyRooms)) {
			propertyRooms.stream().forEach(room -> {
				if (room.getAccomodationName().equalsIgnoreCase(Accommodation.SHARED.name())) {
					int numberOfBed = room.getNumOfBed() == null ? 0 : Integer.parseInt(room.getNumOfBed());
					int numberOfCot = room.getNumOfCot() == null ? 0 : Integer.parseInt(room.getNumOfCot());
					if (bookedRooms.containsKey(room.getOraRoomName())) {
						// check whether any bed is available
						int bookedBeds = 0;
						for (BookingVsRoomModel bookingVsRoomModel : bookedRooms.get(room.getOraRoomName())) {
							bookedBeds += Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed())
									+ Integer.parseInt(bookingVsRoomModel.getNumOfSharedCot());
						}

						if (numberOfBed + numberOfCot <= bookedBeds) {
							return;
						} else {
							RoomFilter roomFilter = new RoomFilter();
							roomFilter.setConsidered(false);
							roomFilter.setMatchingSearchCriteria(true);
							roomFilter.setAvailableBeds(numberOfCot + numberOfBed - bookedBeds);
							roomFilter.setRoomEntity(room);
							filteredRooms.add(roomFilter);
						}
					} else {
						RoomFilter roomFilter = new RoomFilter();
						roomFilter.setConsidered(false);
						roomFilter.setMatchingSearchCriteria(true);
						roomFilter.setAvailableBeds(numberOfCot + numberOfBed);
						roomFilter.setRoomEntity(room);
						filteredRooms.add(roomFilter);
					}
				}
			});
		}
		Collections.sort(filteredRooms, new SharedRoomComparator());
		RoomSelector roomSelector = new RoomSelector();
		roomSelector.setAvailableRooms(filteredRooms);
		roomMap.put(bedRequired, roomSelector);
		if (logger.isInfoEnabled()) {
			logger.info("populateSharedRoomsForProperty -- END");
		}

		return roomMap;
	}
}
