package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.MealPlanCatVsMealPlanEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.MealPlanCategoryVsMealPlanModel;

@Component
public class MealPlanCatVsMealPlanConverter extends CommonConverter
		implements BaseConverter<MealPlanCatVsMealPlanEntity, MealPlanCategoryVsMealPlanModel> {

	private static final long serialVersionUID = 5446873681124249282L;
	private static final Logger logger = LogManager.getLogger(MealPlanCatVsMealPlanConverter.class);

	@Override
	public MealPlanCatVsMealPlanEntity modelToEntity(MealPlanCategoryVsMealPlanModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MealPlanCategoryVsMealPlanModel entityToModel(MealPlanCatVsMealPlanEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		MealPlanCategoryVsMealPlanModel mealPlanCategoryVsMealPlanModel = new MealPlanCategoryVsMealPlanModel();
		mealPlanCategoryVsMealPlanModel = (MealPlanCategoryVsMealPlanModel) Util.transform(modelMapper, e, mealPlanCategoryVsMealPlanModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return mealPlanCategoryVsMealPlanModel;
	}

	@Override
	public List<MealPlanCategoryVsMealPlanModel> entityListToModelList(List<MealPlanCatVsMealPlanEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<MealPlanCategoryVsMealPlanModel> mealPlanCategoryVsMealPlanModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			mealPlanCategoryVsMealPlanModels = new ArrayList<>();
			for(MealPlanCatVsMealPlanEntity mealPlanCatVsMealPlanEntity:es) {
				mealPlanCategoryVsMealPlanModels.add(entityToModel(mealPlanCatVsMealPlanEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return mealPlanCategoryVsMealPlanModels;
	}

}
