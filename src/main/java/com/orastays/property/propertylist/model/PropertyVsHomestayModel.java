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
public class PropertyVsHomestayModel extends CommonModel {

	@JsonProperty("propertyHomeStayId")
	private String propertyHomeStayId;
	
	@JsonProperty("immediateBooking")
	private String immediateBooking;
	
	@JsonProperty("strictCheckin")
	private String strictCheckin;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
}
