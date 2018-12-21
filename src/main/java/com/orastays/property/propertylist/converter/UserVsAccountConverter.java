/**
 * @author Avirup
 */
package com.orastays.property.propertylist.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.UserVsAccountEntity;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.UserVsAccountModel;

@Component
public class UserVsAccountConverter extends CommonConverter
		implements BaseConverter<UserVsAccountEntity, UserVsAccountModel> {

	private static final long serialVersionUID = 9085582036182090828L;
	private static final Logger logger = LogManager.getLogger(UserVsAccountConverter.class);

	@Override
	public UserVsAccountEntity modelToEntity(UserVsAccountModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		UserVsAccountEntity userVsAccountEntity = new UserVsAccountEntity();
		userVsAccountEntity = (UserVsAccountEntity) Util.transform(modelMapper, m, userVsAccountEntity);
		userVsAccountEntity.setStatus(Status.ACTIVE.ordinal());
		userVsAccountEntity.setCreatedDate(Util.getCurrentDateTime());

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}

		return userVsAccountEntity;
	}

	@Override
	public UserVsAccountModel entityToModel(UserVsAccountEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		UserVsAccountModel userVsAccountModel = null;
		if (Objects.nonNull(e)) {
			userVsAccountModel = new UserVsAccountModel();
			userVsAccountModel = (UserVsAccountModel) Util.transform(modelMapper, e, userVsAccountModel);
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return userVsAccountModel;
	}

	@Override
	public List<UserVsAccountModel> entityListToModelList(List<UserVsAccountEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<UserVsAccountModel> userVsAccountModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			userVsAccountModels = new ArrayList<>();
			for (UserVsAccountEntity userVsAccountEntity : es) {
				userVsAccountModels.add(entityToModel(userVsAccountEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return userVsAccountModels;
	}
}