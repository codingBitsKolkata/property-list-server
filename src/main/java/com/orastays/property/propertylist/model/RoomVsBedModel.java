package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsBedModel extends CommonModel {

	private String rbId;
	private String noOfBeds;
	private RoomModel roomModel;
}
