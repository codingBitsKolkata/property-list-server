package com.orastays.property.propertylist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsMealModel extends CommonModel {

	@JsonProperty("roomVsMealId")
	private String roomVsMealId;
	
	@JsonProperty("mealCategory")
	private MealCategoryModel mealCategoryModel;
	
	@JsonProperty("room")
	private RoomModel roomModel;
	
	@JsonProperty("mealDays")
	private MealDaysModel mealDaysModel;
	
	@JsonProperty("mealType")
	private MealTypeModel mealTypeModel;
	
	@JsonProperty("mealPlanCategoryVsMealPlan")
	private MealPlanCategoryVsMealPlanModel mealPlanCategoryVsMealPlanModel;
	
	@JsonProperty("mealPriceCategory")
	private MealPriceCategoryModel mealPriceCategoryModel;
}
