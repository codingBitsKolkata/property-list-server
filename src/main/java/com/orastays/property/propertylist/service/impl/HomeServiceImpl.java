package com.orastays.property.propertylist.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.property.propertylist.service.HomeService;

@Service
@Transactional
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

	private static final Logger logger = LogManager.getLogger(HomeServiceImpl.class);
}
