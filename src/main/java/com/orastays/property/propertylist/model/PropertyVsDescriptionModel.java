package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsDescriptionModel extends CommonModel {

	private String propertyDescId;
	private String description;
	private String languageId;
	private PropertyModel propertyModel;
}
