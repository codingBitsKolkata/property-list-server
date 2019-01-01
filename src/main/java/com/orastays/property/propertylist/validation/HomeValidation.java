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

@Component
public class HomeValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(HomeValidation.class);
	
	public void validatePropertyType(String propertyTypeId) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validatePropertyType -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		// Validate Property Type
		if (StringUtils.isBlank(propertyTypeId)) {
			exceptions.put(messageUtil.getBundle("property.type.id.null.code"), new Exception(messageUtil.getBundle("property.type.id.null.message")));
		} else {
			if (!Util.isNumeric(propertyTypeId)) {
				exceptions.put(messageUtil.getBundle("property.type.id.invalid.code"), new Exception(messageUtil.getBundle("property.type.id.invalid.message")));
			} else {
				PropertyTypeEntity propertyTypeEntity = propertyTypeDAO.find(Long.parseLong(propertyTypeId));
				if (Objects.isNull(propertyTypeEntity) && propertyTypeEntity.getStatus() != Status.ACTIVE.ordinal()) {
					exceptions.put(messageUtil.getBundle("property.type.id.invalid.code"), new Exception(messageUtil.getBundle("property.type.id.invalid.message")));
				}
			}
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validatePropertyType -- End");
		}
	}
}
