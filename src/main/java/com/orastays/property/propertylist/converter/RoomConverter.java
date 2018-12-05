package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomModel;

@Component
public class RoomConverter extends CommonConverter
		implements BaseConverter<RoomEntity, RoomModel> {

	
	private static final long serialVersionUID = -6532505845959859730L;
	private static final Logger logger = LogManager.getLogger(RoomConverter.class);

	@Override
	public RoomEntity modelToEntity(RoomModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomModel entityToModel(RoomEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomModel roomModel = new RoomModel();
		roomModel = (RoomModel) Util.transform(modelMapper, e, roomModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomModel;
	}

	@Override
	public List<RoomModel> entityListToModelList(List<RoomEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomModel> roomModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomModels = new ArrayList<>();
			for(RoomEntity roomEntity:es) {
				roomModels.add(entityToModel(roomEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomModels;
	}

	

}
