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
public class PropertyTypeModel extends CommonModel {

	private String propertyTypeId;
	private String languageId;
	private String parentId;
	private String name;
	private List<PropertyModel> propertyModels;
	private List<RoomCategoryModel> roomCategoryModels;
}
