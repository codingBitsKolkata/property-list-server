package com.orastays.property.propertylist.service;

import com.orastays.property.propertylist.entity.ConvenienceEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.ConvenienceModel;

public interface ConvenienceService {

	ConvenienceEntity getActiveConvenienceEntity();
	ConvenienceModel getActiveConvenienceModel() throws FormExceptions;
}