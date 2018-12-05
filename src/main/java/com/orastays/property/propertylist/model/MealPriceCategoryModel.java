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
public class MealPriceCategoryModel extends CommonModel {

	private String mmpcId;
	private String name;
	private String languageId;
	private String parentId;
	private List<RoomVsMealModel> roomVsMealModels;
}
