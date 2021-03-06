package com.orastays.propertylist.model.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.orastays.propertylist.model.CommonModel;
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
public class BookingPriceModel extends CommonModel {

	@JsonProperty("bookingPriceId")
	private String bookingPriceId;

	@JsonProperty("roomVsPriceId")
	private String roomVsPriceId;

	@JsonProperty("bookingVsRooms")
	private BookingVsRoomModel bookingVsRoomModel;

}
