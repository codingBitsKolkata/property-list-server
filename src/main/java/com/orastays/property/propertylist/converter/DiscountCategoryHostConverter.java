package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.DiscountCategoryHostEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.DiscountCategoryHostModel;

@Component
public class DiscountCategoryHostConverter extends CommonConverter
		implements BaseConverter<DiscountCategoryHostEntity, DiscountCategoryHostModel> {

	private static final long serialVersionUID = 4719399804116610845L;
	private static final Logger logger = LogManager.getLogger(DiscountCategoryHostConverter.class);

	@Override
	public DiscountCategoryHostEntity modelToEntity(DiscountCategoryHostModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountCategoryHostModel entityToModel(DiscountCategoryHostEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		DiscountCategoryHostModel discountCategoryHostModel = new DiscountCategoryHostModel();
		discountCategoryHostModel = (DiscountCategoryHostModel) Util.transform(modelMapper, e, discountCategoryHostModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return discountCategoryHostModel;
	}

	@Override
	public List<DiscountCategoryHostModel> entityListToModelList(List<DiscountCategoryHostEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<DiscountCategoryHostModel> discountCategoryHostModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			discountCategoryHostModels = new ArrayList<>();
			for(DiscountCategoryHostEntity discountCategoryHostEntity:es) {
				discountCategoryHostModels.add(entityToModel(discountCategoryHostEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return discountCategoryHostModels;
	}

}
