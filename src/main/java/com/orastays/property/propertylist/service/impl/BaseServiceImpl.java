package com.orastays.property.propertylist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.property.propertylist.converter.AccommodationConverter;
import com.orastays.property.propertylist.converter.PropertyTypeConverter;
import com.orastays.property.propertylist.converter.StayTypeConverter;
import com.orastays.property.propertylist.dao.AccommodationDAO;
import com.orastays.property.propertylist.dao.PropertyTypeDAO;
import com.orastays.property.propertylist.dao.StayTypeDAO;
import com.orastays.property.propertylist.validation.PropertyValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected PropertyValidation propertyValidation;
	
	@Autowired
	protected PropertyTypeDAO propertyTypeDAO;
	
	@Autowired
	protected PropertyTypeConverter propertyTypeConverter;
	
	@Autowired
	protected StayTypeConverter stayTypeConverter;
	
	@Autowired
	protected StayTypeDAO stayTypeDAO;
	
	@Autowired
	protected AccommodationConverter accommodationConverter;
	
	@Autowired
	protected AccommodationDAO accommodationDAO;
}
