package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsDescriptionEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsDescriptionModel;

@Component
public class PropertyVsDescriptionConverter extends CommonConverter
		implements BaseConverter<PropertyVsDescriptionEntity, PropertyVsDescriptionModel> {

	private static final long serialVersionUID = 450326924626216300L;
	private static final Logger logger = LogManager.getLogger(PropertyVsDescriptionConverter.class);

	@Override
	public PropertyVsDescriptionEntity modelToEntity(PropertyVsDescriptionModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsDescriptionModel entityToModel(PropertyVsDescriptionEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsDescriptionModel propertyVsDescriptionModel = new PropertyVsDescriptionModel();
		propertyVsDescriptionModel = (PropertyVsDescriptionModel) Util.transform(modelMapper, e, propertyVsDescriptionModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsDescriptionModel;
	}

	@Override
	public List<PropertyVsDescriptionModel> entityListToModelList(List<PropertyVsDescriptionEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsDescriptionModel> propertyVsDescriptionModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsDescriptionModels = new ArrayList<>();
			for(PropertyVsDescriptionEntity propertyVsDescriptionEntity:es) {
				propertyVsDescriptionModels.add(entityToModel(propertyVsDescriptionEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsDescriptionModels;
	}

}
