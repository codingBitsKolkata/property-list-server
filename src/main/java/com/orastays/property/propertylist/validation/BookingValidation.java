package com.orastays.property.propertylist.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.user.UserModel;

@Component
public class BookingValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(BookingValidation.class);
	
	public List<Object> validatePropertyBooking(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validatePropertyBooking -- Start");
		}

		List<Object> obj = new ArrayList<>();
		UserModel userModel = getUserDetails(filterCiteriaModel.getUserToken());
		PropertyEntity propertyEntity = propertyListValidation.validateFetchPropertyDetails(filterCiteriaModel);
		obj.set(0, userModel);
		obj.set(1, propertyEntity);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validatePropertyBooking -- End");
		}
		
		return obj;
	}
}
