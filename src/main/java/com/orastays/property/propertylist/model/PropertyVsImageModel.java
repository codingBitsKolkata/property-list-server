package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsImageModel extends CommonModel {

	private String propertyImageId;
	private String imageURL;
	private PropertyModel propertyModel;
}
