package com.orastays.property.propertylist.model.booking;

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
	
	@JsonProperty("accommodationName")
	private String accommodationName;
	
}
