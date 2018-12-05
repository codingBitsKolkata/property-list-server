package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.MealTypeEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.MealTypeModel;

@Component
public class MealTypeConverter extends CommonConverter implements BaseConverter<MealTypeEntity, MealTypeModel> {

	private static final long serialVersionUID = -3373076508647710858L;
	private static final Logger logger = LogManager.getLogger(MealTypeConverter.class);

	@Override
	public MealTypeEntity modelToEntity(MealTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MealTypeModel entityToModel(MealTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		MealTypeModel mealTypeModel = new MealTypeModel();
		mealTypeModel = (MealTypeModel) Util.transform(modelMapper, e, mealTypeModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return mealTypeModel;
	}

	@Override
	public List<MealTypeModel> entityListToModelList(List<MealTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<MealTypeModel> mealTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			mealTypeModels = new ArrayList<>();
			for(MealTypeEntity mealTypeEntity:es) {
				mealTypeModels.add(entityToModel(mealTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return mealTypeModels;
	}
}
