package com.orastays.property.propertylist.service;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.WishlistModel;

public interface BookmarkService {

	void addWishlist(WishlistModel wishlistModel) throws FormExceptions;

}
