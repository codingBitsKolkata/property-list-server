package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsHomestayModel extends CommonModel {

	private String propertyHomeStayId;
	private String immediateBooking;
	private String strictCheckin;
	private PropertyModel propertyModel;
}
