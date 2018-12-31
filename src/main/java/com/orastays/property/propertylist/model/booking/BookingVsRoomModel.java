package com.orastays.property.propertylist.model.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.property.propertylist.model.CommonModel;

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

	@JsonProperty("oraRoomName")
	private String oraRoomName;
	
	@JsonProperty("roomId")
	private String roomId;

	@JsonProperty("numOfAdult")
	private String numOfAdult;

	@JsonProperty("numOfCot")
	private String numOfCot;
	
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
	
	@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomModel cancellationVsRoomModel;
	
	@JsonProperty("roomVsOfferId")
	private String roomVsOfferId;
	
	
}
