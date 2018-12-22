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
public class PropertyListViewModel {

	private String propertyId;
	private String oraName;
	private String address;
	private String latitude;
	private String longitude;
	private String coverImageURL;
	private List<RoomStandardModel> roomStandardModels;
	private String rating;
	private String reviewCount;
	private List<SpaceRuleModel> spaceRuleModels; // Couple Friendly, Pet Friendly, Male/Female
	private String totalPrice;
	private String discountedPrice;
	private String mealFlag;
	private List<AmenitiesModel> amenitiesModels;
}