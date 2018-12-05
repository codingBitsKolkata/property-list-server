package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PGCategorySexEntity;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PGCategorySexModel;

@Component
public class PGCategorySexConverter extends CommonConverter
		implements BaseConverter<PGCategorySexEntity, PGCategorySexModel> {

	private static final long serialVersionUID = 4517963113550272759L;
	private static final Logger logger = LogManager.getLogger(PGCategorySexConverter.class);

	@Override
	public PGCategorySexEntity modelToEntity(PGCategorySexModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PGCategorySexModel entityToModel(PGCategorySexEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PGCategorySexModel pgCategorySexModel = new PGCategorySexModel();
		pgCategorySexModel = (PGCategorySexModel) Util.transform(modelMapper, e, pgCategorySexModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return pgCategorySexModel;
	}

	@Override
	public List<PGCategorySexModel> entityListToModelList(List<PGCategorySexEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PGCategorySexModel> pgCategorySexModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			pgCategorySexModels = new ArrayList<>();
			for(PGCategorySexEntity pgCategorySexEntity:es) {
				pgCategorySexModels.add(entityToModel(pgCategorySexEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return pgCategorySexModels;
	}

}
