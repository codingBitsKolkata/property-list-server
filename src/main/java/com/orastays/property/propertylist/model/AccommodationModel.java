package com.orastays.property.propertylist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class AccommodationModel extends CommonModel {

	@JsonProperty("accommodationId")
	private String accommodationId;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("accommodationName")
	private String accommodationName;
	
	@JsonProperty("rooms")
	private List<RoomModel> roomModels;

}
