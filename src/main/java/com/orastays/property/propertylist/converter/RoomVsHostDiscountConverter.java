package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsHostDiscountEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsHostDiscountModel;

@Component
public class RoomVsHostDiscountConverter extends CommonConverter
		implements BaseConverter<RoomVsHostDiscountEntity, RoomVsHostDiscountModel> {

	private static final long serialVersionUID = -4000646452448985112L;
	private static final Logger logger = LogManager.getLogger(RoomVsHostDiscountConverter.class);

	@Override
	public RoomVsHostDiscountEntity modelToEntity(RoomVsHostDiscountModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsHostDiscountModel entityToModel(RoomVsHostDiscountEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsHostDiscountModel roomVsHostDiscountModel = new RoomVsHostDiscountModel();
		roomVsHostDiscountModel = (RoomVsHostDiscountModel) Util.transform(modelMapper, e, roomVsHostDiscountModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsHostDiscountModel;
	}

	@Override
	public List<RoomVsHostDiscountModel> entityListToModelList(List<RoomVsHostDiscountEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsHostDiscountModel> roomVsHostDiscountModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsHostDiscountModels = new ArrayList<>();
			for(RoomVsHostDiscountEntity roomVsHostDiscountEntity:es) {
				roomVsHostDiscountModels.add(entityToModel(roomVsHostDiscountEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsHostDiscountModels;
	}

}
