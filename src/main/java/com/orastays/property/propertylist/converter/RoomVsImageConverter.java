package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsImageEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsImageModel;

@Component
public class RoomVsImageConverter extends CommonConverter
		implements BaseConverter<RoomVsImageEntity, RoomVsImageModel> {

	private static final long serialVersionUID = 6202228879748998556L;
	private static final Logger logger = LogManager.getLogger(RoomVsImageConverter.class);

	@Override
	public RoomVsImageEntity modelToEntity(RoomVsImageModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsImageModel entityToModel(RoomVsImageEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsImageModel roomVsImageModel = new RoomVsImageModel();
		roomVsImageModel = (RoomVsImageModel) Util.transform(modelMapper, e, roomVsImageModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsImageModel;
	}

	@Override
	public List<RoomVsImageModel> entityListToModelList(List<RoomVsImageEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsImageModel> roomVsImageModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsImageModels = new ArrayList<>();
			for(RoomVsImageEntity roomVsImageEntity:es) {
				roomVsImageModels.add(entityToModel(roomVsImageEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsImageModels;
	}

}
