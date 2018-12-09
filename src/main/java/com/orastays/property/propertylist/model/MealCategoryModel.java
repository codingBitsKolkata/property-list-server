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
public class MealCategoryModel extends CommonModel {

	@JsonProperty("mealCategoryId")
	private String mealCategoryId;
	
	@JsonProperty("mealCatName")
	private String mealCatName;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("roomVsMeals")
	private List<RoomVsMealModel> roomVsMealModels;
}
