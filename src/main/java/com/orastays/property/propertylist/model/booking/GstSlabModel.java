package com.orastays.property.propertylist.model.booking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class GstSlabModel extends CommonModel {

	@JsonProperty("gstSlabId")
	private Long gstSlabId;

	@JsonProperty("fromAmount")
	private String fromAmount;

	@JsonProperty("toAmount")
	private String toAmount;

	@JsonProperty("percentage")
	private String percentage;

	@JsonProperty("bookingVsRooms")
	private List<BookingVsRoomModel> bookingVsRoomModels;

}
