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
public class UserInfo extends CommonModel {

	@JsonProperty("customerName")
	private String customerName;

	@JsonProperty("customerEmail")
	private String customerEmail;

	@JsonProperty("customerPhone")
	private String customerPhone;
}
