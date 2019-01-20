package com.orastays.property.propertylist.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.PropertyListConstant;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.FilterRoomModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.user.UserModel;
import com.orastays.property.propertylist.service.PropertyListService;

@Service
@Transactional
public class PropertyListServiceImpl extends BaseServiceImpl implements PropertyListService {

	private static final Logger logger = LogManager.getLogger(PropertyListServiceImpl.class);
	
	@Override
	public List<PropertyListViewModel> fetchProperties(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- START");
		}
		
		UserModel userModel = propertyListValidation.validateFetchProperties(filterCiteriaModel);
		List<PropertyListViewModel> propertyListViewModels = new ArrayList<PropertyListViewModel>();
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".PropertyEntity", outerMap1);
			
			Map<String, String> innerMap2 = new LinkedHashMap<>();
			innerMap2.put("propertyTypeId", filterCiteriaModel.getPropertyTypeId());

			Map<String, Map<String, String>> outerMap2 = new LinkedHashMap<>();
			outerMap2.put("eq", innerMap2);

			alliasMap.put("propertyTypeEntity", outerMap2);
			
			List<PropertyEntity> propertyEntities = propertyDAO.fetchListBySubCiteria(alliasMap);
			if(!CollectionUtils.isEmpty(propertyEntities)) {
				
				List<PropertyEntity> filteredPropertyEntitiesByRadius = propertyDAO.selectByRadius(filterCiteriaModel);
				List<PropertyEntity> filteredPropertyEntitiesByRadius2 = filteredPropertyEntitiesByRadius.stream().collect(Collectors.toList());
				List<PropertyEntity> propertyEntities2 = propertyEntities.stream().collect(Collectors.toList());
				AtomicBoolean isContinueRating = new AtomicBoolean(false);
				propertyEntities2.parallelStream().forEach(propertyEntity -> {
					//for(PropertyEntity propertyEntity : propertyEntities) {
					isContinueRating.set(false);
					
					// Filter By Property Start Date and End Date
					if(propertyListHelper.filterByPropertyDate(propertyEntity, filterCiteriaModel)) {
						
						// Filter by location // Mandatory
						if(propertyListHelper.filterByLocation(propertyEntity, filteredPropertyEntitiesByRadius2)) {
							// Filter by checkInDate // Mandatory
							// Filter by checkOutDate // Mandatory
							// Filter by roomModels // Mandatory
							Map<Boolean, Map<String, FilterRoomModel>> filterResult = propertyListHelper.filterBycheckInDate(propertyEntity, filterCiteriaModel);
							if (filterResult.containsKey(true)) {
								
								Map<String, FilterRoomModel> filteredRooms = filterResult.get(true);
								System.err.println("filteredRooms ==>> "+filteredRooms);
								// Filter By Rating
								if (!CollectionUtils.isEmpty(filterCiteriaModel.getRatings())) {
									if (!propertyListHelper.filterByRating(propertyEntity, filterCiteriaModel)) {
										isContinueRating.set(true);
									}
								}
								
								// Filter by amenitiesModels
								if (!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels())) {
									if (!propertyListHelper.filterByAmmenities(propertyEntity, filterCiteriaModel)) {
										isContinueRating.set(true);
									}
								}
								
								
								// Filter by budgets
								if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets())) {
									if (!propertyListHelper.filterByBudget(propertyEntity, filterCiteriaModel, filteredRooms)) {
										isContinueRating.set(true);
									}
								}
								
								
								// Filter by popularLocations
								if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations())) {
									if (!propertyListHelper.filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
										isContinueRating.set(true);
									}
								}
								
								
								// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
								if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels())) {
									if (!propertyListHelper.filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
										isContinueRating.set(true);
									}
								}
								
								
								// Filter by pgCategorySexModels // Male/Female
								if(!StringUtils.isBlank(filterCiteriaModel.getPgCategorySex())) {
									if (!propertyListHelper.filterBySex(propertyEntity, filterCiteriaModel)) {
										isContinueRating.set(true);
									}
								}
								
								if(!isContinueRating.get()) {
									PropertyListViewModel propertyListViewModel = propertyListHelper.setPropertyListView(propertyEntity, filterCiteriaModel, filteredRooms);
									if(Objects.nonNull(userModel)) {
										setBookMark(propertyListViewModel, userModel);
									}
									propertyListViewModels.add(propertyListViewModel);
								}
							} 
						} 
					}
				});
				
				// TODO Sorting, Pagination if any
			}
								
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchProperty -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- END");
		}
		
		return propertyListViewModels;
	}
	
	@Override
	public PropertyModel fetchPropertyDetails(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setBookMark(PropertyListViewModel propertyListViewModel, UserModel userModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("setBookMark -- START");
		}
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put(PropertyListConstant.STATUS, String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("userId", userModel.getUserId());

			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".WishlistEntity", outerMap1);

			Map<String, String> innerMap2 = new LinkedHashMap<>();
			innerMap2.put("propertyId", propertyListViewModel.getPropertyId());

			Map<String, Map<String, String>> outerMap2 = new LinkedHashMap<>();
			outerMap2.put("eq", innerMap2);

			alliasMap.put("propertyEntity", outerMap2);
			if(Objects.nonNull(wishlistDAO.fetchObjectBySubCiteria(alliasMap))) {
				propertyListViewModel.setIsBookmark(true);
			}
			
		} catch(Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getPropertyByType -- " + Util.errorToString(e));
			}
		}
		
		if(logger.isInfoEnabled()) {
			logger.info("setBookMark -- END");
		}
	}
	
	@Override
	public BookingVsRoomModel roomDetailsByOraRoomName(String oraRoomName) {

		if (logger.isInfoEnabled()) {
			logger.info("roomDetailsByOraRoomName -- START");
		}
		
		BookingVsRoomModel bookingVsRoomModel = new BookingVsRoomModel();
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("oraRoomName", oraRoomName);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".RoomEntity", outerMap1);
			
			RoomEntity roomEntity = roomDAO.fetchObjectBySubCiteria(alliasMap);
			if(Objects.nonNull(roomEntity)) {
				bookingVsRoomModel.setTotalNumOfSharedBed(roomEntity.getNumOfBed());
				bookingVsRoomModel.setTotalNumOfSharedCot(roomEntity.getNumOfCot());
			}
			
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in roomDetailsByOraRoomName -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("roomDetailsByOraRoomName -- END");
		}
		
		return bookingVsRoomModel;
	}

	@Override
	public PropertyModel fetchPropertyById(String propertyId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyById -- START");
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyById -- END");
		}
		
		return propertyConverter.entityToModel(propertyDAO.find(Long.valueOf(propertyId)));
	}

	@Override
	public Object budgets() {

		if (logger.isInfoEnabled()) {
			logger.info("budgets -- START");
		}
		
		Map<String, String> budgets = new LinkedHashMap<>();
		budgets.put(messageUtil.getBundle("budget.key1"), messageUtil.getBundle("budget.value1"));
		budgets.put(messageUtil.getBundle("budget.key2"), messageUtil.getBundle("budget.value2"));
		budgets.put(messageUtil.getBundle("budget.key3"), messageUtil.getBundle("budget.value3"));
		budgets.put(messageUtil.getBundle("budget.key4"), messageUtil.getBundle("budget.value4"));
		budgets.put(messageUtil.getBundle("budget.key5"), messageUtil.getBundle("budget.value5"));
		budgets.put(messageUtil.getBundle("budget.key6"), messageUtil.getBundle("budget.value6"));
		
		if (logger.isInfoEnabled()) {
			logger.info("budgets -- END");
		}
		
		return budgets;
	}

	@Override
	public Object ratings() {

		if (logger.isInfoEnabled()) {
			logger.info("ratings -- START");
		}
		
		Map<String, String> ratings = new LinkedHashMap<>();
		ratings.put(messageUtil.getBundle("rating.key1"), messageUtil.getBundle("rating.value1"));
		ratings.put(messageUtil.getBundle("rating.key2"), messageUtil.getBundle("rating.value2"));
		ratings.put(messageUtil.getBundle("rating.key3"), messageUtil.getBundle("rating.value3"));
		
		if (logger.isInfoEnabled()) {
			logger.info("ratings -- END");
		}
		
		return ratings;
	}
	
	@Override
	public UserModel getUserDetails(String userId) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel userModel = null;
		try {
			ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("auth.server.url") + "fetch-user-by-id?userId=" + userId, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			userModel = gson.fromJson(jsonString, UserModel.class);
			if (Objects.isNull(userModel)) {
				exceptions.put(messageUtil.getBundle("user.invalid.code"), new Exception(messageUtil.getBundle("user.invalid.message")));
			}

			if (logger.isInfoEnabled()) {
				logger.info("userModel ==>> " + userModel);
			}
			
			System.out.println("userModel ==>> " + userModel);
			
		} catch (Exception e) {
			e.printStackTrace();
			// Disabled the below line to pass the Token Validation
			exceptions.put(messageUtil.getBundle("user.invalid.code"), new Exception(messageUtil.getBundle("user.invalid.message")));
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- END");
		}

		return userModel;
	}

}
