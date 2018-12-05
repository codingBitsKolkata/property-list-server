package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsMealEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsMealModel;

@Component
public class RoomVsMealEntityConverter extends CommonConverter
		implements BaseConverter<RoomVsMealEntity, RoomVsMealModel> {

	private static final long serialVersionUID = -4627576649206466658L;
	
	private static final Logger logger = LogManager.getLogger(RoomVsMealEntityConverter.class);

	@Override
	public RoomVsMealEntity modelToEntity(RoomVsMealModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsMealModel entityToModel(RoomVsMealEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsMealModel roomVsMealModel = new RoomVsMealModel();
		roomVsMealModel = (RoomVsMealModel) Util.transform(modelMapper, e, roomVsMealModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsMealModel;
	}

	@Override
	public List<RoomVsMealModel> entityListToModelList(List<RoomVsMealEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsMealModel> roomVsMealModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsMealModels = new ArrayList<>();
			for(RoomVsMealEntity roomVsMealEntity:es) {
				roomVsMealModels.add(entityToModel(roomVsMealEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsMealModels;
	}

}
