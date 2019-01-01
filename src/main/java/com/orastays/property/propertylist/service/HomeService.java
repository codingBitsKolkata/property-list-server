package com.orastays.property.propertylist.service;

import java.util.List;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.PropertyListViewModel;

public interface HomeService {

	List<PropertyListViewModel> fetchPropertyByType(String propertyTypeId) throws FormExceptions;
}