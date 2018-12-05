package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsPriceEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsPriceModel;

@Component
public class RoomVsPriceConverter extends CommonConverter
		implements BaseConverter<RoomVsPriceEntity, RoomVsPriceModel> {

	private static final long serialVersionUID = -6987883354674730631L;
	private static final Logger logger = LogManager.getLogger(RoomVsPriceConverter.class);

	@Override
	public RoomVsPriceEntity modelToEntity(RoomVsPriceModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsPriceModel entityToModel(RoomVsPriceEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsPriceModel roomVsPriceModel = new RoomVsPriceModel();
		roomVsPriceModel = (RoomVsPriceModel) Util.transform(modelMapper, e, roomVsPriceModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsPriceModel;
	}

	@Override
	public List<RoomVsPriceModel> entityListToModelList(List<RoomVsPriceEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsPriceModel> roomVsPriceModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsPriceModels = new ArrayList<>();
			for(RoomVsPriceEntity roomVsPriceEntity:es) {
				roomVsPriceModels.add(entityToModel(roomVsPriceEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsPriceModels;
	}
}
