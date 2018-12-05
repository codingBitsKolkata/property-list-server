package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.RoomVsAmenitiesModel;

@Component
public class RoomVsAmenitiesConverter extends CommonConverter
		implements BaseConverter<RoomVsAmenitiesEntity, RoomVsAmenitiesModel> {

	private static final long serialVersionUID = -4858154515911634790L;
	private static final Logger logger = LogManager.getLogger(RoomVsAmenitiesConverter.class);

	@Override
	public RoomVsAmenitiesEntity modelToEntity(RoomVsAmenitiesModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVsAmenitiesModel entityToModel(RoomVsAmenitiesEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		RoomVsAmenitiesModel roomVsAmenitiesModel = new RoomVsAmenitiesModel();
		roomVsAmenitiesModel = (RoomVsAmenitiesModel) Util.transform(modelMapper, e, roomVsAmenitiesModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return roomVsAmenitiesModel;
	}

	@Override
	public List<RoomVsAmenitiesModel> entityListToModelList(List<RoomVsAmenitiesEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<RoomVsAmenitiesModel> roomVsAmenitiesModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			roomVsAmenitiesModels = new ArrayList<>();
			for(RoomVsAmenitiesEntity roomVsAmenitiesEntity:es) {
				roomVsAmenitiesModels.add(entityToModel(roomVsAmenitiesEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return roomVsAmenitiesModels;
	}

}
