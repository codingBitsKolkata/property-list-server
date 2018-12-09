package com.orastays.property.propertylist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsPriceDropModel extends CommonModel {

	@JsonProperty("propertyPDropId")
	private String propertyPDropId;
	
	@JsonProperty("percentage")
	private String percentage;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
	
	@JsonProperty("priceDrop")
	private PriceDropModel priceDropModel;
}
