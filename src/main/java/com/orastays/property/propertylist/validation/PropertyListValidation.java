package com.orastays.property.propertylist.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PropertyListValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(PropertyListValidation.class);
}
