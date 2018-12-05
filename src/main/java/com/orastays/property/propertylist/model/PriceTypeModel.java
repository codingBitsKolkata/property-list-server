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
public class PriceTypeModel extends CommonModel {

	private String priceTypeId;
	private String PriceTypeName;
	private String languageId;
	private String parentId;
	private List<RoomVsPriceModel> roomVsPriceModels;
}
