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
public class MealTypeModel extends CommonModel {

	private String mealTypeId;
	private String name;
	private String imgUrl;
	private List<RoomVsMealModel> roomVsMealModels;
}
