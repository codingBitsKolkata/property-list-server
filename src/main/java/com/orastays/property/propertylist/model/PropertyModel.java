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
public class PropertyModel extends CommonModel {

	@JsonProperty("propertyId")
	private String propertyId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("oraname")
	private String oraname;

	@JsonProperty("entireApartment")
	private String entireApartment;

	@JsonProperty("apartmentName")
	private String apartmentName;

	@JsonProperty("apartmentNumber")
	private String apartmentNumber;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("longitude")
	private String longitude;

	@JsonProperty("address")
	private String address;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("checkinTime")
	private String checkinTime;

	@JsonProperty("checkoutTime")
	private String checkoutTime;

	@JsonProperty("coverImageUrl")
	private String coverImageUrl;

	@JsonProperty("priceDrop")
	private String priceDrop;

	@JsonProperty("immediateBooking")
	private String immediateBooking;

	@JsonProperty("strictCheckin")
	private String strictCheckin;

	@JsonProperty("propertyType")
	private PropertyTypeModel propertyTypeModel;

	@JsonProperty("propertyVsDocuments")
	private List<PropertyVsDocumentModel> propertyVsDocumentModels;

	@JsonProperty("stayType")
	private StayTypeModel stayTypeModel;

	@JsonProperty("userVsAccount")
	private UserVsAccountModel userVsAccountModel;

	@JsonProperty("pgCategorySex")
	private PGCategorySexModel pgCategorySexModel;

	@JsonProperty("propertyVsDescriptions")
	private List<PropertyVsDescriptionModel> propertyVsDescriptionModels;

	@JsonProperty("propertyVsGuestAccess")
	private List<PropertyVsGuestAccessModel> propertyVsGuestAccessModels;

	@JsonProperty("propertyVsImages")
	private List<PropertyVsImageModel> propertyVsImageModels;

	@JsonProperty("propertyVsNearbys")
	private List<PropertyVsNearbyModel> propertyVsNearbyModels;

	@JsonProperty("propertyVsPriceDrops")
	private List<PropertyVsPriceDropModel> propertyVsPriceDropModels;

	@JsonProperty("propertyVsSpaceRules")
	private List<PropertyVsSpaceRuleModel> propertyVsSpaceRuleModels;

	@JsonProperty("propertyVsSpecialExperiences")
	private List<PropertyVsSpecialExperienceModel> propertyVsSpecialExperienceModels;

	@JsonProperty("rooms")
	private List<RoomModel> roomModels;
}
