package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.MealDaysEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.MealDaysModel;

@Component
public class MealDaysConverter extends CommonConverter implements BaseConverter<MealDaysEntity, MealDaysModel> {

	private static final long serialVersionUID = -6188321305638091179L;
	private static final Logger logger = LogManager.getLogger(MealDaysConverter.class);

	@Override
	public MealDaysEntity modelToEntity(MealDaysModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MealDaysModel entityToModel(MealDaysEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		MealDaysModel mealDaysModel = new MealDaysModel();
		mealDaysModel = (MealDaysModel) Util.transform(modelMapper, e, mealDaysModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return mealDaysModel;
	}

	@Override
	public List<MealDaysModel> entityListToModelList(List<MealDaysEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<MealDaysModel> mealDaysModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			mealDaysModels = new ArrayList<>();
			for(MealDaysEntity mealDaysEntity:es) {
				mealDaysModels.add(entityToModel(mealDaysEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return mealDaysModels;
	}

}
