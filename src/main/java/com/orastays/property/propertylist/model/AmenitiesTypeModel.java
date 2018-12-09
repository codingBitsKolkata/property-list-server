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
public class AmenitiesTypeModel extends CommonModel {

	@JsonProperty("amiTypeId")
	private String amiTypeId;
	
	@JsonProperty("amenities")
	private List<AmenitiesModel> amenitiesModels;
	
	@JsonProperty("aminitiesTypeName")
	private String aminitiesTypeName;
}
