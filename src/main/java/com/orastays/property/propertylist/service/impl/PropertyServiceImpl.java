package com.orastays.property.propertylist.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.PropertyAddConstant;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.model.AccommodationModel;
import com.orastays.property.propertylist.model.CommonModel;
import com.orastays.property.propertylist.model.PropertyTypeModel;
import com.orastays.property.propertylist.model.StayTypeModel;
import com.orastays.property.propertylist.service.PropertyService;

@Service
@Transactional
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyService {

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);
	
	@Override
	public List<PropertyTypeModel> fetchPropertyTypes(CommonModel commonModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyTypes -- START");
		}
		
		propertyValidation.validateFetchPropertyType(commonModel);
		List<PropertyTypeModel> propertyTypeModels = null;
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put(PropertyAddConstant.STATUS, String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put(PropertyAddConstant.LANGUAGEID, commonModel.getLanguageId());
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".PropertyTypeEntity", outerMap1);
	
			propertyTypeModels = propertyTypeConverter.entityListToModelList(propertyTypeDAO.fetchListBySubCiteria(alliasMap));

		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyTypes -- END");
		}
		
		return propertyTypeModels;
	}
	
	@Override
	public List<StayTypeModel> fetchStayTypeList(String languageId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchAllStayTypeByLanguage -- START");
		}
		
		List<StayTypeModel> stayTypeModels = null;
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<String, String>();
			innerMap1.put("status", "1");
			innerMap1.put("languageId", languageId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<String, Map<String, String>>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
			alliasMap.put(entitymanagerPackagesToScan+".StayTypeEntity", outerMap1);
	
			stayTypeModels = stayTypeConverter.entityListToModelList(stayTypeDAO.fetchListBySubCiteria(alliasMap));

		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchAllPropertyTypeByLanguage -- END");
		}
		
		return stayTypeModels;
	}
	
	@Override
	public List<AccommodationModel> fetchAllAccommodationTypeByLanguage(String languageId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchAllPropertyTypeByLanguage -- START");
		}
		
		List<AccommodationModel> accommodationModels = null;
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<String, String>();
			innerMap1.put("status", "1");
			innerMap1.put("languageId", languageId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<String, Map<String, String>>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
			alliasMap.put(entitymanagerPackagesToScan+".PropertyTypeEntity", outerMap1);
	
			accommodationModels = accommodationConverter.entityListToModelList(accommodationDAO.fetchListBySubCiteria(alliasMap));

		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchAllPropertyTypeByLanguage -- END");
		}
		
		return accommodationModels;
	}

}
