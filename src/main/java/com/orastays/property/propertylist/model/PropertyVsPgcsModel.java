package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsPgcsModel extends CommonModel {

	private String propertyPGCSId;
	private PGCategorySexModel pgCategorySexModel;
	private PropertyModel propertyModel;
}
