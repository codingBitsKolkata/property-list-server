package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsMealModel extends CommonModel {

	private String roomVsMealId;
	private MealCategoryModel mealCategoryModel;
	private RoomModel roomModel;
	private MealDaysModel mealDaysModel;
	private MealTypeModel mealTypeModel;
	private MealPlanCategoryVsMealPlanModel planCategoryVsMealPlanModel;
	private MealPriceCategoryModel mealPriceCategoryModel;
}
