package com.orastays.property.propertylist.service;

import com.orastays.property.propertylist.entity.GstSlabEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.booking.GstSlabModel;

public interface GstSlabService {

	GstSlabEntity getActiveGstEntity(Double amount) throws FormExceptions;
	GstSlabModel getActiveGstModel(Double amount) throws FormExceptions;
}