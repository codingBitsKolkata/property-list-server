package com.orastays.property.propertylist.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.helper.MessageUtil;
import com.orastays.property.propertylist.helper.PropertyType;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.RoomModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.utils.RoomFilter;

@Component
public class FilterRoomsUtil {

	private static final Logger logger = LogManager.getLogger(FilterRoomsUtil.class);
	
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected PropertyListRoomUtil propertyListRoomUtil;

	public Map<Integer, RoomSelector> getRoomsByPropertyWithCheckinDateFilter(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("getRoomsByPropertyWithCheckinDateFilter -- START");
		}
		
		Map<Integer, RoomSelector> filteredRooms = new HashMap<>();
		BookingModel bookingModel = new BookingModel();
		bookingModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
		bookingModel.setCheckinDate(filterCiteriaModel.getCheckInDate());
		ResponseModel responseModel = restTemplate.postForObject(
				messageUtil.getBundle("booking.server.url") + "get-bookings", bookingModel, ResponseModel.class);
		Gson gson = new Gson();
		String jsonString = gson.toJson(responseModel.getResponseBody());
		gson = new Gson();
		Type listType = new TypeToken<List<BookingModel>>() {}.getType();
		List<BookingModel> bookingModels = gson.fromJson(jsonString, listType);

		Map<String, List<BookingVsRoomModel>> roomByOraName = new LinkedHashMap<>();
		if (!CollectionUtils.isEmpty(bookingModels)) {

			List<BookingVsRoomModel> bookingVsRoomModels = new ArrayList<>();
			for (BookingModel bookingModel2 : bookingModels) {
				if (Objects.nonNull(bookingModel2) && !CollectionUtils.isEmpty(bookingModel2.getBookingVsRooms())) {
					bookingVsRoomModels.addAll(bookingModel2.getBookingVsRooms());
				}
			}

			if (!CollectionUtils.isEmpty(bookingVsRoomModels)) {
				for (BookingVsRoomModel bookingVsRoomModel : bookingVsRoomModels) {
					if (roomByOraName.isEmpty()) { // First Time
						List<BookingVsRoomModel> mapBookingVsRoomModels = new ArrayList<>();
						mapBookingVsRoomModels.add(bookingVsRoomModel);
						roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
					} else {
						if (roomByOraName.containsKey(bookingVsRoomModel.getOraRoomName())) { // KEY Present
							List<BookingVsRoomModel> mapBookingVsRoomModels = roomByOraName
									.get(bookingVsRoomModel.getOraRoomName());
							mapBookingVsRoomModels.add(bookingVsRoomModel);
							roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
						} else { // KEY not present
							List<BookingVsRoomModel> mapBookingVsRoomModels = new ArrayList<>();
							mapBookingVsRoomModels.add(bookingVsRoomModel);
							roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
						}
					}
				}
			}

		}

		System.err.println("roomByOraName ==>> "+roomByOraName);
		// if hotel homestay guest house then go with private rooms

		if (filterCiteriaModel.getPropertyTypeId().equalsIgnoreCase(String.valueOf(PropertyType.HOTEL.ordinal()))
				|| filterCiteriaModel.getPropertyTypeId().equalsIgnoreCase(String.valueOf(PropertyType.HOMESTAY.ordinal()))
				|| filterCiteriaModel.getPropertyTypeId().equalsIgnoreCase(String.valueOf(PropertyType.GUEST_HOUSE.ordinal()))) {
			
			Map<Integer, RoomSelector> propertyPrivateAvailableRooms = propertyListRoomUtil.populatePrivateRoomsForProperty(roomByOraName, propertyEntity);

			// check if the key is present if not then checked for shared rooms

			for (RoomModel roomModel : filterCiteriaModel.getRoomModels()) {
				int guests = Integer.parseInt(roomModel.getNoOfGuest());
				int childs = roomModel.getNoOfChild() == null ? 0 : Integer.parseInt(roomModel.getNoOfChild());

				if (!propertyPrivateAvailableRooms.containsKey(guests)) {
					return filteredRooms;

				} else {
					List<RoomFilter> roomFilters = propertyPrivateAvailableRooms.get(guests).getAvailableRooms();
					boolean flag = false;
					for (RoomFilter roomFilter : roomFilters) {
						// check if condition is matching
						if (Integer.parseInt(roomFilter.getRoomEntity().getNoOfChild()) >= childs
								&& !roomFilter.isConsidered()) { // criteria matching
							roomFilter.setConsidered(true);
							flag = true;
							propertyPrivateAvailableRooms.get(guests).setSelectedRoom(roomFilter);
							break;
						}
					}
					System.err.println(roomFilters);
					if (!flag) {
						filteredRooms = new HashMap<Integer, RoomSelector>();
						return filteredRooms; // room not allocated
					} else {
						filteredRooms.put(guests, propertyPrivateAvailableRooms.get(guests));
					}
				}
			}
			
			System.out.println("propertyPrivateAvailableRooms ==>> "+filteredRooms);
			
			return filteredRooms;
		} else { // search for shared rooms
			//count total guest + child in case of shared. allocated seperate beds for them
			int totalGuest = 0;
			int totalChild = 0;
			
			for(RoomModel roomModel : filterCiteriaModel.getRoomModels()) {
				totalGuest += roomModel.getNoOfGuest() == null ? 0 : Integer.parseInt(roomModel.getNoOfGuest());
				totalChild += roomModel.getNoOfChild() == null ? 0 : Integer.parseInt(roomModel.getNoOfChild());
				
			}
			int bedRequired = totalGuest + totalChild;
			Map<Integer, RoomSelector> propertySharedAvailableRooms = propertyListRoomUtil.populateSharedRoomsForProperty(roomByOraName, propertyEntity, bedRequired);
			List<RoomFilter> filteredSharedRooms = propertySharedAvailableRooms.get(bedRequired).getAvailableRooms();
			if(Objects.isNull(filteredSharedRooms) && filteredSharedRooms.isEmpty()) {
				return filteredRooms;
			} else { //check if criteria is matching
				int remaining = bedRequired;
				boolean flag = false;
				for(RoomFilter roomFilter : filteredSharedRooms) {
					int availableBeds = roomFilter.getAvailableBeds();
					if(availableBeds >= remaining) {
						roomFilter.setConsidered(true);
						roomFilter.setSelectedNumberOfBeds(remaining);
						flag = true;
						break;
					} else {
						roomFilter.setConsidered(true);
						roomFilter.setSelectedNumberOfBeds(availableBeds);
						remaining -= availableBeds;
						if(remaining == 0) {
							flag = true;
							break;
						}
					}
				}
				if(!flag) {
					return filteredRooms; // beds not allocated
				} else {
					filteredRooms.put(bedRequired, propertySharedAvailableRooms.get(bedRequired));
				}
			}
			
			System.out.println("propertySharedAvailableRooms ==>> "+filteredRooms);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("getRoomsByPropertyWithCheckinDateFilter -- END");
		}

		return filteredRooms;
	}
}
