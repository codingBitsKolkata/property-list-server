package com.orastays.property.propertylist.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.helper.Accommodation;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.utils.RoomFilter;

@Component
public class PropertyListRoomUtil {

	public static void populateAvailableRoomMap(Map<String, List<BookingVsRoomModel>> bookedRooms,
			Map<Integer, List<RoomFilter>> roomMap, List<RoomEntity> roomEntities) {
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
	}

	public Map<Integer, List<RoomFilter>> populatePrivateRoomsForProperty(
			Map<String, List<BookingVsRoomModel>> bookedRooms, PropertyEntity propertyEntity) {
		List<RoomEntity> propertyRooms = propertyEntity.getRoomEntities();
		Map<Integer, List<RoomFilter>> roomMap = new HashMap<>();
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
						RoomFilter roomFilter = new RoomFilter();
						roomFilter.setConsidered(false);
						roomFilter.setRoomEntity(room);
						if (!roomMap.containsKey(numberOfGuest + numberOfCot)) {
							List<RoomFilter> roomFilters = new LinkedList<>();
							roomMap.put(numberOfGuest + numberOfCot, roomFilters);
						}
						roomMap.get(numberOfGuest + numberOfCot).add(roomFilter);
					}
				}
			});
		}
		for(Map.Entry<Integer, List<RoomFilter>> map : roomMap.entrySet()) {
			Collections.sort(map.getValue(), new PrivateRoomComparator());
		}
		return roomMap;
	}

	public Map<Integer, List<RoomFilter>> populateSharedRoomsForProperty(
			Map<String, List<BookingVsRoomModel>> bookedRooms, PropertyEntity propertyEntity) {
		List<RoomEntity> propertyRooms = propertyEntity.getRoomEntities();
		Map<Integer, List<RoomFilter>> roomMap = new HashMap<>();
		if (!Objects.isNull(propertyRooms)) {
			propertyRooms.parallelStream().forEach(room -> {
				if (room.getAccomodationName().equalsIgnoreCase(Accommodation.SHARED.name())) {
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
		}
		return roomMap;
	}
}
