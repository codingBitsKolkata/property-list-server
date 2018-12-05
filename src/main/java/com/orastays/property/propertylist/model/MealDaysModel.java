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
public class MealDaysModel extends CommonModel {

	private String mealDaysId;
	private String name;
	private String languageId;
	private String parentId;
	private List<RoomVsMealModel> roomVsMealModels;
}
