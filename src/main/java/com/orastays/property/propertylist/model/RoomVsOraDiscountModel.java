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
public class RoomVsOraDiscountModel extends CommonModel {

	@JsonProperty("rodId")
	private String rodId;
	
	@JsonProperty("discount")
	private String discount;
	
	@JsonProperty("room")
	private RoomModel roomModel;
	
	@JsonProperty("discountCategoryOra")
	private DiscountCategoryOraModel discountCategoryOraModel;
}
