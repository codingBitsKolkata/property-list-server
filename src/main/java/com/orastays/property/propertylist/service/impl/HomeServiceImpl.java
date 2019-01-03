package com.orastays.property.propertylist.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Accommodation;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.review.BookingVsRatingModel;
import com.orastays.property.propertylist.model.review.UserReviewModel;
import com.orastays.property.propertylist.service.HomeService;

@Service
@Transactional
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

	private static final Logger logger = LogManager.getLogger(HomeServiceImpl.class);

	@Override
	public List<PropertyListViewModel> fetchPropertyByType(String propertyTypeId) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyByType -- START");
		}

		homeValidation.validatePropertyType(propertyTypeId);
		List<PropertyListViewModel> propertyListViewModels = new ArrayList<>();
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
				
				propertyListViewModels.add(findByRating(propertyEntities));
				propertyListViewModels.add(findByPrice(propertyEntities));
				propertyListViewModels.add(findByBooking(propertyEntities));
				propertyListViewModels.add(findByAmmenities(propertyEntities));
			}

		} catch(Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getPropertyByType -- " + Util.errorToString(e));
			}
		}

		if(logger.isInfoEnabled()) {
			logger.info("fetchPropertyByType -- END");
		}
	
		return propertyListViewModels;
	}
	
	private PropertyListViewModel findByRating(List<PropertyEntity> propertyEntities) {
		
		if (logger.isInfoEnabled()) {
			logger.info("findByRating -- START");
		}
		
		PropertyListViewModel propertyListViewModel = null;
		Map<PropertyEntity, Integer> ratingCount = new LinkedHashMap<>();
		for(PropertyEntity propertyEntity : propertyEntities) {
			try {
				UserReviewModel userReviewModel2 = new UserReviewModel();
				userReviewModel2.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
				userReviewModel2.setUserTypeId(String.valueOf(Status.INACTIVE.ordinal()));
				ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("review.server.url") +"fetch-review", userReviewModel2, ResponseModel.class);
				Gson gson = new Gson();
				String jsonString = gson.toJson(responseModel.getResponseBody());
				gson = new Gson();
				Type listType = new TypeToken<List<UserReviewModel>>() {}.getType();
				List<UserReviewModel> userReviewModels = gson.fromJson(jsonString, listType);
				
				if (logger.isInfoEnabled()) {
					logger.info("userReviewModels ==>> "+userReviewModels);
				}
				System.err.println("userReviewModels ==>> "+userReviewModels);
				
				if(Objects.nonNull(userReviewModels) && !CollectionUtils.isEmpty(userReviewModels)) {
					// Calculate Rating
					Map<String, String> ratingTypes = new LinkedHashMap<>();
					for(UserReviewModel userReviewModel : userReviewModels) {
						if(!CollectionUtils.isEmpty(userReviewModel.getBookingVsRatings())) {
							for(BookingVsRatingModel bookingVsRatingModel : userReviewModel.getBookingVsRatings()) {
								if(ratingTypes.isEmpty()) { // First Time
									ratingTypes.put(bookingVsRatingModel.getRatings().getRatingId(), bookingVsRatingModel.getRating());
								} else {
									
									String reviews = ratingTypes.get(bookingVsRatingModel.getRatings().getRatingId());
									if (StringUtils.isBlank(reviews)) { // No Such Rating ID Found
										ratingTypes.put(bookingVsRatingModel.getRatings().getRatingId(), bookingVsRatingModel.getRating());
									} else {
										reviews = String.valueOf(Long.parseLong(reviews) + Long.parseLong(bookingVsRatingModel.getRating()));
										ratingTypes.put(bookingVsRatingModel.getRatings().getRatingId(), reviews);
									}
								}
							}
						} else {
							ratingCount.put(propertyEntity, 0); // Rating
							break;
						}
					}
					
					Integer totalRating = 0;
					for (Map.Entry<String,String> entry : ratingTypes.entrySet()) { 
						totalRating = totalRating + Integer.parseInt(entry.getValue());
					}
					ratingCount.put(propertyEntity, (totalRating / ratingTypes.size())); // Rating
				}
				
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					logger.info("Exception in findByRating -- "+Util.errorToString(e));
				}
			}
		}
		
		PropertyEntity propertyEntity = null;
		RoomEntity roomEntity = null;
		int maxValueInMap = (Collections.max(ratingCount.values()));
        for (Entry<PropertyEntity, Integer> entry : ratingCount.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
            	propertyEntity = entry.getKey();
            	if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
					for(RoomEntity roomEntity2 :propertyEntity.getRoomEntities()) {
						if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
							roomEntity = roomEntity2; // Considering First Room
							break;
						}
					}
            	}
            }
        }
        
        propertyListViewModel = propertyListService.setPropertyListView(propertyEntity, null);
        List<String> prices = priceCalculation(propertyEntity, roomEntity);
        propertyListViewModel.setTotalPrice(prices.get(0));
		//propertyListViewModel.setDiscountedPrice(prices.get(1));
        
		if (logger.isInfoEnabled()) {
			logger.info("findByRating -- END");
		}
		
		return propertyListViewModel;
	}
	
	private PropertyListViewModel findByBooking(List<PropertyEntity> propertyEntities) {
		
		if (logger.isInfoEnabled()) {
			logger.info("findByBooking -- START");
		}
		
		PropertyListViewModel propertyListViewModel = null;
		Map<PropertyEntity, Integer> bookingCount = new LinkedHashMap<>();
		for(PropertyEntity propertyEntity : propertyEntities) {
			try {
				BookingModel bookingModel = new BookingModel();
				bookingModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
				ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("booking.server.url") +"get-property-bookings", bookingModel, ResponseModel.class);
				Gson gson = new Gson();
				String jsonString = gson.toJson(responseModel.getResponseBody());
				gson = new Gson();
				Type listType = new TypeToken<List<BookingModel>>() {}.getType();
				List<BookingModel> bookingModels = gson.fromJson(jsonString, listType);
				
				if (logger.isInfoEnabled()) {
					logger.info("bookingModels ==>> "+bookingModels);
				}
				System.err.println("bookingModels ==>> "+bookingModels);
				
				if (CollectionUtils.isEmpty(bookingModels)) {
					bookingCount.put(propertyEntity, bookingModels.size());
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (logger.isInfoEnabled()) {
					logger.info("Exception in findByBooking -- "+Util.errorToString(e));
				}
			}
		}
		
		PropertyEntity propertyEntity = null;
		RoomEntity roomEntity = null;
		int maxValueInMap = (Collections.max(bookingCount.values()));
        for (Entry<PropertyEntity, Integer> entry : bookingCount.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
            	propertyEntity = entry.getKey();
            	if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
					for(RoomEntity roomEntity2 :propertyEntity.getRoomEntities()) {
						if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
							roomEntity = roomEntity2; // Considering First Room
							break;
						}
					}
            	}
            }
        }
		
        propertyListViewModel = propertyListService.setPropertyListView(propertyEntity, null);
        List<String> prices = priceCalculation(propertyEntity, roomEntity);
        propertyListViewModel.setTotalPrice(prices.get(0));
		//propertyListViewModel.setDiscountedPrice(prices.get(1));
		
		if (logger.isInfoEnabled()) {
			logger.info("findByBooking -- END");
		}
		
		return propertyListViewModel;
	}
	
	private PropertyListViewModel findByAmmenities(List<PropertyEntity> propertyEntities) {
			
		if (logger.isInfoEnabled()) {
			logger.info("findByAmmenities -- START");
		}
		
		PropertyListViewModel propertyListViewModel = null;
		Map<RoomEntity, Integer> ammenitiesCount = new LinkedHashMap<>();
		for(PropertyEntity propertyEntity : propertyEntities) {
			if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
				for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
					if(Objects.nonNull(roomEntity)) {
						if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
							ammenitiesCount.put(roomEntity, roomEntity.getRoomVsAmenitiesEntities().size());
						}
					}
				}
			}
		}
		
		PropertyEntity propertyEntity = null;
		RoomEntity roomEntity = null;
		int maxValueInMap = (Collections.max(ammenitiesCount.values()));
        for (Entry<RoomEntity, Integer> entry : ammenitiesCount.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
            	propertyEntity = entry.getKey().getPropertyEntity();
            	roomEntity = entry.getKey();
            }
        }
        
        propertyListViewModel = propertyListService.setPropertyListView(propertyEntity, null);
        List<String> prices = priceCalculation(propertyEntity, roomEntity);
        propertyListViewModel.setTotalPrice(prices.get(0));
		//propertyListViewModel.setDiscountedPrice(prices.get(1));
		
		if (logger.isInfoEnabled()) {
			logger.info("findByAmmenities -- END");
		}
		
		return propertyListViewModel;
	}
	
	
	private PropertyListViewModel findByPrice(List<PropertyEntity> propertyEntities) {
		
		if (logger.isInfoEnabled()) {
			logger.info("findByPrice -- START");
		}
		
		PropertyListViewModel propertyListViewModel = new PropertyListViewModel();
		Map<RoomEntity, Double> priceCount = new LinkedHashMap<>();
		for(PropertyEntity propertyEntity : propertyEntities) {
			if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
				for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
					if(Objects.nonNull(roomEntity)) {
						Double price = 0.0D;
						Double totalPrice = 0.0D;
						if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.ACTIVE.ordinal()){   //Long term
							
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								//Shared Month price 
								price = (Double.parseDouble(roomEntity.getSharedBedPricePerMonth())/30);
							} else { //private
								 //Private Month Price
								price = (Double.parseDouble(roomEntity.getRoomPricePerMonth())/30);
							}
					
						} else {   //both & Short term
							
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								//Shared Night price 
								price = Double.parseDouble(roomEntity.getSharedBedPricePerNight());
							} else {   //private
								 //Private Night Price
								price = Double.parseDouble(roomEntity.getRoomPricePerNight());
							}
						}
						System.out.println("price ==>> "+price);
						System.err.println("roomEntity.getOraPercentage() ==>> "+roomEntity.getOraPercentage());
						System.out.println("totalPrice before ==>> "+totalPrice);
						totalPrice = totalPrice + price + (Double.parseDouble(roomEntity.getOraPercentage()) * price / 100);
						System.err.println("totalPrice after including OraPercentage ==>> "+totalPrice); 
						
						priceCount.put(roomEntity, totalPrice);
					}
				}
			}
		}
		
		PropertyEntity propertyEntity = null;
		RoomEntity roomEntity = null;
		Double maxValueInMap = (Collections.max(priceCount.values()));
        for (Entry<RoomEntity, Double> entry : priceCount.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
            	propertyEntity = entry.getKey().getPropertyEntity();
            	roomEntity = entry.getKey();
            }
        }
		
        propertyListViewModel = propertyListService.setPropertyListView(propertyEntity, null);
        List<String> prices = priceCalculation(propertyEntity, roomEntity);
        propertyListViewModel.setTotalPrice(prices.get(0));
		//propertyListViewModel.setDiscountedPrice(prices.get(1));
        
		if (logger.isInfoEnabled()) {
			logger.info("findByPrice -- END");
		}
		
		return propertyListViewModel;
	}
	
	private List<String> priceCalculation(PropertyEntity propertyEntity, RoomEntity roomEntity) {
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculation -- START");
		}
		
		List<String> prices = new ArrayList<>();
		Double price = 0.0D;
		Double totalPrice = 0.0D;
		Double discountedPrice = 0.0D;
		if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.ACTIVE.ordinal()){   //Long term
			
			if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
				//Shared Month price 
				price = (Double.parseDouble(roomEntity.getSharedBedPricePerMonth())/30);
			} else { //private
				 //Private Month Price
				price = (Double.parseDouble(roomEntity.getRoomPricePerMonth())/30);
			}
	
		} else {   //both & Short term
			
			if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
				//Shared Night price 
				price = Double.parseDouble(roomEntity.getSharedBedPricePerNight());
			} else {   //private
				 //Private Night Price
				price = Double.parseDouble(roomEntity.getRoomPricePerNight());
			}
		}
		
		System.out.println("price ==>> "+price);
		System.err.println("roomEntity.getOraPercentage() ==>> "+roomEntity.getOraPercentage());
		System.out.println("totalPrice before ==>> "+totalPrice);
		totalPrice = totalPrice + price + (Double.parseDouble(roomEntity.getOraPercentage()) * price / 100);
		System.err.println("totalPrice after including OraPercentage ==>> "+totalPrice); 
		
		prices.add(String.valueOf(Math.round(totalPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(discountedPrice * 100D) / 100D));
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculation -- END");
		}
		
		return prices;
	}
}