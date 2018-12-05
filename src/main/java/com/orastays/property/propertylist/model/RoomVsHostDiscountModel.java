package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsHostDiscountModel extends CommonModel {

	private String rhdId;
	private String percentage;
	private DiscountCategoryHostModel discountCategoryHostModel;
	private RoomModel roomModel;
}
