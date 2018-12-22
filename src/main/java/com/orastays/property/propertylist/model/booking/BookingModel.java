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
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;

	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("propertyId")
	private String propertyId;

	@JsonProperty("checkinDate")
	private String checkinDate;

	@JsonProperty("checkoutDate")
	private String checkoutDate;

	@JsonProperty("numOfDays")
	private String numOfDays;

	@JsonProperty("totalPaybleWithoutGST")
	private String totalPaybleWithoutGST;

	@JsonProperty("totalPaybleWithGST")
	private String totalPaybleWithGST;

	@JsonProperty("grandTotal")
	private String grandTotal;

	@JsonProperty("conveniences")
	private ConvenienceModel convenienceModel;
	
	@JsonProperty("bookingInfos")
	private BookingInfoModel bookingInfoModel;

	@JsonProperty("bookingApproval")
	private String bookingApproval;
	
	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@JsonProperty("bookingVsRooms")
	private List<BookingVsRoomModel> bookingVsRoomModels;
	
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;
	

}



