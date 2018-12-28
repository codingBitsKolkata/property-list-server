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
public class AmenitiesModel extends CommonModel {

	@JsonProperty("aminitiesId")
	private String aminitiesId;

	@JsonProperty("aminitiesName")
	private String aminitiesName;

	@JsonProperty("filterFlag")
	private String filterFlag;

	@JsonProperty("priority")
	private String priority;

	@JsonProperty("expressFlag")
	private String expressFlag;

	@JsonProperty("premiumFlag")
	private String premiumFlag;

	@JsonProperty("aminitiesType")
	private String aminitiesType;

	@JsonProperty("languageId")
	private String languageId;

	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("roomVsAmenities")
	private List<RoomVsAmenitiesModel> roomVsAmenitiesModels;
}
