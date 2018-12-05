package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsSpaceRuleEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsSpaceRuleModel;

@Component
public class PropertyVsSpaceRuleConverter extends CommonConverter
		implements BaseConverter<PropertyVsSpaceRuleEntity, PropertyVsSpaceRuleModel> {

	private static final long serialVersionUID = 4884374267851340797L;
	private static final Logger logger = LogManager.getLogger(PropertyVsSpaceRuleConverter.class);

	@Override
	public PropertyVsSpaceRuleEntity modelToEntity(PropertyVsSpaceRuleModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsSpaceRuleModel entityToModel(PropertyVsSpaceRuleEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsSpaceRuleModel propertyVsSpaceRuleModel = new PropertyVsSpaceRuleModel();
		propertyVsSpaceRuleModel = (PropertyVsSpaceRuleModel) Util.transform(modelMapper, e, propertyVsSpaceRuleModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsSpaceRuleModel;
	}

	@Override
	public List<PropertyVsSpaceRuleModel> entityListToModelList(List<PropertyVsSpaceRuleEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsSpaceRuleModel> propertyVsSpaceRuleModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsSpaceRuleModels = new ArrayList<>();
			for(PropertyVsSpaceRuleEntity propertyVsSpaceRuleEntity:es) {
				propertyVsSpaceRuleModels.add(entityToModel(propertyVsSpaceRuleEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsSpaceRuleModels;
	}

}
