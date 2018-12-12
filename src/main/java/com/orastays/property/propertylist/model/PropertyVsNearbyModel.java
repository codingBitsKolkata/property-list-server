package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsNearbyModel extends CommonModel {

	@JsonProperty("propertyNearbyId")
	private String propertyNearbyId;
	
	@JsonProperty("places")
	private String places;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
}
