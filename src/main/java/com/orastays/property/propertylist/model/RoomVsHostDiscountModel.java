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
public class RoomVsHostDiscountModel extends CommonModel {

	@JsonProperty("rhdId")
	private String rhdId;
	
	@JsonProperty("percentage")
	private String percentage;
	
	@JsonProperty("discountCategoryHost")
	private DiscountCategoryHostModel discountCategoryHostModel;
	
	@JsonProperty("room")
	private RoomModel roomModel;
}
