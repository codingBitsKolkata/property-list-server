package com.orastays.property.propertylist.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class BookingVsRoomOraDiscountModel extends CommonModel {

	@JsonProperty("brodId")
	private Long brodId;

	@JsonProperty("rodId")
	private String rodId;

	@JsonProperty("bookingVsRooms")
	private BookingVsRoomModel bookingVsRoomModel;
	
}
