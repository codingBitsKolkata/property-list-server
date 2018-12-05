package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsPriceDropModel extends CommonModel {

	private String propertyPDropId;
	private String percentage;
	private PropertyModel propertyModel;
	private PriceDropModel priceDropModel;
}
