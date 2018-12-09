package com.orastays.property.propertylist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsInfoModel extends CommonModel {

	@JsonProperty("riId")
	private String riId;
	
	@JsonProperty("roomName")
	private String roomName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("room")
	private RoomModel roomModel;
}
