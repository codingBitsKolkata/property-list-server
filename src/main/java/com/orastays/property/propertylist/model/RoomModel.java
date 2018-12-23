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
public class RoomModel extends CommonModel {

	@JsonProperty("roomId")
	private String roomId;
	
	@JsonProperty("sharedSpace")
	private String sharedSpace;
	
	@JsonProperty("cotAvailable")
	private String cotAvailable;
	
	@JsonProperty("noOfGuest")
	private String noOfGuest;
	
	@JsonProperty("noOfChild")
	private String noOfChild;
	
	@JsonProperty("numOfCot")
	private String numOfCot;
	
	@JsonProperty("roomCurrentStatus")
	private String roomCurrentStatus;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
	
	@JsonProperty("accommodation")
	private AccommodationModel accommodationModel;
	
	@JsonProperty("discountCategoryOra")
	private DiscountCategoryOraModel discountCategoryOraModel;
	
	@JsonProperty("roomCategory")
	private RoomCategoryModel roomCategoryModel;
	
	@JsonProperty("roomStandard")
	private RoomStandardModel roomStandardModel;
	
	@JsonProperty("roomVsBed")
	private RoomVsBedModel roomVsBedModel;
	
	@JsonProperty("roomVsAmenities")
	private List<RoomVsAmenitiesModel> roomVsAmenitiesModels;
	
	@JsonProperty("roomVsCancellation")
	private List<RoomVsCancellationModel> roomVsCancellationModels;
	
	@JsonProperty("roomVsImage")
	private List<RoomVsImageModel> roomVsImageModels;
	
	@JsonProperty("roomVsPrice")
	private List<RoomVsPriceModel> roomVsPriceModels;
	
	@JsonProperty("roomVsHostDiscount")
	private List<RoomVsHostDiscountModel> roomVsHostDiscountModels;
	
	@JsonProperty("roomVsOraDiscount")
	private List<RoomVsOraDiscountModel> roomVsOraDiscountModels;
	
	@JsonProperty("roomVsOrapricePerc")
	private List<RoomVsOrapricePercModel> roomVsOrapricePercModels;
	
	@JsonProperty("roomVsSpecialities")
	private List<RoomVsSpecialitiesModel> roomVsSpecialitiesModels;
	
	@JsonProperty("roomVsMeal")
	private List<RoomVsMealModel> roomVsMealModels;

}
