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
public class MealTypeModel extends CommonModel {

	@JsonProperty("mealTypeId")
	private String mealTypeId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("imgUrl")
	private String imgUrl;

	@JsonProperty("roomVsMeals")
	private List<RoomVsMealModel> roomVsMealModels;
}
