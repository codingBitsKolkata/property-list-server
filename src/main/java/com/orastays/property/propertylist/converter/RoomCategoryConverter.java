package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomCategoryEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomCategoryModel;

@Component
public class RoomCategoryConverter extends CommonConverter
		implements BaseConverter<RoomCategoryEntity, RoomCategoryModel> {

	private static final long serialVersionUID = 6255959173759896724L;
	private static final Logger logger = LogManager.getLogger(RoomCategoryConverter.class);

	@Override
	public RoomCategoryEntity modelToEntity(RoomCategoryModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomCategoryModel entityToModel(RoomCategoryEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomCategoryModel roomCategoryModel = new RoomCategoryModel();
		roomCategoryModel = (RoomCategoryModel) Util.transform(modelMapper, e, roomCategoryModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomCategoryModel;
	}

	@Override
	public List<RoomCategoryModel> entityListToModelList(List<RoomCategoryEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomCategoryModel> roomCategoryModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomCategoryModels = new ArrayList<>();
			for(RoomCategoryEntity roomCategoryEntity:es) {
				roomCategoryModels.add(entityToModel(roomCategoryEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomCategoryModels;
	}

}