package com.orastays.property.propertylist.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.orastays.property.propertylist.entity.AmenitiesEntity;
import com.orastays.property.propertylist.entity.PriceTypeEntity;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.entity.RoomVsMealEntity;
import com.orastays.property.propertylist.entity.RoomVsPriceEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyTypeModel;
import com.orastays.property.propertylist.service.HomeService;

@Service
@Transactional
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

	private static final Logger logger = LogManager.getLogger(HomeServiceImpl.class);

	@Override
	public List<PropertyListViewModel> getProperty(PropertyTypeModel propertyType) {

		if (logger.isInfoEnabled()) {
			logger.info("getProperty -- START");
		}

		try {
			propertyListValidation.validatePropertyType(propertyType);
		} catch (FormExceptions e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String propertyTypeId = propertyType.getPropertyTypeId();
		List<PropertyListViewModel> propertyListViewModelList = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));

			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".PropertyEntity", outerMap1);

			Map<String, String> innerMap2 = new LinkedHashMap<>();
			innerMap2.put("propertyTypeId", propertyTypeId);

			Map<String, Map<String, String>> outerMap2 = new LinkedHashMap<>();
			outerMap2.put("eq", innerMap2);

			alliasMap.put("propertyTypeEntity", outerMap2);

			List<PropertyEntity> propertyEntities = propertyDAO.fetchListBySubCiteria(alliasMap);

			if (!CollectionUtils.isEmpty(propertyEntities)) {
				Long minPrice = 99999999L;
				Long roomPriceForMeal = 0L;
				Long roomPriceForMaxAmenties = 0L;
				String propAddressForMinPrice = "";
				String propAddressForMealService = "";
				String propAddressForMaxAmenities = "";
				List<RoomEntity> roomEntites = null;
				List<RoomVsPriceEntity> roomVsPriceEntites = null;
				List<AmenitiesEntity> amentitieEntityList = new ArrayList<>();
				List<PriceTypeEntity> priceEntityList = new ArrayList<>();
				List<RoomVsMealEntity> roomVsMealEntities = null;
				List<RoomVsAmenitiesEntity> roomVsAmentiesEntities = null;
				PropertyEntity propertyWithMinPrice = new PropertyEntity();
				PropertyEntity propertyWithMealService = new PropertyEntity();
				PropertyEntity propertyWithMaxAmenities = new PropertyEntity();
				RoomEntity roomEntityWithMinPrice = new RoomEntity();
				RoomEntity roomEntityWithMeal = new RoomEntity();
				RoomEntity roomEntityWithMaxAmenities = new RoomEntity();
				PropertyListViewModel propertyListViewModel = new PropertyListViewModel();
				Integer noOfAmenities = 0;

				for (PropertyEntity propEntity : propertyEntities) {
					roomEntites = propEntity.getRoomEntities();
					if (null != roomEntites) {
						for (RoomEntity roomEntity : roomEntites) {
							roomVsPriceEntites = roomEntity.getRoomVsPriceEntities();
							if (null != roomVsPriceEntites) {
								for (RoomVsPriceEntity roomVsPriceEntity : roomVsPriceEntites) {
									if (Long.parseLong(roomVsPriceEntity.getValue()) < minPrice) {
										minPrice = Long.parseLong(roomVsPriceEntity.getValue());
										propertyWithMinPrice = propEntity;
										roomEntityWithMinPrice = roomEntity;
									}
								}
							}

							// Property with Meal Service
							roomVsMealEntities = roomEntity.getRoomVsMealEntities();
							if (null != roomVsMealEntities) {
								propertyWithMealService = propEntity;
								roomEntityWithMeal = roomEntity;
								/*
								 * for (RoomVsPriceEntity roomVsPriceEntity : roomVsPriceEntites) {
								 * roomPriceForMeal = Long.parseLong(roomVsPriceEntity.getPrice()); }
								 */

							}
							// Property with Max Amenities
							roomVsAmentiesEntities = roomEntity.getRoomVsAmenitiesEntities();
							if (null != roomVsAmentiesEntities) {
								if (noOfAmenities < roomVsAmentiesEntities.size()) {
									noOfAmenities = roomVsAmentiesEntities.size();
									roomEntityWithMaxAmenities = roomEntity;
									propertyWithMaxAmenities = propEntity;
								}
							}
						}
					}

				}

				// Setting model for min price property
/*				List<RoomVsAmenitiesEntity> roomVsAmmenitiesEntities = roomEntityWithMinPrice
						.getRoomVsAmenitiesEntities();
				for (RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomVsAmmenitiesEntities) {
					amentitieEntityList.add(roomVsAmenitiesEntity.getAmenitiesEntity());
				}
				propertyListViewModel.setAddress(propertyWithMinPrice.getAddress());
				propertyListViewModel.setAmenitiesModel(amenitiesConverter.entityListToModelList(amentitieEntityList));
				propertyListViewModel.setTotalPrice(String.valueOf(minPrice));
				propertyListViewModel.setCoverImageURL("");

				// Setting model for meal service property
				roomVsAmmenitiesEntities = roomEntityWithMeal.getRoomVsAmenitiesEntities();
				for (RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomVsAmmenitiesEntities) {
					amentitieEntityList.add(roomVsAmenitiesEntity.getAmenitiesEntity());
				}
				propertyListViewModel.setAddress(propertyWithMealService.getAddress());
				propertyListViewModel.setAmenitiesModels(amenitiesConverter.entityListToModelList(amentitieEntityList));
				propertyListViewModel.setTotalPrice(String.valueOf(roomPriceForMeal));
				propertyListViewModel.setCoverImageURL("");

				// Setting model for Max Amenites property
				roomVsAmmenitiesEntities = roomEntityWithMaxAmenities.getRoomVsAmenitiesEntities();
				for (RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomVsAmmenitiesEntities) {
					amentitieEntityList.add(roomVsAmenitiesEntity.getAmenitiesEntity());
				}
				roomVsPriceEntites = roomEntityWithMaxAmenities.getRoomVsPriceEntities();
				for (RoomVsPriceEntity roomVsPriceEntity : roomVsPriceEntites) {
					priceEntityList.add(roomVsPriceEntity.getPriceTypeEntity());
					for (PriceTypeEntity priceTypeEntity : priceEntityList) {
						if ("DC".equals(priceTypeEntity.getPriceTypeName())) {
							propertyListViewModel.setDiscountedPrice(priceTypeEntity.getP);
						}
					}
				}
				propertyListViewModel.setAddress(propertyWithMaxAmenities.getAddress());
				propertyListViewModel.setAmenitiesModels(amenitiesConverter.entityListToModelList(amentitieEntityList));
				propertyListViewModel.setCoverImageURL("");
				propertyListViewModel.setTotalPrice(totalPrice);*/
			}

		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getProperty -- " + Util.errorToString(e));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("getProperty -- END");
		}

		return propertyListViewModelList;

	}
}