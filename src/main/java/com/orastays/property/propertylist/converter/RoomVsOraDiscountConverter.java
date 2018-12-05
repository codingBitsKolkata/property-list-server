package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsOraDiscountEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsOraDiscountModel;

@Component
public class RoomVsOraDiscountConverter extends CommonConverter
		implements BaseConverter<RoomVsOraDiscountEntity, RoomVsOraDiscountModel> {

	private static final long serialVersionUID = 2117334663641665760L;
	private static final Logger logger = LogManager.getLogger(RoomVsOraDiscountConverter.class);

	@Override
	public RoomVsOraDiscountEntity modelToEntity(RoomVsOraDiscountModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsOraDiscountModel entityToModel(RoomVsOraDiscountEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsOraDiscountModel roomVsOraDiscountModel = new RoomVsOraDiscountModel();
		roomVsOraDiscountModel = (RoomVsOraDiscountModel) Util.transform(modelMapper, e, roomVsOraDiscountModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsOraDiscountModel;
	}

	@Override
	public List<RoomVsOraDiscountModel> entityListToModelList(List<RoomVsOraDiscountEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsOraDiscountModel> roomVsOraDiscountModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsOraDiscountModels = new ArrayList<>();
			for(RoomVsOraDiscountEntity roomVsOraDiscountEntity:es) {
				roomVsOraDiscountModels.add(entityToModel(roomVsOraDiscountEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsOraDiscountModels;
	}
}
