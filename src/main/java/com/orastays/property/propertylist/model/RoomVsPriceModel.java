package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsPriceModel extends CommonModel {

	private String roomVsPriceId;
	private String price;
	private PriceTypeModel priceTypeModel;
	private RoomModel roomModel;
}
