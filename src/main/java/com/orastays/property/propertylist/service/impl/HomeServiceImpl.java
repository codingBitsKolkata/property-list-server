package com.orastays.property.propertylist.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<PropertyListViewModel> fetchPropertyByType(PropertyTypeModel propertyTypeModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyByType -- START");
		}

		homeValidation.validatePropertyType(propertyTypeModel);
		List<PropertyListViewModel> propertyListViewModelList = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));

			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".PropertyEntity", outerMap1);

			Map<String, String> innerMap2 = new LinkedHashMap<>();
			innerMap2.put("propertyTypeId", propertyTypeModel.getPropertyTypeId());

			Map<String, Map<String, String>> outerMap2 = new LinkedHashMap<>();
			outerMap2.put("eq", innerMap2);

			alliasMap.put("propertyTypeEntity", outerMap2);

			/*List<PropertyEntity> propertyEntities = propertyDAO.fetchListBySubCiteria(alliasMap);
			if (!CollectionUtils.isEmpty(propertyEntities)) {
				Double price = 0.0D;
				for(PropertyEntity propertyEntity : propertyEntities) {
					if(Objects.nonNull(propertyEntity) && !CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
						for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
							if(!CollectionUtils.isEmpty(roomEntity.getRoomVsPriceEntities())) {
								
								if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.INACTIVE.ordinal()) {   //short term
							
									if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) { //shared
										//Shared night price 
										RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
								                .filter(x -> PriceType.SHARED_BED_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
										if(Objects.nonNull(roomVsPriceEntity)) {
											price = Double.parseDouble(roomVsPriceEntity.getValue());
										}
									} else { //private
										 //Private night Price
										RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
								                .filter(x -> PriceType.ROOM_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
										if(Objects.nonNull(roomVsPriceEntity)) {
											price = Double.parseDouble(roomVsPriceEntity.getValue());
										}
									}
							
								} else {   //both & long term
									
									if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) {   //shared
										//Shared Month price 
										RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
								                .filter(x -> PriceType.SHARED_BED_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
										if(Objects.nonNull(roomVsPriceEntity)) {
											price =(Double.parseDouble(roomVsPriceEntity.getValue())/30);
										}
									} else {   //private
										 //Private Month Price
										RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
								                .filter(x -> PriceType.ROOM_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
										if(Objects.nonNull(roomVsPriceEntity)) {
											price = (Double.parseDouble(roomVsPriceEntity.getValue())/30);
										}
									}
								}
							}
						}
					}
				}
				
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
								
								 * for (RoomVsPriceEntity roomVsPriceEntity : roomVsPriceEntites) {
								 * roomPriceForMeal = Long.parseLong(roomVsPriceEntity.getPrice()); }
								 

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

				}*/

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
			//}

		}catch(Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getPropertyByType -- " + Util.errorToString(e));
			}
		}

	if(logger.isInfoEnabled())
	{
		logger.info("fetchPropertyByType -- END");
	}

	return propertyListViewModelList;
}
		}