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
public class MealPlanCatVsMealPlanModel extends CommonModel {

	@JsonProperty("mpcmpId")
	private String mpcmpId;

	@JsonProperty("mealPlanCategory")
	private MealPlanCategoryModel mealPlanCategoryModel;

	@JsonProperty("mealPlan")
	private MealPlanModel mealPlanModel;
}
