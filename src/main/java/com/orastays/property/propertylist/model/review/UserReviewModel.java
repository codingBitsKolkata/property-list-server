package com.orastays.property.propertylist.model.review;

import java.util.List;

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
public class UserReviewModel extends CommonModel {

	@JsonProperty("userReviewId")
	private String userReviewId;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("bookingId")
	private String bookingId;
	
	@JsonProperty("propertyId")
	private String propertyId;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("userTypeId")
	private String userTypeId;
	
	@JsonProperty("bookingVsRatings")
	private List<BookingVsRatingModel> bookingVsRatingModels;
}