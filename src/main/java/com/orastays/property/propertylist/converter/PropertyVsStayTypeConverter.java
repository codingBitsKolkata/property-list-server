package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsStayTypeEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsStayTypeModel;

@Component
public class PropertyVsStayTypeConverter extends CommonConverter
		implements BaseConverter<PropertyVsStayTypeEntity, PropertyVsStayTypeModel> {

	private static final long serialVersionUID = 5305209904741530647L;
	
	private static final Logger logger = LogManager.getLogger(PropertyVsStayTypeConverter.class);

	@Override
	public PropertyVsStayTypeEntity modelToEntity(PropertyVsStayTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsStayTypeModel entityToModel(PropertyVsStayTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsStayTypeModel propertyVsStayTypeModel = new PropertyVsStayTypeModel();
		propertyVsStayTypeModel = (PropertyVsStayTypeModel) Util.transform(modelMapper, e, propertyVsStayTypeModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsStayTypeModel;
	}

	@Override
	public List<PropertyVsStayTypeModel> entityListToModelList(List<PropertyVsStayTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsStayTypeModel> propertyVsStayTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsStayTypeModels = new ArrayList<>();
			for(PropertyVsStayTypeEntity propertyVsStayTypeEntity:es) {
				propertyVsStayTypeModels.add(entityToModel(propertyVsStayTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsStayTypeModels;
	}

}
