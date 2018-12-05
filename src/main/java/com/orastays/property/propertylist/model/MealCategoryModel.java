package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MealCategoryModel extends CommonModel {

	private String mealCategoryId;
	private String mealCatName;
	private String languageId;
	private String parentId;
}
