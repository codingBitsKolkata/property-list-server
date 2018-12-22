package com.orastays.property.propertylist.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class FilterCiteriaModel {

	private String userToken;
	private String sorting; // POPULARITY, PRICING
	private List<SpaceRuleModel> spaceRuleModels; // Couple Friendly, Pet Friendly
	private List<PGCategorySexModel> pgCategorySexModels; // Male/Female
	private String propertyTypeId; // Mandatory
	private String latitude; // Mandatory
	private String longitude; // Mandatory
	private String checkInDate; // Mandatory
	private String checkOutDate; // Mandatory
	private List<RoomModel> roomModels; // Mandatory
	private List<String> ratings; // Excellent(>8.5), Very Good(>7.5), Good(>6.5), Average(<6.5)
	private List<AmenitiesModel> amenitiesModels;
	private List<String> budgets;
	private List<String> popularLocations;
}
