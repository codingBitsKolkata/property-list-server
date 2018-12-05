package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsNearbyModel extends CommonModel {

	private String propertyNearbyId;
	private String places;
	private PropertyModel propertyModel;
}
