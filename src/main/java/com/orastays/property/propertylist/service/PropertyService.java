package com.orastays.property.propertylist.service;

import java.util.List;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.AccommodationModel;
import com.orastays.property.propertylist.model.CommonModel;
import com.orastays.property.propertylist.model.PropertyTypeModel;
import com.orastays.property.propertylist.model.StayTypeModel;

public interface PropertyService {

	List<PropertyTypeModel> fetchPropertyTypes(CommonModel commonModel) throws FormExceptions;
	List<StayTypeModel> fetchStayTypeList(String languageId);
	List<AccommodationModel> fetchAllAccommodationTypeByLanguage(String languageId);
}