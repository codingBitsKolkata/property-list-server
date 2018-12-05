package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsInfoModel extends CommonModel {

	private String riId;
	private String roomName;
	private String description;
	private String languageId;
	private String parentId;
	private RoomModel roomModel;
}
