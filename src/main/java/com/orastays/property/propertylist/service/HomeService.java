package com.orastays.property.propertylist.service;

import java.util.List;

import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyTypeModel;

public interface HomeService {

	List<PropertyListViewModel> getProperty(PropertyTypeModel propType);

}
