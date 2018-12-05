package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class WishlistModel extends CommonModel {

	private String wishlistId;
	private String userId;
	private PropertyModel propertyModel;
}
