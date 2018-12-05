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
public class PropertyModel extends CommonModel {

	private String propertyId;
	private String name;
	private String oraname;
	private String entireApartment;
	private String dedicatedSpace;
	private String apartmentName;
	private String apartmentNumber;
	private String latitude;
	private String longitude;
	private String address;
	private String startDate;
	private String endDate;
	private String checkinTime;
	private String checkoutTime;
	private String coverImageUrl;
	private String priceDrop;
	private PropertyTypeModel propertyTypeModel;
	private List<PropertyVsDescriptionModel> propertyVsDescriptionModels;
	private List<PropertyVsGuestAccessModel> propertyVsGuestAccessModels;
	private List<PropertyVsHomestayModel> propertyVsHomestayModels;
	private List<PropertyVsImageModel> propertyVsImageModels;
	private List<PropertyVsNearbyModel> propertyVsNearbyModels;
	private List<PropertyVsPgcsModel> propertyVsPgcsModels;
	private List<PropertyVsPriceDropModel> propertyVsPriceDropModels;
	private List<PropertyVsSpaceRuleModel> propertyVsSpaceRuleModels;
	private List<PropertyVsSpecialExperienceModel> propertyVsSpecialExperienceModels;
	private List<PropertyVsStayTypeModel> propertyVsStayTypeModels;
	private List<RoomModel> roomModel;
}
