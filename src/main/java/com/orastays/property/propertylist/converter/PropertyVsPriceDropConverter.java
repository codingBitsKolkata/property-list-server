package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsPriceDropEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsPriceDropModel;

@Component
public class PropertyVsPriceDropConverter extends CommonConverter
		implements BaseConverter<PropertyVsPriceDropEntity, PropertyVsPriceDropModel> {

	private static final long serialVersionUID = -3079477860269369413L;
	private static final Logger logger = LogManager.getLogger(PropertyVsPriceDropConverter.class);

	@Override
	public PropertyVsPriceDropEntity modelToEntity(PropertyVsPriceDropModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsPriceDropModel entityToModel(PropertyVsPriceDropEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsPriceDropModel propertyVsPriceDropModel = new PropertyVsPriceDropModel();
		propertyVsPriceDropModel = (PropertyVsPriceDropModel) Util.transform(modelMapper, e, propertyVsPriceDropModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsPriceDropModel;
	}

	@Override
	public List<PropertyVsPriceDropModel> entityListToModelList(List<PropertyVsPriceDropEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsPriceDropModel> propertyVsPriceDropModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsPriceDropModels = new ArrayList<>();
			for(PropertyVsPriceDropEntity propertyVsPriceDropEntity:es) {
				propertyVsPriceDropModels.add(entityToModel(propertyVsPriceDropEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsPriceDropModels;
	}

}
