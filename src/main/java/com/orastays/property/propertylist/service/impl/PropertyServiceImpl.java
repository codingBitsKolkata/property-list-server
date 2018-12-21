package com.orastays.property.propertylist.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.property.propertylist.service.PropertyListService;

@Service
@Transactional
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyListService {

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);
}
