package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsPgcsEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsPgcsModel;

@Component
public class PropertyVsPgcsConverter extends CommonConverter
		implements BaseConverter<PropertyVsPgcsEntity, PropertyVsPgcsModel> {

	private static final long serialVersionUID = -549724460872810295L;
	private static final Logger logger = LogManager.getLogger(PropertyVsPgcsConverter.class);

	@Override
	public PropertyVsPgcsEntity modelToEntity(PropertyVsPgcsModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsPgcsModel entityToModel(PropertyVsPgcsEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsPgcsModel propertyVsPgcsModel = new PropertyVsPgcsModel();
		propertyVsPgcsModel = (PropertyVsPgcsModel) Util.transform(modelMapper, e, propertyVsPgcsModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsPgcsModel;
	}

	@Override
	public List<PropertyVsPgcsModel> entityListToModelList(List<PropertyVsPgcsEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsPgcsModel> propertyVsPgcsModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsPgcsModels = new ArrayList<>();
			for(PropertyVsPgcsEntity propertyVsPgcsEntity:es) {
				propertyVsPgcsModels.add(entityToModel(propertyVsPgcsEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsPgcsModels;
	}

}
