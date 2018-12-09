package com.orastays.property.propertylist.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class AmenitiesModel extends CommonModel {

	@JsonProperty("aminitiesId")
	private String aminitiesId;
	
	@JsonProperty("aminitiesName")
	private String aminitiesName;
	
	@JsonProperty("filterFlag")
	private String filterFlag;
	
	@JsonProperty("priority")
	private String priority;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("roomVsAmenities")
	private List<RoomVsAmenitiesModel> roomVsAmenitiesModels;

	@JsonProperty("amenitiesType")
	private AmenitiesTypeModel amenitiesTypeModel;
}
