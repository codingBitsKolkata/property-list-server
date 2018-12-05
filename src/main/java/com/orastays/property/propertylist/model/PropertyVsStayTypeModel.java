package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsStayTypeModel extends CommonModel {

	private String propertyStayTypeId;
	private StayTypeModel stayTypeModel;
	private PropertyModel propertyModel;
}
