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
public class DiscountCategoryHostModel extends CommonModel {

	private String dchId;
	private String disCatHostname;
	private String languageId;
	private String parentId;
	private List<RoomVsHostDiscountModel> roomVsHostDiscountModels;
}
