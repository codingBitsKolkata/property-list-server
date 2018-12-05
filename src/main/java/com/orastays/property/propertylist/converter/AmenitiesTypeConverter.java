package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.AmenitiesTypeEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.AmenitiesTypeModel;

@Component
public class AmenitiesTypeConverter extends CommonConverter implements BaseConverter<AmenitiesTypeEntity, AmenitiesTypeModel> {

	private static final long serialVersionUID = -5497668039041430153L;
	private static final Logger logger = LogManager.getLogger(AmenitiesTypeConverter.class);

	@Override
	public AmenitiesTypeEntity modelToEntity(AmenitiesTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmenitiesTypeModel entityToModel(AmenitiesTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		AmenitiesTypeModel amenitiesTypeModel = new AmenitiesTypeModel();
		amenitiesTypeModel = (AmenitiesTypeModel) Util.transform(modelMapper, e, amenitiesTypeModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return amenitiesTypeModel;
	}

	@Override
	public List<AmenitiesTypeModel> entityListToModelList(List<AmenitiesTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<AmenitiesTypeModel> amenitiesTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			amenitiesTypeModels = new ArrayList<>();
			for(AmenitiesTypeEntity amenitiesTypeEntity:es) {
				amenitiesTypeModels.add(entityToModel(amenitiesTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return amenitiesTypeModels;
	}

}
