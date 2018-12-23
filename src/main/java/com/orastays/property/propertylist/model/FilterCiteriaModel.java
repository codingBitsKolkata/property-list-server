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
public class FilterCiteriaModel {

	@JsonProperty("userToken")
	private String userToken;
	
	@JsonProperty("sorting")
	private String sorting; // POPULARITY, PRICING
	
	@JsonProperty("spaceRule")
	private List<SpaceRuleModel> spaceRuleModels; // Couple Friendly, Pet Friendly
	
	@JsonProperty("pgCategorySex")
	private List<PGCategorySexModel> pgCategorySexModels; // Male/Female
	
	@JsonProperty("propertyTypeId")
	private String propertyTypeId; // Mandatory
	
	@JsonProperty("latitude")
	private String latitude; // Mandatory
	
	@JsonProperty("longitude")
	private String longitude; // Mandatory
	
	@JsonProperty("checkInDate")
	private String checkInDate; // Mandatory
	
	@JsonProperty("checkOutDate")
	private String checkOutDate; // Mandatory
	
	@JsonProperty("rooms")
	private List<RoomModel> roomModels; // Mandatory
	
	@JsonProperty("ratings")
	private List<String> ratings; // Excellent(>8.5), Very Good(>7.5), Good(>6.5), Average(<6.5)
	
	@JsonProperty("amenities")
	private List<AmenitiesModel> amenitiesModels;
	
	@JsonProperty("budgets")
	private List<String> budgets;
	
	@JsonProperty("popularLocations")
	private List<String> popularLocations;
}
