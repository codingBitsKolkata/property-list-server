package com.orastays.property.propertylist.service.impl;

import java.awt.BufferCapabilities.FlipContents;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.property.propertylist.entity.OfferEntity;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.PropertyVsPriceDropEntity;
import com.orastays.property.propertylist.entity.PropertyVsSpaceRuleEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.entity.RoomVsMealEntity;
import com.orastays.property.propertylist.entity.RoomVsOfferEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Accommodation;
import com.orastays.property.propertylist.helper.MealPriceCategory;
import com.orastays.property.propertylist.helper.PropertyListConstant;
import com.orastays.property.propertylist.helper.Sex;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.UserType;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.AmenitiesModel;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.RoomModel;
import com.orastays.property.propertylist.model.SpaceRuleModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.review.BookingVsRatingModel;
import com.orastays.property.propertylist.model.review.UserReviewModel;
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
		
		propertyListValidation.validateFetchProperties(filterCiteriaModel);
		List<PropertyListViewModel> propertyListViewModels = new ArrayList<PropertyListViewModel>();
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".PropertyEntity", outerMap1);
			
			List<PropertyEntity> propertyEntities2 = propertyDAO.fetchListBySubCiteria(alliasMap);
			List<PropertyEntity> propertyEntities = new ArrayList<PropertyEntity>();
			propertyEntities = propertyEntities2;
			System.err.println("propertyEntities ==>> "+propertyEntities);
			if(!CollectionUtils.isEmpty(propertyEntities)) {
				AtomicBoolean isContinueRating = new AtomicBoolean(false);
				propertyEntities.parallelStream().forEach(propertyEntity -> {
					//for(PropertyEntity propertyEntity : propertyEntities) {
					isContinueRating.set(false);
					
					// Filter By Property Start Date and End Date
					if(filterByPropertyDate(propertyEntity, filterCiteriaModel)) {
						
						// Filter by propertyTypeId // Mandatory
						if (StringUtils.equals(filterCiteriaModel.getPropertyTypeId(), String.valueOf(propertyEntity.getPropertyTypeEntity().getPropertyTypeId()))) {
							
							// Filter by location // Mandatory
							if(filterByLocation(propertyEntity, filterCiteriaModel)) {
								// Filter by checkInDate // Mandatory
								// Filter by checkOutDate // Mandatory
								// Filter by roomModels // Mandatory
								if(filterBycheckInDate(propertyEntity, filterCiteriaModel)) {
									
									// Filter By Rating
									if (!CollectionUtils.isEmpty(filterCiteriaModel.getRatings())) {
										if (!filterByRating(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									// Filter by amenitiesModels
									if (!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels())) {
										if (!filterByAmmenities(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									
									// Filter by budgets
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets())) {
										if (!filterByBudget(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									
									// Filter by popularLocations
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations())) {
										if (!filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									
									// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels())) {
										if (!filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									
									// Filter by pgCategorySexModels // Male/Female
									if(!StringUtils.isBlank(filterCiteriaModel.getPgCategorySex())) {
										if (!filterBySex(propertyEntity, filterCiteriaModel)) {
											isContinueRating.set(true);
										}
									}
									
									if(!isContinueRating.get())
										propertyListViewModels.add(setPropertyListView(propertyEntity, filterCiteriaModel));
									
								} 
							} 
						} 
					}
				});
				
				// TODO Sorting if any
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
	public PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("setPropertyListView -- START");
		}
		
		PropertyListViewModel propertyListViewModel = new PropertyListViewModel();
		propertyListViewModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
		propertyListViewModel.setOraName(propertyEntity.getOraname());
		propertyListViewModel.setAddress(propertyEntity.getAddress());
		propertyListViewModel.setLatitude(propertyEntity.getLatitude());
		propertyListViewModel.setLongitude(propertyEntity.getLongitude());
		propertyListViewModel.setCoverImageURL(propertyEntity.getCoverImageUrl());
		
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
				if(Objects.nonNull(roomEntity)) {
					if(StringUtils.equals(roomEntity.getRoomStandard(), PropertyListConstant.ROOM_STANDARD_PREMIUM)) {
						propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_PREMIUM);
						break;
					} else if(StringUtils.equals(roomEntity.getRoomStandard(), PropertyListConstant.ROOM_STANDARD_EXPRESS)) {
						propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_EXPRESS);
					} else {
						propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_NORMAL);
					}
				}
			}
		} else {
			propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_NORMAL);
		}
		
		propertyListViewModel.setRating(getRatingAndReview(propertyEntity).get(0));
		propertyListViewModel.setReviewCount(getRatingAndReview(propertyEntity).get(1));
		
		if(Double.parseDouble(propertyListViewModel.getRating()) >= Double.parseDouble(messageUtil.getBundle("rating.key1"))) {
			propertyListViewModel.setRatingText(messageUtil.getBundle("rating.value1"));
		} else if(Double.parseDouble(propertyListViewModel.getRating()) >= Double.parseDouble(messageUtil.getBundle("rating.key2"))) {
			propertyListViewModel.setRatingText(messageUtil.getBundle("rating.value2"));
		} else {
			propertyListViewModel.setRatingText(messageUtil.getBundle("rating.value3"));
		}
		
		List<SpaceRuleModel> spaceRuleModels = null;
		if(!CollectionUtils.isEmpty(propertyEntity.getPropertyVsSpaceRuleEntities())) {
			spaceRuleModels = new ArrayList<>();
			for(PropertyVsSpaceRuleEntity propertyVsSpaceRuleEntity : propertyEntity.getPropertyVsSpaceRuleEntities()) {
				spaceRuleModels.add(spaceRuleConverter.entityToModel(propertyVsSpaceRuleEntity.getSpaceRuleEntity()));
			}
		} else {
			propertyListViewModel.setSpaceRuleModels(spaceRuleModels);
		}
		propertyListViewModel.setSpaceRuleModels(spaceRuleModels);
		
		propertyListViewModel.setPgCategorySex(propertyEntity.getSexCategory());
		
		// Price Section
		if(Objects.nonNull(filterCiteriaModel)) {
			List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel);
			propertyListViewModel.setTotalPrice(prices.get(0));
			propertyListViewModel.setDiscountedPrice(prices.get(1));
		}
		
		// Meal Section
		propertyListViewModel.setMealFlag(false);
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(Objects.nonNull(roomEntity)) {
					if(!CollectionUtils.isEmpty(roomEntity.getRoomVsMealEntities())) {
						for(RoomVsMealEntity roomVsMealEntity : roomEntity.getRoomVsMealEntities()) {
							if((StringUtils.equals(roomVsMealEntity.getMealPriceCategorySunday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategoryMonday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategoryTuesday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategoryWednesday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategoryThursday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategoryFriday(), MealPriceCategory.COMPLIMENTARY.name()))
									|| (StringUtils.equals(roomVsMealEntity.getMealPriceCategorySaturday(), MealPriceCategory.COMPLIMENTARY.name()))) {
									propertyListViewModel.setMealFlag(true);
									break;
							}
						}
					}
				}
			}
		}
		
		Set<AmenitiesModel> amenitiesModels = new LinkedHashSet<>();
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
				if(Objects.nonNull(roomEntity)) {
					if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
						for(RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomEntity.getRoomVsAmenitiesEntities()) {
							amenitiesModels.add(amenitiesConverter.entityToModel(roomVsAmenitiesEntity.getAmenitiesEntity()));
						}
					}
				}
			}
		}
		
		propertyListViewModel.setAmenitiesModels(amenitiesModels);
		
		if (logger.isInfoEnabled()) {
			logger.info("setPropertyListView -- END");
		}
		
		return propertyListViewModel;
	}
	
	private List<String> priceCalculation(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculation -- START");
		}
		
		List<String> prices = new ArrayList<>();
		int numOfDays = Util.getDayDiff(filterCiteriaModel.getCheckInDate(), filterCiteriaModel.getCheckOutDate());
		Double price = 0.0D;
		Double totalPrice = 0.0D;
		Double discountedPrice = 0.0D;
		Double offerPrice = 0.0D;
		Set<OfferEntity> offerEntities = new HashSet<>();
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			System.err.println("propertyEntity.getRoomEntities().size() ==>> "+propertyEntity.getRoomEntities().size());
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				System.err.println("roomEntity ==>> "+roomEntity);
				if(Objects.nonNull(roomEntity) && roomEntity.getStatus() == Status.ACTIVE.ordinal()) {
					
					// Price calculation
					if (numOfDays >= 30 ) {
							
						if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.INACTIVE.ordinal()) {   //short term (ID = 2)
					
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								
								//Shared night price 
								price = Double.parseDouble(roomEntity.getSharedBedPricePerNight());
							} else { //private
								 //Private night Price
								price = Double.parseDouble(roomEntity.getRoomPricePerNight());
							}
					
						} else {   //both & long term
							
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								//Shared Month price 
								price = (Double.parseDouble(roomEntity.getSharedBedPricePerMonth())/30);
							} else {   //private
								 //Private Month Price
								price = (Double.parseDouble(roomEntity.getRoomPricePerMonth())/30);
							}
						}
					} else {
						
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
					}
					
					System.out.println("price ==>> "+price);
					System.err.println("roomEntity.getOraPercentage() ==>> "+roomEntity.getOraPercentage());
					System.out.println("totalPrice before ==>> "+totalPrice);
					totalPrice = totalPrice + price + (Double.parseDouble(roomEntity.getOraPercentage()) * price / 100);
					System.err.println("totalPrice after including OraPercentage ==>> "+totalPrice); 
					
					// Discount Section
					Double hostDiscount = 0.0D;
					Double oraDiscount = 0.0D;
					Double priceDropDiscount = 0.0D;
					
					// Check Pricedrop if any
					if(Util.getDateDiff1(filterCiteriaModel.getCheckInDate()) == 0 && numOfDays == 1) { // Current Date
						if(!CollectionUtils.isEmpty(propertyEntity.getPropertyVsPriceDropEntities())) { // Price Drop Present
							int hourDifference = Util.getMinuteDiff(Util.getCurrentDate() + " " +propertyEntity.getCheckinTime()) / 60;
							for(int i = 0; i< propertyEntity.getPropertyVsPriceDropEntities().size(); i++) {
								PropertyVsPriceDropEntity propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i);
								if(hourDifference <= Integer.parseInt(propertyVsPriceDropEntity.getPriceDropEntity().getAfterTime())) {
									if(i == 0) { // First Condition
										priceDropDiscount = Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
										break;
									} else {
										propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i -1);
										priceDropDiscount = Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
										break;
									}
								}
							}
						}
					} else {
						// Host Discount if any
						if (numOfDays >= 7 && numOfDays < 30) { // Weekly
							
							hostDiscount = Double.parseDouble(roomEntity.getHostDiscountWeekly()) * price / 100;
							
						} else if(numOfDays >= 30) { // Monthly
							
							if (!StringUtils.isBlank(roomEntity.getHostDiscountMonthly())) { // Check if monthly present
								hostDiscount = Double.parseDouble(roomEntity.getHostDiscountMonthly()) * price / 100;
							} else if (!StringUtils.isBlank(roomEntity.getHostDiscountWeekly())) { // otherwise calculate with weekly
								hostDiscount = Double.parseDouble(roomEntity.getHostDiscountWeekly()) * price / 100;
							}
						}
						
						// Room Vs ORA Discount
						// Percentage
						if (!StringUtils.isBlank(roomEntity.getOraDiscountPercentage())) {
							oraDiscount = Double.parseDouble(roomEntity.getOraDiscountPercentage()) * price / 100;
						}
						
						// Offer
						if(!CollectionUtils.isEmpty(roomEntity.getRoomVsOfferEntities())) {
							for(RoomVsOfferEntity roomVsOfferEntity : roomEntity.getRoomVsOfferEntities()) {
								if(Objects.nonNull(roomVsOfferEntity)) {
									if(Objects.nonNull(roomVsOfferEntity.getOfferEntity())) {
										offerEntities.add(roomVsOfferEntity.getOfferEntity());
									}
								}
							}
						}
						
					}
					
					System.out.println("priceDropDiscount ==>> "+priceDropDiscount);
					System.err.println("hostDiscount ==>> "+hostDiscount);
					System.out.println("oraDiscount ==>> "+oraDiscount);
					System.err.println("discountedPrice before ==>> "+discountedPrice);
					discountedPrice = discountedPrice + hostDiscount + oraDiscount + priceDropDiscount;
					System.out.println("discountedPrice after deduction from totalPrice ==>> "+discountedPrice);
				}
			}
		}
		
		Double calculatedPrice = totalPrice - discountedPrice;
		System.err.println("calculatedPrice ==>> "+calculatedPrice);
		// Offer Calculation
		if(!CollectionUtils.isEmpty(offerEntities)) {
			for(OfferEntity offerEntity : offerEntities) {
				System.out.println("offerEntity ==>> "+offerEntity);
				if(Objects.nonNull(offerEntity)) {
					
					System.out.println("offerEntity.getMaxAmount() ==>> "+offerEntity.getMaxAmount());
					if (!StringUtils.isBlank(offerEntity.getMaxAmount())) { // Calculate with Max Amount
						
						if (Double.parseDouble(offerEntity.getMaxAmount()) <= calculatedPrice) {
							if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
								offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
								System.out.println("Calculate with Max Amount offerPrice ==>> "+offerPrice);
							} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
								offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
								System.out.println("Calculate with Max Amount offerPrice ==>> "+offerPrice);
							}
						}
					}
					
					System.out.println("offerEntity.getStartDateRange() ==>> "+offerEntity.getStartDateRange());
					if (!StringUtils.isBlank(offerEntity.getStartDateRange()) && !StringUtils.isBlank(offerEntity.getEndDateRange())) { // Calculate with Date Range
						if (Util.getDateDiff(offerEntity.getStartDateRange()) >= 0 && Util.getDateDiff(offerEntity.getEndDateRange()) <= 0) {
							if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
								offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
								System.out.println("Calculate with Date Range offerPrice ==>> "+offerPrice);
							} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
								offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
								System.out.println("Calculate with Date Range offerPrice ==>> "+offerPrice);
							}
						}
					}
					
					if (StringUtils.isBlank(offerEntity.getMaxAmount()) && StringUtils.isBlank(offerEntity.getStartDateRange()) && StringUtils.isBlank(offerEntity.getOnline())) { // Calculate other than Date Range & Max Amount
						if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
							offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
							System.out.println("Calculate other than Date Range & Max Amount offerPrice ==>> "+offerPrice);
						} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
							offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
							System.out.println("Calculate other than Date Range & Max Amount offerPrice ==>> "+offerPrice);
						}
					}
					
				}
			}
		}
		
		System.out.println("offerPrice ==>> "+offerPrice);
		discountedPrice = discountedPrice + offerPrice;
		System.out.println("Final discountedPrice ==>> "+discountedPrice);
		//totalPrice = totalPrice * numOfDays;
		//discountedPrice = discountedPrice * numOfDays;
		prices.add(String.valueOf(Math.round(totalPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(discountedPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(offerPrice * 100D) / 100D));
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculation -- END");
		}
		
		return prices;
	}
	
	private List<String> getRatingAndReview(PropertyEntity propertyEntity) {
		
		if (logger.isInfoEnabled()) {
			logger.info("getRatingAndReview -- START");
		}
		
		List<String> ratings = new ArrayList<>();
		try {
			PropertyModel propertyModel = new PropertyModel();
			propertyModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
			propertyModel.setUserTypeId(String.valueOf(UserType.CUSTOMER.ordinal()));
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("review.server.url") +"fetch-review", propertyModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			gson = new Gson();
			Type listType = new TypeToken<List<UserReviewModel>>() {}.getType();
			List<UserReviewModel> userReviewModels = gson.fromJson(jsonString, listType);
			
			if (logger.isInfoEnabled()) {
				logger.info("userReviewModels ==>> "+userReviewModels);
			}
			System.out.println("userReviewModels ==>> "+userReviewModels);
			
			if(!CollectionUtils.isEmpty(userReviewModels)) {
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
						ratings.add(0, "0"); // Rating
						break;
					}
				}
				
				Double totalRating = 0.0D;
				for (Map.Entry<String,String> entry : ratingTypes.entrySet()) { 
					totalRating = totalRating + Double.parseDouble(entry.getValue());
				}
				
				if (!CollectionUtils.isEmpty(ratingTypes)) {
					ratings.add(0, String.valueOf(Util.roundOff(totalRating / ratingTypes.size() / userReviewModels.size()))); // Rating
				} else {
					ratings.add("0"); // Rating
				}
				
				
				// Calculate Review Count
				ratings.add(1, String.valueOf(userReviewModels.size())); // Review Count
			} else {
				ratings.add("0"); // Rating
				ratings.add("0"); // Review Count
			}
			
			
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getRatingAndReview -- "+Util.errorToString(e));
			}
			ratings.add("0");
			ratings.add("0");
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("getRatingAndReview -- END");
		}
		
		return ratings;
	}
	
	private Boolean filterByLocation(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByLocation -- START");
		}
		
		boolean flag = false;
		if(!CollectionUtils.isEmpty(propertyDAO.selectByRadius(filterCiteriaModel))) {
			flag = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByLocation -- END");
		}
		
		return flag;
	}
	
	private Boolean filterByPropertyDate(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPropertyDate -- START");
		}
		
		boolean flag = false;
		if(Util.getDayDiff(filterCiteriaModel.getCheckInDate(), propertyEntity.getStartDate()) <= 0 && Util.getDayDiff(filterCiteriaModel.getCheckOutDate(), propertyEntity.getEndDate()) >= 0) {
			flag = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPropertyDate -- END");
		}
		
		return flag;
	}
	
	private Boolean filterBycheckInDate(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBycheckInDate -- START");
		}
		
		boolean flag = false;
		
		try {
			BookingModel bookingModel = new BookingModel();
			bookingModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
			bookingModel.setCheckinDate(filterCiteriaModel.getCheckInDate());
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("booking.server.url") +"get-bookings", bookingModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			gson = new Gson();
			Type listType = new TypeToken<List<BookingModel>>() {}.getType();
			List<BookingModel> bookingModels = gson.fromJson(jsonString, listType);
			
			if (logger.isInfoEnabled()) {
				logger.info("bookingModels ==>> "+bookingModels);
			}
			
			System.err.println("bookingModels ==>> "+bookingModels);
			
			Map<String, List<BookingVsRoomModel>> roomByOraName = new LinkedHashMap<>();
			if (!CollectionUtils.isEmpty(bookingModels)) {
				
				List<BookingVsRoomModel> bookingVsRoomModels = new ArrayList<>();
				for(BookingModel bookingModel2 : bookingModels) {
					if(Objects.nonNull(bookingModel2) && !CollectionUtils.isEmpty(bookingModel2.getBookingVsRooms())) {
						bookingVsRoomModels.addAll(bookingModel2.getBookingVsRooms());
					}
				}
				
				if(!CollectionUtils.isEmpty(bookingVsRoomModels)) {
					for(BookingVsRoomModel bookingVsRoomModel : bookingVsRoomModels) {
						if(roomByOraName.isEmpty()) { // First Time
							List<BookingVsRoomModel> mapBookingVsRoomModels = new ArrayList<>();
							mapBookingVsRoomModels.add(bookingVsRoomModel);
							roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
						} else {
							if(roomByOraName.containsKey(bookingVsRoomModel.getOraRoomName())) { // KEY Present
								List<BookingVsRoomModel> mapBookingVsRoomModels = roomByOraName.get(bookingVsRoomModel.getOraRoomName());
								mapBookingVsRoomModels.add(bookingVsRoomModel);
								roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
							} else { // KEY not present
								List<BookingVsRoomModel> mapBookingVsRoomModels = new ArrayList<>();
								mapBookingVsRoomModels.add(bookingVsRoomModel);
								roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
							}
						}
					}
				}
			}
			
			Set<RoomEntity> filteredRoomEntities = new HashSet<>();
			System.out.println("propertyEntity ==>> "+propertyEntity);
			if(CollectionUtils.isEmpty(roomByOraName)) { // No Rooms Are Booked
				
				List<Boolean> countFlag = new ArrayList<>();
				List<RoomEntity> deletableRoomEntities = new CopyOnWriteArrayList<>();
				deletableRoomEntities = propertyEntity.getRoomEntities();
				System.out.println("deletableRoomEntities ==>> "+deletableRoomEntities);
				for(RoomModel roomModel : filterCiteriaModel.getRoomModels()) {
					
					int bedRequired = Integer.parseInt(roomModel.getNoOfGuest());
					System.err.println("bedRequired ==>> "+bedRequired);
					
					for(int i = 0; i < deletableRoomEntities.size(); i++) {
						
						RoomEntity roomEntity = deletableRoomEntities.get(i);
						System.err.println("First Loop roomEntity ==>> "+roomEntity);
						
						if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
							
							int totalBed = 0;
							if(StringUtils.isBlank(roomEntity.getNumOfCot())) {
								totalBed = Integer.parseInt(roomEntity.getNumOfBed());
							} else {
								totalBed = (Integer.parseInt(roomEntity.getNumOfBed()) + (Integer.parseInt(roomEntity.getNumOfCot())));
							}
							
							System.err.println("totalBed ==>> "+totalBed);
							
							if(bedRequired <= totalBed) { // Bed + Cot
								
								System.err.println("Less than Bed + Cot");
								filteredRoomEntities.add(roomEntity);
								deletableRoomEntities.remove(i);
							} else {
								bedRequired = bedRequired - totalBed;
								filteredRoomEntities.add(roomEntity);
								deletableRoomEntities.remove(i);
							}
						} else { //private
							
							int totalBed = 0;
							if(StringUtils.isBlank(roomEntity.getNumOfCot())) {
								totalBed = Integer.parseInt(roomEntity.getNoOfGuest());
							} else {
								totalBed = (Integer.parseInt(roomEntity.getNoOfGuest()) + (Integer.parseInt(roomEntity.getNumOfCot())));
							}
							
							System.err.println("totalBed ==>> "+totalBed);
							
							int userChild = 0;
							if(!StringUtils.isBlank(roomModel.getNoOfChild())) {
								userChild = Integer.parseInt(roomModel.getNoOfChild());
							}
							
							int dbChild = 0;
							if(!StringUtils.isBlank(roomEntity.getNoOfChild())) {
								dbChild = Integer.parseInt(roomEntity.getNoOfChild());
							}
							
							if(bedRequired <= totalBed && (userChild <= dbChild)) { // Bed + Cot
								
								System.err.println("Less than Bed + Cot");
								filteredRoomEntities.add(roomEntity);
								deletableRoomEntities.remove(i);
							}
						}
					}
					
					System.out.println("filteredRoomEntities ==>> "+filteredRoomEntities);
					if(!CollectionUtils.isEmpty(filteredRoomEntities)) { // Contains MIN 1 ROOM
						propertyEntity.setRoomEntities(null); // Delete All Rooms
						List<RoomEntity> listRoomEntities = new ArrayList<>(filteredRoomEntities); // SET to LIST
						propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
						countFlag.add(true);
					} else {
						countFlag.add(false);
					}
				}
				
				if(!countFlag.contains(false)) {
					flag = true;
				}
			} else { // Property Is Booked
				
				List<Boolean> countFlag = new ArrayList<>();
				List<RoomEntity> deletableRoomEntities = new CopyOnWriteArrayList<>();
				deletableRoomEntities = propertyEntity.getRoomEntities();
				for(RoomModel roomModel : filterCiteriaModel.getRoomModels()) {
					
					int bedRequired = Integer.parseInt(roomModel.getNoOfGuest());
					System.err.println("bedRequired ==>> "+bedRequired);
					
					for(int i = 0; i < deletableRoomEntities.size(); i++) {
						
						RoomEntity roomEntity = deletableRoomEntities.get(i);
						System.err.println("First Loop roomEntity ==>> "+roomEntity);
						if(roomByOraName.containsKey(roomEntity.getOraRoomName())) { // KEY Present
							
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								
								int maxBedBooked = 0;
								List<BookingVsRoomModel> roomBooked = roomByOraName.get(roomEntity.getOraRoomName());
								for(BookingVsRoomModel bookingVsRoomModel : roomBooked) {
									maxBedBooked = maxBedBooked + Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed());
								}
								
								System.out.println("maxBedBooked ==>> "+maxBedBooked);
								
								int totalBed = 0;
								if(StringUtils.isBlank(roomEntity.getNumOfCot())) {
									totalBed = Integer.parseInt(roomEntity.getNumOfBed());
								} else {
									totalBed = (Integer.parseInt(roomEntity.getNumOfBed()) + (Integer.parseInt(roomEntity.getNumOfCot())));
								}
								
								totalBed = totalBed - maxBedBooked;
								System.err.println("totalBed ==>> "+totalBed);
								
								if(bedRequired <= totalBed) { // Bed + Cot
									
									System.err.println("Less than Bed + Cot");
									filteredRoomEntities.add(roomEntity);
									deletableRoomEntities.remove(i);
								} else {
									bedRequired = bedRequired - totalBed;
									filteredRoomEntities.add(roomEntity);
									deletableRoomEntities.remove(i);
								}
							}
						} else { // ROOM Not Booked
							
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								
								int totalBed = 0;
								if(!StringUtils.isBlank(roomEntity.getNumOfCot())) {
									totalBed = Integer.parseInt(roomEntity.getNumOfBed());
								} else {
									totalBed = (Integer.parseInt(roomEntity.getNumOfBed()) + (Integer.parseInt(roomEntity.getNumOfCot())));
								}
								System.err.println("totalBed ==>> "+totalBed);
								
								if(bedRequired <= totalBed) { // Bed + Cot
								
								System.err.println("Less than Bed + Cot");
								filteredRoomEntities.add(roomEntity);
								deletableRoomEntities.remove(i);
							} else {
								bedRequired = bedRequired - totalBed;
								filteredRoomEntities.add(roomEntity);
								deletableRoomEntities.remove(i);
							}
							} else { //private
								
								int totalBed = 0;
								if(!StringUtils.isBlank(roomEntity.getNumOfCot())) {
									totalBed = Integer.parseInt(roomEntity.getNoOfGuest());
								} else {
									totalBed = (Integer.parseInt(roomEntity.getNoOfGuest()) + (Integer.parseInt(roomEntity.getNumOfCot())));
								}
								
								System.err.println("totalBed ==>> "+totalBed);
								
								int userChild = 0;
								if(!StringUtils.isBlank(roomModel.getNoOfChild())) {
									userChild = Integer.parseInt(roomModel.getNoOfChild());
								}
								
								int dbChild = 0;
								if(!StringUtils.isBlank(roomEntity.getNoOfChild())) {
									dbChild = Integer.parseInt(roomEntity.getNoOfChild());
								}
								
								if(bedRequired <= totalBed && (userChild <= dbChild)) { // Bed + Cot
									
									System.err.println("Less than Bed + Cot");
									filteredRoomEntities.add(roomEntity);
									deletableRoomEntities.remove(i);
								}
							}
						}
					}
					
					if(!CollectionUtils.isEmpty(filteredRoomEntities)) { // Contains MIN 1 ROOM
						propertyEntity.setRoomEntities(null); // Delete All Rooms
						List<RoomEntity> listRoomEntities = new ArrayList<>(filteredRoomEntities); // SET to LIST
						propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
						countFlag.add(true);
					} else {
						countFlag.add(false);
					}
				}
				
				if(!countFlag.contains(false)) {
					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("Exception in filterBycheckInDate -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBycheckInDate -- END");
		}
		
		return flag;
	}
	
	private Boolean filterByRating(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByRating -- START");
		}
		
		boolean flag = false;
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
				List<String> ratingsInput = filterCiteriaModel.getRatings();
				List<String> ratings = new ArrayList<>();
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
						ratings.add(0, "0"); // Rating
						break;
					}
				}
				
				Long totalRating = 0L;
				for (Map.Entry<String,String> entry : ratingTypes.entrySet()) { 
					totalRating = totalRating + Long.parseLong(entry.getValue());
				}
				ratings.add(0, String.valueOf(totalRating / ratingTypes.size())); // Rating
				for(String input : ratingsInput) {
					if(Long.parseLong(input) >= Long.parseLong(ratings.get(0))) {
						flag = true;
						break;
					}
				}
			}
			
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in filterByRating -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByRating -- END");
		}
		
		return flag;
	}
	
	private Boolean filterByAmmenities(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
			
		if (logger.isInfoEnabled()) {
			logger.info("filterByAmmenities -- START");
		}
		
		boolean flag = false;
		Set<String> amenitiesModels = new LinkedHashSet<>();
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
				if(Objects.nonNull(roomEntity)) {
					if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
						for(RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomEntity.getRoomVsAmenitiesEntities()) {
							amenitiesModels.add(amenitiesConverter.entityToModel(roomVsAmenitiesEntity.getAmenitiesEntity()).getAminitiesId());
						}
					}
				}
			}
		}
		
		List<Boolean> count = new ArrayList<>();
		for(AmenitiesModel amenitiesModel : filterCiteriaModel.getAmenitiesModels()) {
			if(amenitiesModels.contains(amenitiesModel.getAminitiesId())) {
				count.add(true);
			} else {
				count.add(false);
			}
		}
		
		if(!count.contains(false)) {
			flag = true;
		}
		
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByAmmenities -- END");
		}
		
		return flag;
	}
	
	
	private Boolean filterByBudget(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByBudget -- START");
		}
		
		boolean flag = false;
		List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel);
		Double price = Double.parseDouble(prices.get(0));
		for(String budget : filterCiteriaModel.getBudgets()) {
			Double start = Double.parseDouble(budget.split("-")[0]);
			Double end = Double.parseDouble(budget.split("-")[1]);
			if(price >= start && price <= end) {
				flag = true;
				break;
			}
		}
		
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByBudget -- END");
		}
		
		return flag;
	}
	
	
	private Boolean filterByPopularLocation(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPopularLocation -- START");
		}
		
		boolean flag = false;
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPopularLocation -- END");
		}
		
		return flag;
	}
	
	
	private Boolean filterBySpaceRule(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBySpaceRule -- START");
		}
		
		boolean flag = false;
		List<Boolean> count = new ArrayList<>();
		if(!CollectionUtils.isEmpty(propertyEntity.getPropertyVsSpaceRuleEntities())) {
			Set<Long> spruleId = new LinkedHashSet<>();
			for(PropertyVsSpaceRuleEntity propertyVsSpaceRuleEntity : propertyEntity.getPropertyVsSpaceRuleEntities()) {
				if(StringUtils.equals(propertyVsSpaceRuleEntity.getAnswer(), PropertyListConstant.STR_Y)) {
					spruleId.add(propertyVsSpaceRuleEntity.getSpaceRuleEntity().getSpruleId());
				}
			}
			
			for(SpaceRuleModel spaceRuleModel : filterCiteriaModel.getSpaceRuleModels()) {
				if(spruleId.contains(Long.valueOf(spaceRuleModel.getSpruleId()))) {
					count.add(true);
				} else {
					count.add(false);
				}
			}
		}
		
		if(!count.contains(false)) {
			flag = true;
		}
		
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBySpaceRule -- END");
		}
		
		return flag;
	}
	
	private Boolean filterBySex(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBySex -- START");
		}
		
		boolean flag = false;
		if(StringUtils.equals(String.valueOf(propertyEntity.getSexCategory()), String.valueOf(Sex.BOTH))) {
			flag = true;
		} else if(StringUtils.equals(filterCiteriaModel.getPgCategorySex(), String.valueOf(propertyEntity.getSexCategory()))) {
			flag = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBySex -- END");
		}
		
		return flag;
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
	public PropertyModel fetchPropertyDetails(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyDetails -- START");
		}
		
		PropertyModel propertyModel = null;
