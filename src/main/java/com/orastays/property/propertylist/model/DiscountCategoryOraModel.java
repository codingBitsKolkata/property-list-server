package com.orastays.property.propertylist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class DiscountCategoryOraModel extends CommonModel {

	@JsonProperty("dcoId")
	private String dcoId;
	
	@JsonProperty("disCatOraName")
	private String disCatOraName;
	
	@JsonProperty("roomVsOraDiscounts")
	private List<RoomVsOraDiscountModel> roomVsOraDiscountModels;
}
