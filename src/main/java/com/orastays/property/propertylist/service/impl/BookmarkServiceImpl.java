package com.orastays.property.propertylist.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.WishlistModel;
import com.orastays.property.propertylist.model.user.UserModel;
import com.orastays.property.propertylist.service.BookmarkService;

@Service
@Transactional
public class BookmarkServiceImpl extends BaseServiceImpl implements BookmarkService {

	private static final Logger logger = LogManager.getLogger(BookmarkServiceImpl.class);

	@Override
	public void addWishlist(WishlistModel wishlistModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("addWishlist -- START");
		}
		
		UserModel userModel = wishlistValidation.validateBookmark(wishlistModel);
		wishlistModel.setUserId(userModel.getUserId());
		wishlistDAO.save(wishlistConverter.modelToEntity(wishlistModel));
		
		if (logger.isInfoEnabled()) {
			logger.info("addWishlist -- END");
		}
		
	}
}
