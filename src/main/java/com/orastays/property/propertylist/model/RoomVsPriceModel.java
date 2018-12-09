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
public class RoomVsPriceModel extends CommonModel {

	@JsonProperty("roomVsPriceId")
	private String roomVsPriceId;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("priceType")
	private PriceTypeModel priceTypeModel;
	
	@JsonProperty("room")
	private RoomModel roomModel;
}
