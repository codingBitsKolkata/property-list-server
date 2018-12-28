package com.orastays.property.propertylist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MealPlanCategoryModel extends CommonModel {

	@JsonProperty("mpcId")
	private String mpcId;

	@JsonProperty("mpcName")
	private String mpcName;

	@JsonProperty("languageId")
	private String languageId;

	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("mealPlanCatVsMealPlans")
	private List<MealPlanCatVsMealPlanModel> mealPlanCatVsMealPlanEntities;
}
