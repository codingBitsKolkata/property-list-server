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
public class RoomCategoryModel extends CommonModel {

	private String roomCatId;
	private String name;
	private String languageId;
	private String parentId;
	private List<RoomModel> roomModels;
	private PropertyTypeModel propertyTypeModel;
}
