package com.orastays.property.propertylist.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.UserModel;
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
		
		UserModel userModel = propertyListValidation.validateFetchProperties(filterCiteriaModel);
		List<PropertyListViewModel> propertyListViewModels = null;
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".PropertyEntity", outerMap1);
			
			List<PropertyEntity> propertyEntities = propertyDAO.fetchListBySubCiteria(alliasMap);
			
			if(!CollectionUtils.isEmpty(propertyEntities)) {
				
			}
			
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchUserByMobileNumber -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- END");
		}
		
		return propertyListViewModels;
	}
}
