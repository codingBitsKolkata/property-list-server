package com.orastays.property.propertylist.service;

import java.util.List;
import java.util.Map;

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.utils.RoomSelector;

public interface PropertyListService {

	List<PropertyListViewModel> fetchProperties(FilterCiteriaModel filterCiteriaModel) throws FormExceptions;

	BookingVsRoomModel roomDetailsByOraRoomName(String oraRoomName);

	PropertyModel fetchPropertyById(String propertyId);

	Object budgets();

	Object ratings();

	//PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel);

	PropertyModel fetchPropertyDetails(FilterCiteriaModel filterCiteriaModel) throws FormExceptions;

	PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel,
			Map<Integer, RoomSelector> filteredRooms);

}