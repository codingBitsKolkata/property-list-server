package com.orastays.property.propertylist.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.service.PropertyListService;

@Service
@Transactional
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyListService {

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);

	@Override
	public List<PropertyListViewModel> fetchProperties(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- START");
		}
		
		List<PropertyListViewModel> propertyListViewModels = null;
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- END");
		}
		
		return propertyListViewModels;
	}
}
