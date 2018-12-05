package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsCancellationEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsCancellationModel;

@Component
public class RoomVsCancellationConverter extends CommonConverter
		implements BaseConverter<RoomVsCancellationEntity, RoomVsCancellationModel> {

	private static final long serialVersionUID = 5826695203338063494L;
	private static final Logger logger = LogManager.getLogger(RoomVsCancellationConverter.class);

	@Override
	public RoomVsCancellationEntity modelToEntity(RoomVsCancellationModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsCancellationModel entityToModel(RoomVsCancellationEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsCancellationModel roomVsCancellationModel = new RoomVsCancellationModel();
		roomVsCancellationModel = (RoomVsCancellationModel) Util.transform(modelMapper, e, roomVsCancellationModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsCancellationModel;
	}

	@Override
	public List<RoomVsCancellationModel> entityListToModelList(List<RoomVsCancellationEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsCancellationModel> roomVsCancellationModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsCancellationModels = new ArrayList<>();
			for(RoomVsCancellationEntity roomVsCancellationEntity:es) {
				roomVsCancellationModels.add(entityToModel(roomVsCancellationEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsCancellationModels;
	}

}
