package com.orastays.property.propertylist.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.property.propertylist.entity.PropertyTypeEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.UserModel;

@Component
public class PropertyListValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(PropertyListValidation.class);
	
	public UserModel validateFetchProperties(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchProperties -- Start");
		}

		UserModel userModel = getUserDetails(filterCiteriaModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		// Validate Property Type
		if (StringUtils.isBlank(filterCiteriaModel.getPropertyTypeId())) {
			exceptions.put(messageUtil.getBundle("property.type.id.null.code"), new Exception(messageUtil.getBundle("property.type.id.null.message")));
		} else {
			if (!Util.isNumeric(filterCiteriaModel.getPropertyTypeId())) {
				exceptions.put(messageUtil.getBundle("property.type.id.invalid.code"), new Exception(messageUtil.getBundle("property.type.id.invalid.message")));
			} else {
				PropertyTypeEntity propertyTypeEntity = propertyTypeDAO .find(Long.parseLong(filterCiteriaModel.getPropertyTypeId()));
				if (Objects.isNull(propertyTypeEntity) && propertyTypeEntity.getStatus() != Status.ACTIVE.ordinal()) {
					exceptions.put(messageUtil.getBundle("property.type.id.invalid.code"), new Exception(messageUtil.getBundle("property.type.id.invalid.message")));
				}
			}
		}
		
		// Validate Latitude
		if (StringUtils.isBlank(filterCiteriaModel.getLatitude())) {
			exceptions.put(messageUtil.getBundle("latitude.null.code"), new Exception(messageUtil.getBundle("latitude.null.message")));
		} else {
			if (!Util.checkLatitude(filterCiteriaModel.getLatitude())) {
				exceptions.put(messageUtil.getBundle("latitude.invalid.code"), new Exception(messageUtil.getBundle("latitude.invalid.message")));
			}
		}

		// Validate Longitude
		if (StringUtils.isBlank(filterCiteriaModel.getLongitude())) {
			exceptions.put(messageUtil.getBundle("longitude.null.code"), new Exception(messageUtil.getBundle("longitude.null.message")));
		} else {
			if (!Util.checkLongitude(filterCiteriaModel.getLongitude())) {
				exceptions.put(messageUtil.getBundle("longitude.invalid.code"), new Exception(messageUtil.getBundle("longitude.invalid.message")));
			}
		}
		
		// Validate CheckIn Time
		if (StringUtils.isBlank(filterCiteriaModel.getCheckInDate())) {
			exceptions.put(messageUtil.getBundle("checkin.date.null.code"), new Exception(messageUtil.getBundle("checkin.date.null.message")));
		} else {
			if (!Util.checkOnlyDate(filterCiteriaModel.getCheckInDate())) {
				exceptions.put(messageUtil.getBundle("checkin.date.invalid.code"), new Exception(messageUtil.getBundle("checkin.date.invalid.message")));
			}
		}

		// Validate CheckOut Time
		if (StringUtils.isBlank(filterCiteriaModel.getCheckOutDate())) {
			exceptions.put(messageUtil.getBundle("checkout.date.null.code"), new Exception(messageUtil.getBundle("checkout.date.null.message")));
		} else {
			if (!Util.checkOnlyDate(filterCiteriaModel.getCheckOutDate())) {
				exceptions.put(messageUtil.getBundle("checkout.date.invalid.code"), new Exception(messageUtil.getBundle("checkout.date.invalid.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			if(Util.getDayDiff(filterCiteriaModel.getCheckInDate(), filterCiteriaModel.getCheckOutDate()) <= 0) {
				exceptions.put(messageUtil.getBundle("checkout.date.lesser.code"), new Exception(messageUtil.getBundle("checkout.date.lesser.message")));
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchProperties -- End");
		}
		
		return userModel;
	}
}
