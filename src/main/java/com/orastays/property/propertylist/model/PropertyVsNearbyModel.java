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
public class PropertyVsNearbyModel extends CommonModel {

	@JsonProperty("propertyNearbyId")
	private String propertyNearbyId;
	
	@JsonProperty("places")
	private String places;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
}
