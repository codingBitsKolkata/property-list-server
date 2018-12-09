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
public class MealPriceCategoryModel extends CommonModel {

	@JsonProperty("mmpcId")
	private String mmpcId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("roomVsMeals")
	private List<RoomVsMealModel> roomVsMealModels;
}
