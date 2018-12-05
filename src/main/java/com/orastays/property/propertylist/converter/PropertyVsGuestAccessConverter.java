package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsGuestAccessEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsGuestAccessModel;

@Component
public class PropertyVsGuestAccessConverter extends CommonConverter
		implements BaseConverter<PropertyVsGuestAccessEntity, PropertyVsGuestAccessModel> {

	private static final long serialVersionUID = 4517963113550272759L;
	private static final Logger logger = LogManager.getLogger(PropertyVsGuestAccessConverter.class);

	@Override
	public PropertyVsGuestAccessEntity modelToEntity(PropertyVsGuestAccessModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsGuestAccessModel entityToModel(PropertyVsGuestAccessEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsGuestAccessModel propertyVsGuestAccessModel = new PropertyVsGuestAccessModel();
		propertyVsGuestAccessModel = (PropertyVsGuestAccessModel) Util.transform(modelMapper, e, propertyVsGuestAccessModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsGuestAccessModel;
	}

	@Override
	public List<PropertyVsGuestAccessModel> entityListToModelList(List<PropertyVsGuestAccessEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsGuestAccessModel> propertyVsGuestAccessModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsGuestAccessModels = new ArrayList<>();
			for(PropertyVsGuestAccessEntity propertyVsGuestAccessEntity:es) {
				propertyVsGuestAccessModels.add(entityToModel(propertyVsGuestAccessEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsGuestAccessModels;
	}

}
