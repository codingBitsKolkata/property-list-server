package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsOrapricePercModel extends CommonModel {

	private String ropId;
	private String percentage;
	private RoomModel roomModel;
}
