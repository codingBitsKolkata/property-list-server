package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsInfoEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsInfoModel;

@Component
public class RoomVsInfoConverter extends CommonConverter implements BaseConverter<RoomVsInfoEntity, RoomVsInfoModel> {

	private static final long serialVersionUID = -6875617161053014130L;
	private static final Logger logger = LogManager.getLogger(RoomVsInfoConverter.class);

	@Override
	public RoomVsInfoEntity modelToEntity(RoomVsInfoModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsInfoModel entityToModel(RoomVsInfoEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsInfoModel roomVsInfoModel = new RoomVsInfoModel();
		roomVsInfoModel = (RoomVsInfoModel) Util.transform(modelMapper, e, roomVsInfoModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsInfoModel;
	}

	@Override
	public List<RoomVsInfoModel> entityListToModelList(List<RoomVsInfoEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsInfoModel> roomVsInfoModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsInfoModels = new ArrayList<>();
			for(RoomVsInfoEntity roomVsInfoEntity:es) {
				roomVsInfoModels.add(entityToModel(roomVsInfoEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsInfoModels;
	}

}
