package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.PropertyVsDocumentEntity;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyVsDocumentModel;

@Component
public class PropertyVsDocumentConverter extends CommonConverter implements
BaseConverter<PropertyVsDocumentEntity, PropertyVsDocumentModel> {

	private static final long serialVersionUID = 7886479117519078004L;
	private static final Logger logger = LogManager.getLogger(PropertyVsDocumentConverter.class);
	
	@Override
	public PropertyVsDocumentEntity modelToEntity(PropertyVsDocumentModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsDocumentEntity propertyVsDocumentEntity = new PropertyVsDocumentEntity();
		propertyVsDocumentEntity = (PropertyVsDocumentEntity) Util.transform(modelMapper, m, propertyVsDocumentEntity);
		propertyVsDocumentEntity.setStatus(Status.ACTIVE.ordinal());
		propertyVsDocumentEntity.setCreatedDate(Util.getCurrentDateTime());
		propertyVsDocumentEntity.setDocumentEntity(documentDAO.find(Long.valueOf(m.getDocumentModel().getDocumentId())));
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsDocumentEntity;
		
	}
	
	@Override
	public PropertyVsDocumentModel entityToModel(PropertyVsDocumentEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PropertyVsDocumentModel propertyVsDocumentModel = new PropertyVsDocumentModel();
		propertyVsDocumentModel = (PropertyVsDocumentModel) Util.transform(modelMapper, e, propertyVsDocumentModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return propertyVsDocumentModel;
	}
	
	@Override
	public List<PropertyVsDocumentModel> entityListToModelList(List<PropertyVsDocumentEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PropertyVsDocumentModel> propertyVsDocumentModels = null;
		
		if(!CollectionUtils.isEmpty(es)) {
			propertyVsDocumentModels = new ArrayList<>();
			for(PropertyVsDocumentEntity propertyVsDocumentEntity:es) {
				propertyVsDocumentModels.add(entityToModel(propertyVsDocumentEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return propertyVsDocumentModels;
	}
}
