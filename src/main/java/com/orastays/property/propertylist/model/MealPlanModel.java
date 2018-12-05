package com.orastays.property.propertylist.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MealPlanModel extends CommonModel {

	private String mealPlanId;
	private String mealPlanName;
	private String languageId;
	private String parentId;
	private List<MealPlanCategoryVsMealPlanModel> mealPlanCategoryVsMealPlanModels;
}
