package com.orastays.property.propertylist.model.review;

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
public class BookingVsRatingModel extends CommonModel {

	@JsonProperty("bookingRatingId")
	private String bookingRatingId;
	
	@JsonProperty("propertyId")
	private String propertyId;
	
	@JsonProperty("rating")
	private String rating;
	
	@JsonProperty("ratings")
	private RatingModel ratings;
	
	@JsonProperty("userReview")
	private UserReviewModel userReview;
}
