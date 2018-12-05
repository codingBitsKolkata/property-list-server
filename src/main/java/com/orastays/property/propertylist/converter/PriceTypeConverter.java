package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PriceTypeEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PriceTypeModel;

@Component
public class PriceTypeConverter extends CommonConverter implements BaseConverter<PriceTypeEntity, PriceTypeModel> {

	private static final long serialVersionUID = 8908736000853319703L;
	private static final Logger logger = LogManager.getLogger(PriceTypeConverter.class);

	@Override
	public PriceTypeEntity modelToEntity(PriceTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceTypeModel entityToModel(PriceTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PriceTypeModel priceTypeModel = new PriceTypeModel();
		priceTypeModel = (PriceTypeModel) Util.transform(modelMapper, e, priceTypeModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return priceTypeModel;
	}

	@Override
	public List<PriceTypeModel> entityListToModelList(List<PriceTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PriceTypeModel> priceTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			priceTypeModels = new ArrayList<>();
			for(PriceTypeEntity priceTypeEntity:es) {
				priceTypeModels.add(entityToModel(priceTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return priceTypeModels;
	}

}