//		PropertyEntity propertyEntity = propertyListValidation.validateFetchPropertyDetails(filterCiteriaModel);
//		
//		// Filter By Property Start Date and End Date
//		if(filterByPropertyDate(propertyEntity, filterCiteriaModel)) {
//			
//			// Filter by propertyTypeId // Mandatory
//			if (StringUtils.equals(filterCiteriaModel.getPropertyTypeId(), String.valueOf(propertyEntity.getPropertyTypeEntity().getPropertyTypeId()))) {
//				
//				// Filter by location // Mandatory
//				if(filterByLocation(propertyEntity, filterCiteriaModel)) {
//					boolean flag = true;
//					// Filter by checkInDate // Mandatory
//					// Filter by checkOutDate // Mandatory
//					// Filter by roomModels // Mandatory
//					if(filterBycheckInDate(propertyEntity, filterCiteriaModel)) {
//						
//						// Filter By Rating
//						if (!CollectionUtils.isEmpty(filterCiteriaModel.getRatings())) {
//							if (!filterByRating(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						// Filter by amenitiesModels
//						if (!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels()) && flag) {
//							if (!filterByAmmenities(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						
//						// Filter by budgets
//						if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets()) && flag) {
//							if (!filterByBudget(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						
//						// Filter by popularLocations
//						if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && flag) {
//							if (!filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						
//						// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
//						if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && flag) {
//							if (!filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						
//						// Filter by pgCategorySexModels // Male/Female
//						if(!StringUtils.isBlank(filterCiteriaModel.getPgCategorySex()) && flag) {
//							if (!filterBySex(propertyEntity, filterCiteriaModel)) {
//								flag = false;
//							}
//						}
//						
//						if(flag) {
//							propertyModel = propertyConverter.entityToModel(propertyEntity);
//							List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel);
//							propertyModel.setTotalPrice(prices.get(0));
//							propertyModel.setDiscountedPrice(prices.get(1));
//							
//							// Calculate Convenience
//							try {
//								ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("booking.server.url") +"get-convenience", ResponseModel.class);
//								Gson gson = new Gson();
//								String jsonString = gson.toJson(responseModel.getResponseBody());
//								ConvenienceModel convenienceModel = gson.fromJson(jsonString, ConvenienceModel.class);
//								
//								if (logger.isInfoEnabled()) {
//									logger.info("convenienceModel ==>> "+convenienceModel);
//								}
//								System.err.println("convenienceModel ==>> "+convenienceModel);
//								
//								if (Objects.nonNull(convenienceModel)) {
//									propertyModel.setConvenienceFee(convenienceModel.getAmount());
//									propertyModel.setConvenienceGSTPercentage(convenienceModel.getGstPercentage());
//									propertyModel.setConvenienceGSTAmount(String.valueOf(Math.round(Double.parseDouble(convenienceModel.getAmount()) * Double.parseDouble(convenienceModel.getGstPercentage()) / 100 * 100D) / 100D));
//								}
//							} catch (Exception e) {
//								if (logger.isInfoEnabled()) {
//									logger.info("Exception in getConvenience -- "+Util.errorToString(e));
//								}
//								propertyModel.setConvenienceFee("0");
//								propertyModel.setConvenienceGSTPercentage("0");
//								propertyModel.setConvenienceGSTAmount("0");
//							}
//							
//							// Calculate GST
//							try {
//								String amount = String.valueOf(Math.round(Double.parseDouble(propertyModel.getTotalPrice()) - Double.parseDouble(propertyModel.getDiscountedPrice()))* 100D / 100D);
//								System.err.println("amount ==>> "+amount);
//								propertyModel.setAmountPayable(amount);
//								ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("booking.server.url") +"get-gst-price?amount="+amount, ResponseModel.class);
//								Gson gson = new Gson();
//								String jsonString = gson.toJson(responseModel.getResponseBody());
//								GstSlabModel gstSlabModel = gson.fromJson(jsonString, GstSlabModel.class);
//								
//								if (logger.isInfoEnabled()) {
//									logger.info("gstSlabModel ==>> "+gstSlabModel);
//								}
//								System.err.println("gstSlabModel ==>> "+gstSlabModel);
//								
//								if (Objects.nonNull(gstSlabModel)) {
//									propertyModel.setGstPercentage(gstSlabModel.getPercentage());
//									propertyModel.setAmountWithGST(String.valueOf(Math.round(Double.parseDouble(amount) * Double.parseDouble(gstSlabModel.getPercentage()) / 100 * 100D) / 100D));
//								}
//							} catch (Exception e) {
//								if (logger.isInfoEnabled()) {
//									logger.info("Exception in getGSTPrice -- "+Util.errorToString(e));
//								}
//								propertyModel.setGstPercentage("0");
//								propertyModel.setAmountWithGST("0");
//							}
//							
//							if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
//								for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
//									if(Objects.nonNull(roomEntity) && roomEntity.getStatus() == Status.ACTIVE.ordinal()) {
//										if(!CollectionUtils.isEmpty(propertyModel.getRoomModels())) {
//											for(RoomModel roomModel : propertyModel.getRoomModels()) {
//												if(Objects.nonNull(roomModel) && roomModel.getStatus() == Status.ACTIVE.ordinal()) {
//													if(StringUtils.equals(roomModel.getRoomId(), String.valueOf(roomEntity.getRoomId()))) {
//														roomModel.setOraPrice(priceCalculationForRoom(propertyEntity, roomEntity, filterCiteriaModel).get(0));
//														roomModel.setOraDiscount(priceCalculationForRoom(propertyEntity, roomEntity, filterCiteriaModel).get(1));
//														roomModel.setOffer(priceCalculationForRoom(propertyEntity, roomEntity, filterCiteriaModel).get(2));
//														roomModel.setPriceDrop(priceCalculationForRoom(propertyEntity, roomEntity, filterCiteriaModel).get(3));
//														break;
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					} 
//				} 
//			} 
//		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyDetails -- END");
		}
		
		return propertyModel;
	}
	
	public List<String> priceCalculationForRoom(PropertyEntity propertyEntity, RoomEntity roomEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculationForRoom -- START");
		}
		
		List<String> prices = new ArrayList<>();
		int numOfDays = Util.getDayDiff(filterCiteriaModel.getCheckInDate(), filterCiteriaModel.getCheckOutDate());
		Double price = 0.0D;
		Double totalPrice = 0.0D;
		Double discountedPrice = 0.0D;
		Double offerPrice = 0.0D;
		Set<OfferEntity> offerEntities = new HashSet<>();
					
		// Price calculation
		if (numOfDays >= 30 ) {
				
			if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.INACTIVE.ordinal()) {   //short term (ID = 2)
		
				if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
					
					//Shared night price 
					price = Double.parseDouble(roomEntity.getSharedBedPricePerNight());
				} else { //private
					 //Private night Price
					price = Double.parseDouble(roomEntity.getRoomPricePerNight());
				}
		
			} else {   //both & long term
				
				if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
					//Shared Month price 
					price = (Double.parseDouble(roomEntity.getSharedBedPricePerMonth())/30);
				} else {   //private
					 //Private Month Price
					price = (Double.parseDouble(roomEntity.getRoomPricePerMonth())/30);
				}
			}
		} else {
			
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
		}
		
		System.out.println("price ==>> "+price);
		System.err.println("roomEntity.getOraPercentage() ==>> "+roomEntity.getOraPercentage());
		System.out.println("totalPrice before ==>> "+totalPrice);
		totalPrice = totalPrice + price + (Double.parseDouble(roomEntity.getOraPercentage()) * price / 100);
		System.err.println("totalPrice after including OraPercentage ==>> "+totalPrice); 
		
		// Discount Section
		Double hostDiscount = 0.0D;
		Double oraDiscount = 0.0D;
		Double priceDropDiscount = 0.0D;
		
		// Check Pricedrop if any
		if(Util.getDateDiff1(filterCiteriaModel.getCheckInDate()) == 0 && numOfDays == 1) { // Current Date
			if(!CollectionUtils.isEmpty(propertyEntity.getPropertyVsPriceDropEntities())) { // Price Drop Present
				int hourDifference = Util.getMinuteDiff(Util.getCurrentDate() + " " +propertyEntity.getCheckinTime()) / 60;
				for(int i = 0; i< propertyEntity.getPropertyVsPriceDropEntities().size(); i++) {
					PropertyVsPriceDropEntity propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i);
					if(hourDifference <= Integer.parseInt(propertyVsPriceDropEntity.getPriceDropEntity().getAfterTime())) {
						if(i == 0) { // First Condition
							priceDropDiscount = Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
							break;
						} else {
							propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i -1);
							priceDropDiscount = Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
							break;
						}
					}
				}
			}
		} else {
			// Host Discount if any
			if (numOfDays >= 7 && numOfDays < 30) { // Weekly
				
				hostDiscount = Double.parseDouble(roomEntity.getHostDiscountWeekly()) * price / 100;
				
			} else if(numOfDays >= 30) { // Monthly
				
				if (!StringUtils.isBlank(roomEntity.getHostDiscountMonthly())) { // Check if monthly present
					hostDiscount = Double.parseDouble(roomEntity.getHostDiscountMonthly()) * price / 100;
				} else if (!StringUtils.isBlank(roomEntity.getHostDiscountWeekly())) { // otherwise calculate with weekly
					hostDiscount = Double.parseDouble(roomEntity.getHostDiscountWeekly()) * price / 100;
				}
			}
			
			// Room Vs ORA Discount
			// Percentage
			if (!StringUtils.isBlank(roomEntity.getOraDiscountPercentage())) {
				oraDiscount = Double.parseDouble(roomEntity.getOraDiscountPercentage()) * price / 100;
			}
			
			// Offer
			if(!CollectionUtils.isEmpty(roomEntity.getRoomVsOfferEntities())) {
				for(RoomVsOfferEntity roomVsOfferEntity : roomEntity.getRoomVsOfferEntities()) {
					if(Objects.nonNull(roomVsOfferEntity)) {
						if(Objects.nonNull(roomVsOfferEntity.getOfferEntity())) {
							offerEntities.add(roomVsOfferEntity.getOfferEntity());
						}
					}
				}
			}
			
		}
		
		System.out.println("priceDropDiscount ==>> "+priceDropDiscount);
		System.err.println("hostDiscount ==>> "+hostDiscount);
		System.out.println("oraDiscount ==>> "+oraDiscount);
		System.err.println("discountedPrice before ==>> "+discountedPrice);
		discountedPrice = discountedPrice + hostDiscount + oraDiscount + priceDropDiscount;
		System.out.println("discountedPrice after deduction from totalPrice ==>> "+discountedPrice);
		
		Double calculatedPrice = totalPrice - discountedPrice;
		System.err.println("calculatedPrice ==>> "+calculatedPrice);
		// Offer Calculation
		if(!CollectionUtils.isEmpty(offerEntities)) {
			for(OfferEntity offerEntity : offerEntities) {
				System.out.println("offerEntity ==>> "+offerEntity);
				if(Objects.nonNull(offerEntity)) {
					
					System.out.println("offerEntity.getMaxAmount() ==>> "+offerEntity.getMaxAmount());
					if (!StringUtils.isBlank(offerEntity.getMaxAmount())) { // Calculate with Max Amount
						
						if (Double.parseDouble(offerEntity.getMaxAmount()) <= calculatedPrice) {
							if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
								offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
								System.out.println("Calculate with Max Amount offerPrice ==>> "+offerPrice);
							} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
								offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
								System.out.println("Calculate with Max Amount offerPrice ==>> "+offerPrice);
							}
						}
					}
					
					System.out.println("offerEntity.getStartDateRange() ==>> "+offerEntity.getStartDateRange());
					if (!StringUtils.isBlank(offerEntity.getStartDateRange()) && !StringUtils.isBlank(offerEntity.getEndDateRange())) { // Calculate with Date Range
						if (Util.getDateDiff(offerEntity.getStartDateRange()) >= 0 && Util.getDateDiff(offerEntity.getEndDateRange()) <= 0) {
							if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
								offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
								System.out.println("Calculate with Date Range offerPrice ==>> "+offerPrice);
							} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
								offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
								System.out.println("Calculate with Date Range offerPrice ==>> "+offerPrice);
							}
						}
					}
					
					if (StringUtils.isBlank(offerEntity.getMaxAmount()) && StringUtils.isBlank(offerEntity.getStartDateRange()) && StringUtils.isBlank(offerEntity.getOnline())) { // Calculate other than Date Range & Max Amount
						if (!StringUtils.isBlank(offerEntity.getAmount())) { // Amount Check
							offerPrice = offerPrice + Double.parseDouble(offerEntity.getAmount());
							System.out.println("Calculate other than Date Range & Max Amount offerPrice ==>> "+offerPrice);
						} else if (!StringUtils.isBlank(offerEntity.getPercentage())) { // Percentage Check
							offerPrice = offerPrice + (Double.parseDouble(offerEntity.getPercentage()) * calculatedPrice / 100);
							System.out.println("Calculate other than Date Range & Max Amount offerPrice ==>> "+offerPrice);
						}
					}
					
				}
			}
		}
		
		System.out.println("offerPrice ==>> "+offerPrice);
		discountedPrice = discountedPrice + offerPrice;
		System.out.println("Final discountedPrice ==>> "+discountedPrice);
		//totalPrice = totalPrice * numOfDays;
		//discountedPrice = discountedPrice * numOfDays;
		prices.add(String.valueOf(Math.round(totalPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(discountedPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(offerPrice * 100D) / 100D));
		prices.add(String.valueOf(Math.round(priceDropDiscount * 100D) / 100D));
		
		if (logger.isInfoEnabled()) {
			logger.info("priceCalculationForRoom -- END");
		}
		
		return prices;
	}

}