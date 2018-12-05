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
public class PriceDropModel extends CommonModel {

	private String priceDropId;
	private String afterTime;
	private List<PropertyVsPriceDropModel> propertyVsPriceDropModels;
}
