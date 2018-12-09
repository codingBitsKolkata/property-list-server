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
public class RoomVsOrapricePercModel extends CommonModel {

	@JsonProperty("ropId")
	private String ropId;
	
	@JsonProperty("percentage")
	private String percentage;
	
	@JsonProperty("room")
	private RoomModel roomModel;
}
