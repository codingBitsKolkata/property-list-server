package com.orastays.property.propertylist.model.booking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingVsRoomModel extends CommonModel {

	@JsonProperty("bookingVsRoomId")
	private String bookingVsRoomId;

	@JsonProperty("roomId")
	private String roomId;

	@JsonProperty("numOfAdult")
	private String numOfAdult;

	@JsonProperty("numOfCot")
	private String numOfCot;

	@JsonProperty("ropId")
	private String ropId;

	@JsonProperty("rhdId")
	private String rhdId;
	
	@JsonProperty("rodId")
	private String rodId;
	
	@JsonProperty("propertyPriceDropId")
	private String propertyPriceDropId;

	@JsonProperty("roomGSTSlabPrice")
	private String roomGSTSlabPrice;

	@JsonProperty("sgst")
	private String sgst;

	@JsonProperty("cgst")
	private String cgst;

	@JsonProperty("igst")
	private String igst;

	@JsonProperty("roomActualPrice")
	private String roomActualPrice;
	
	@JsonProperty("bookingPrices")
	private List<BookingPriceModel> bookingPriceModels;

	@JsonProperty("bookings")
	private BookingModel bookingModel;

	@JsonProperty("sacCodes")
	private SacCodeModel sacCodeModel;

	@JsonProperty("gstSlabs")
	private GstSlabModel gstSlabModel;
	
	@JsonProperty("accommodation")
	private AccommodationModel accommodationModel;
	
	@JsonProperty("numOfSharedBed")
	private String numOfSharedBed;
	
	@JsonProperty("numOfSharedCot")
	private String numOfSharedCot;
	
	@JsonProperty("totalNumOfSharedCot")
	private String totalNumOfSharedCot;
	
	@JsonProperty("totalNumOfSharedBed")
	private String totalNumOfSharedBed;
	
	@JsonProperty("BookingVsRoomOraDiscounts")
	private List<BookingVsRoomOraDiscountModel> bookingVsRoomOraDiscountModels;
	
	
}
