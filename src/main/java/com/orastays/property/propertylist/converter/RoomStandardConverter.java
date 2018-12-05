package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomStandardEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomStandardModel;

@Component
public class RoomStandardConverter extends CommonConverter
		implements BaseConverter<RoomStandardEntity, RoomStandardModel> {

	private static final long serialVersionUID = 8126896890217765710L;
	private static final Logger logger = LogManager.getLogger(RoomStandardConverter.class);

	@Override
	public RoomStandardEntity modelToEntity(RoomStandardModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomStandardModel entityToModel(RoomStandardEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomStandardModel roomStandardModel = new RoomStandardModel();
		roomStandardModel = (RoomStandardModel) Util.transform(modelMapper, e, roomStandardModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomStandardModel;
	}

	@Override
	public List<RoomStandardModel> entityListToModelList(List<RoomStandardEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomStandardModel> roomStandardModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomStandardModels = new ArrayList<>();
			for(RoomStandardEntity roomStandardEntity:es) {
				roomStandardModels.add(entityToModel(roomStandardEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomStandardModels;
	}

}
