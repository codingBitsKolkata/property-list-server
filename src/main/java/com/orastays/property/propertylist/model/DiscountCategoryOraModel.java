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
public class DiscountCategoryOraModel extends CommonModel {

	private String dcoId;
	private String disCatOraname;
	private List<RoomModel> roomModels;
}
