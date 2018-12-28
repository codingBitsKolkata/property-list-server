package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertyadd.entity.WishlistEntity;
import com.orastays.property.propertyadd.helper.Util;
import com.orastays.property.propertyadd.model.WishlistModel;

@Component
public class WishlistConverter extends CommonConverter implements BaseConverter<WishlistEntity, WishlistModel> {

	private static final long serialVersionUID = -6987883354674730631L;
	private static final Logger logger = LogManager.getLogger(WishlistConverter.class);

	@Override
	public WishlistEntity modelToEntity(WishlistModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WishlistModel entityToModel(WishlistEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		WishlistModel wishlistModel = new WishlistModel();
		wishlistModel = (WishlistModel) Util.transform(modelMapper, e, wishlistModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return wishlistModel;
	}

	@Override
	public List<WishlistModel> entityListToModelList(List<WishlistEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<WishlistModel> wishlistModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			wishlistModels = new ArrayList<>();
			for (WishlistEntity wishlistEntity : es) {
				wishlistModels.add(entityToModel(wishlistEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return wishlistModels;
	}

}
