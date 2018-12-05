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
public class RoomModel extends CommonModel {

	private String sharedSpace;
	private String cotAvailable;
	private String noOfGuest;
	private String noOfChild;
	private String numOfCot;
	private String commision;
	private String floorNo;
	private PropertyModel propertyModel;
	private AccommodationModel accommodationModel;
	private DiscountCategoryOraModel discountCategoryOraModel;
	private RoomCategoryModel roomCategoryModel;
	private RoomStandardModel roomStandardModel;
	private List<RoomVsAmenitiesModel> roomVsAmenitiesModels;
	private RoomVsInfoModel roomVsInfoModel;
	private List<RoomVsBedModel> roomVsBedModels;
	private List<RoomVsCancellationModel> roomVsCancellationModels;
	private List<RoomVsHostDiscountModel> roomVsHostDiscountModels;
	private List<RoomVsImageModel> roomVsImageModels;
	private List<RoomVsOraDiscountModel> roomVsOraDiscountModels;
	private List<RoomVsOrapricePercModel> roomVsOrapricePercModels;
	private List<RoomVsPriceModel> roomVsPriceModels;
	private List<RoomVsSpecialitiesModel> roomVsSpecialitiesModels;
	private List<RoomVsMealModel> roomVsMealModels;

}
