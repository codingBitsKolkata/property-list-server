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
public class PriceTypeModel extends CommonModel {

	@JsonProperty("priceTypeId")
	private String priceTypeId;

	@JsonProperty("priceTypeName")
	private String priceTypeName;
	
	@JsonProperty("perFlag")
	private String perFlag;

	@JsonProperty("languageId")
	private String languageId;

	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("roomVsPrices")
	private List<RoomVsPriceModel> roomVsPriceModels;
}
