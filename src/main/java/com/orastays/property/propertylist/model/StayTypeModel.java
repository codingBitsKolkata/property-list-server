package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class StayTypeModel extends CommonModel {

	private String stayTypeId;
	private String languageId;
	private String parentId;
	private String stayTypeName;
}
