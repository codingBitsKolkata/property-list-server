package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsSpecialExperienceEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsSpecialExperienceModel;

@Component
public class PropertyVsSpecialExperienceConverter extends CommonConverter
		implements BaseConverter<PropertyVsSpecialExperienceEntity, PropertyVsSpecialExperienceModel> {

	private static final long serialVersionUID = 4778058926083747281L;
	private static final Logger logger = LogManager.getLogger(PropertyVsSpecialExperienceConverter.class);

	@Override
	public PropertyVsSpecialExperienceEntity modelToEntity(PropertyVsSpecialExperienceModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyVsSpecialExperienceModel entityToModel(PropertyVsSpecialExperienceEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsSpecialExperienceModel propertyVsSpecialExperienceModel = new PropertyVsSpecialExperienceModel();
		propertyVsSpecialExperienceModel = (PropertyVsSpecialExperienceModel) Util.transform(modelMapper, e, propertyVsSpecialExperienceModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsSpecialExperienceModel;
	}

	@Override
	public List<PropertyVsSpecialExperienceModel> entityListToModelList(List<PropertyVsSpecialExperienceEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsSpecialExperienceModel> propertyVsSpecialExperienceModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsSpecialExperienceModels = new ArrayList<>();
			for(PropertyVsSpecialExperienceEntity propertyVsSpecialExperienceEntity:es) {
				propertyVsSpecialExperienceModels.add(entityToModel(propertyVsSpecialExperienceEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsSpecialExperienceModels;
	}

}
