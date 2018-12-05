package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsAmenitiesModel extends CommonModel {

	private String roomVsAmiId;
	private AmenitiesModel amenitiesModel;
	private RoomModel roomModel;
}
