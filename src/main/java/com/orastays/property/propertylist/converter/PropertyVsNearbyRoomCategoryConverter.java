package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsNearbyEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsNearbyModel;

@Component
public class PropertyVsNearbyRoomCategoryConverter extends CommonConverter
		implements BaseConverter<PropertyVsNearbyEntity, PropertyVsNearbyModel> {

	private static final long serialVersionUID = -5052826047073738394L;
	private static final Logger logger = LogManager.getLogger(PropertyVsNearbyRoomCategoryConverter.class);

	@Override
	public PropertyVsNearbyEntity modelToEntity(PropertyVsNearbyModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsNearbyModel entityToModel(PropertyVsNearbyEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsNearbyModel propertyVsNearbyModel = new PropertyVsNearbyModel();
		propertyVsNearbyModel = (PropertyVsNearbyModel) Util.transform(modelMapper, e, propertyVsNearbyModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsNearbyModel;
	}

	@Override
	public List<PropertyVsNearbyModel> entityListToModelList(List<PropertyVsNearbyEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsNearbyModel> propertyVsNearbyModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsNearbyModels = new ArrayList<>();
			for(PropertyVsNearbyEntity propertyVsNearbyEntity:es) {
				propertyVsNearbyModels.add(entityToModel(propertyVsNearbyEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsNearbyModels;
	}

}
