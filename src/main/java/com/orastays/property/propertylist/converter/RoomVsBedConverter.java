package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsBedEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsBedModel;

@Component
public class RoomVsBedConverter extends CommonConverter implements BaseConverter<RoomVsBedEntity, RoomVsBedModel> {

	private static final long serialVersionUID = -710429158752467664L;
	private static final Logger logger = LogManager.getLogger(RoomVsBedConverter.class);

	@Override
	public RoomVsBedEntity modelToEntity(RoomVsBedModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsBedModel entityToModel(RoomVsBedEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsBedModel roomVsBedModel = new RoomVsBedModel();
		roomVsBedModel = (RoomVsBedModel) Util.transform(modelMapper, e, roomVsBedModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsBedModel;
	}

	@Override
	public List<RoomVsBedModel> entityListToModelList(List<RoomVsBedEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsBedModel> roomVsBedModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsBedModels = new ArrayList<>();
			for(RoomVsBedEntity roomVsBedEntity:es) {
				roomVsBedModels.add(entityToModel(roomVsBedEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsBedModels;
	}

}
