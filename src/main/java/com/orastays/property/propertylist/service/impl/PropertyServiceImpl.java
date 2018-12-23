package com.orastays.property.propertylist.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.PropertyVsPriceDropEntity;
import com.orastays.property.propertylist.entity.PropertyVsSpaceRuleEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.entity.RoomVsHostDiscountEntity;
import com.orastays.property.propertylist.entity.RoomVsMealEntity;
import com.orastays.property.propertylist.entity.RoomVsOraDiscountEntity;
import com.orastays.property.propertylist.entity.RoomVsPriceEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.HostDiscount;
import com.orastays.property.propertylist.helper.OraDiscount;
import com.orastays.property.propertylist.helper.PriceType;
import com.orastays.property.propertylist.helper.PropertyListConstant;
import com.orastays.property.propertylist.helper.Status;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.AmenitiesModel;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.SpaceRuleModel;
import com.orastays.property.propertylist.model.UserModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.review.BookingVsRatingModel;
import com.orastays.property.propertylist.model.review.UserReviewModel;
import com.orastays.property.propertylist.service.PropertyListService;

@Service
@Transactional
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyListService {

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);

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
			
			List<PropertyEntity> propertyEntities = propertyDAO.fetchListBySubCiteria(alliasMap);
			System.err.println("propertyEntities ==>> "+propertyEntities);
			if(!CollectionUtils.isEmpty(propertyEntities)) {
				
				for(PropertyEntity propertyEntity : propertyEntities) {
					
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
											break;
										}
									}
									
									// Filter by amenitiesModels
									if (!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels())) {
										if (!filterByAmmenities(propertyEntity, filterCiteriaModel)) {
											break;
										}
									}
									
									
									// Filter by budgets
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets())) {
										if (!filterByBudget(propertyEntity, filterCiteriaModel)) {
											break;
										}
									}
									
									
									// Filter by popularLocations
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations())) {
										if (!filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
											break;
										}
									}
									
									
									// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels())) {
										if (!filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
											break;
										}
									}
									
									
									// Filter by pgCategorySexModels // Male/Female
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels())) {
										if (!filterBySex(propertyEntity, filterCiteriaModel)) {
											break;
										}
									}
									
									propertyListViewModels.add(setPropertyListView(propertyEntity, filterCiteriaModel));
									
								} else {
									break;
								}
							} else {
								break;
							}
						} else {
							break;
						}
					} else {
						break;
					}
					
				}
				
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
	
	private PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
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
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(StringUtils.equals(roomEntity.getRoomStandardEntity().getName(), PropertyListConstant.ROOM_STANDARD_PREMIUM)) {
					propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_PREMIUM);
					break;
				} else if(StringUtils.equals(roomEntity.getRoomStandardEntity().getName(), PropertyListConstant.ROOM_STANDARD_EXPRESS)) {
					propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_EXPRESS);
				} else {
					propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_NORMAL);
				}
			}
		} else {
			propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_NORMAL);
		}
		
		
		propertyListViewModel.setRating(getRatingAndReview(propertyEntity).get(0));
		propertyListViewModel.setReviewCount(getRatingAndReview(propertyEntity).get(1));
		
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
		
		propertyListViewModel.setPgCategorySexModel(pgCategorySexConverter.entityToModel(propertyEntity.getPgCategorySexEntity()));
		
		// Price Section
		List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel);
		propertyListViewModel.setTotalPrice(prices.get(0));
		propertyListViewModel.setDiscountedPrice(prices.get(1));
		
		// Meal Section
		propertyListViewModel.setMealFlag(false);
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(!CollectionUtils.isEmpty(roomEntity.getRoomVsMealEntities())) {
					for(RoomVsMealEntity roomVsMealEntity : roomEntity.getRoomVsMealEntities()) {
						if(Objects.nonNull(roomVsMealEntity.getMealPriceCategoryEntity())) {
							if(roomVsMealEntity.getMealPriceCategoryEntity().getMmpcId() == Status.ACTIVE.ordinal()) {
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
				if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
					for(RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomEntity.getRoomVsAmenitiesEntities()) {
						amenitiesModels.add(amenitiesConverter.entityToModel(roomVsAmenitiesEntity.getAmenitiesEntity()));
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
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(!CollectionUtils.isEmpty(roomEntity.getRoomVsPriceEntities())) {
					
					// Price calculation
					if (numOfDays >= 30 ) {
							
							if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.INACTIVE.ordinal()) {   //short term
						
								if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) { //shared
									//Shared night price 
									RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
							                .filter(x -> PriceType.SHARED_BED_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
									if(Objects.nonNull(roomVsPriceEntity)) {
										price = price + Double.parseDouble(roomVsPriceEntity.getValue());
									}
								} else { //private
									 //Private night Price
									RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
							                .filter(x -> PriceType.ROOM_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
									if(Objects.nonNull(roomVsPriceEntity)) {
										price = price + Double.parseDouble(roomVsPriceEntity.getValue());
									}
								}
						
							} else {   //both & long term
								
								if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) {   //shared
									//Shared Month price 
									RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
							                .filter(x -> PriceType.SHARED_BED_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
									if(Objects.nonNull(roomVsPriceEntity)) {
										price = price + (Double.parseDouble(roomVsPriceEntity.getValue())/30);
									}
								} else {   //private
									 //Private Month Price
									RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
							                .filter(x -> PriceType.ROOM_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
									if(Objects.nonNull(roomVsPriceEntity)) {
										price = price + (Double.parseDouble(roomVsPriceEntity.getValue())/30);
									}
								}
							}
					} else {
						if(propertyEntity.getStayTypeEntity().getStayTypeId() == Status.ACTIVE.ordinal()){   //Long term
							
							if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) { //shared
								//Shared Month price 
								RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
						                .filter(x -> PriceType.SHARED_BED_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
								if(Objects.nonNull(roomVsPriceEntity)) {
									price = price + (Double.parseDouble(roomVsPriceEntity.getValue())/30);
								}
							} else { //private
								 //Private Month Price
								RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
						                .filter(x -> PriceType.ROOM_PRICE_PER_MONTH.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
								if(Objects.nonNull(roomVsPriceEntity)) {
									price = price + (Double.parseDouble(roomVsPriceEntity.getValue())/30);
								}
							}
					
						} else {   //both & Short term
							
							if(roomEntity.getAccommodationEntity().getAccommodationId() == Status.ACTIVE.ordinal()) {   //shared
									//Shared Night price 
								RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
						                .filter(x -> PriceType.SHARED_BED_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
								if(Objects.nonNull(roomVsPriceEntity)) {
									price = price + Double.parseDouble(roomVsPriceEntity.getValue());
								}
							} else {   //private
								 //Private Night Price
								RoomVsPriceEntity roomVsPriceEntity = roomEntity.getRoomVsPriceEntities().stream() 
						                .filter(x -> PriceType.ROOM_PRICE_PER_NIGHT.ordinal() == x.getRoomVsPriceId()).findAny().orElse(null);  
								if(Objects.nonNull(roomVsPriceEntity)) {
									price = price + Double.parseDouble(roomVsPriceEntity.getValue());
								}
							}
						}				
					}
				}
				
				/*System.err.println("price ==>> "+price);
				System.out.println("roomEntity.getRoomVsOraPricePercentageEntities().get(0).getPercentage() ==>> "+roomEntity.getRoomVsOraPricePercentageEntities().get(0).getPercentage());
				System.err.println("totalPrice ==>> "+totalPrice);*/
				totalPrice = totalPrice + price + (Double.parseDouble(roomEntity.getRoomVsOraPricePercentageEntities().get(0).getPercentage()) * price / 100);
				/* System.err.println("totalPrice ==>> "+totalPrice); */
				
				// Discount Section
				Double discount = 0.0D;
				// Check Pricedrop if any
				if(Util.getDateDiff1(filterCiteriaModel.getCheckInDate()) == 0) { // Current Date
					if(!CollectionUtils.isEmpty(propertyEntity.getPropertyVsPriceDropEntities())) { // Price Drop Present
						int hourDifference = Util.getMinuteDiff(Util.getCurrentDate() + " " +propertyEntity.getCheckinTime()) / 60;
						for(int i = 0; i< propertyEntity.getPropertyVsPriceDropEntities().size(); i++) {
							PropertyVsPriceDropEntity propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i);
							if(hourDifference <= Integer.parseInt(propertyVsPriceDropEntity.getPriceDropEntity().getAfterTime())) {
								if(i == 0) { // First Condition
									discount = price - Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
									break;
								} else {
									propertyVsPriceDropEntity = propertyEntity.getPropertyVsPriceDropEntities().get(i -1);
									discount = price - Double.parseDouble(propertyVsPriceDropEntity.getPercentage()) * price / 100;
									break;
								}
							}
						}
					}
				} else {
					// Host Discount if any
					if (numOfDays >= 7 && numOfDays < 30) { // Weekly
						
						RoomVsHostDiscountEntity roomVsHostDiscountEntity = roomEntity.getRoomVsHostDiscountEntities().stream() 
				                .filter(x -> HostDiscount.WEEKLY.ordinal() == x.getDiscountCategoryHostEntity().getDchId()).findAny().orElse(null);
						if(Objects.nonNull(roomVsHostDiscountEntity)) {
							discount = price - Double.parseDouble(roomVsHostDiscountEntity.getPercentage()) * price / 100;
						}
						
					} else if(numOfDays >= 30) { // Monthly
						RoomVsHostDiscountEntity roomVsHostDiscountEntity = roomEntity.getRoomVsHostDiscountEntities().stream() 
				                .filter(x -> HostDiscount.MONTHLY.ordinal() == x.getDiscountCategoryHostEntity().getDchId()).findAny().orElse(null);
						if(Objects.nonNull(roomVsHostDiscountEntity)) {
							discount = price - Double.parseDouble(roomVsHostDiscountEntity.getPercentage()) * price / 100;
						} else {
							 roomVsHostDiscountEntity = roomEntity.getRoomVsHostDiscountEntities().stream() 
						                .filter(x -> HostDiscount.WEEKLY.ordinal() == x.getDiscountCategoryHostEntity().getDchId()).findAny().orElse(null);
							if(Objects.nonNull(roomVsHostDiscountEntity)) {
								discount = price - Double.parseDouble(roomVsHostDiscountEntity.getPercentage()) * price / 100;
							}
						}
					}
					
					// Room Vs ORA Discount
					// Percentage
					RoomVsOraDiscountEntity roomVsOraDiscountEntity = roomEntity.getRoomVsOraDiscountEntities().stream() 
			                .filter(x -> OraDiscount.PERCENTAGE.ordinal() == x.getDiscountCategoryOraEntity().getDcoId()).findAny().orElse(null);
					if(Objects.nonNull(roomVsOraDiscountEntity)) {
						discount = price - Double.parseDouble(roomVsOraDiscountEntity.getDiscount()) * price / 100;
					}
					
					// Amount
					roomVsOraDiscountEntity = roomEntity.getRoomVsOraDiscountEntities().stream() 
			                .filter(x -> OraDiscount.AMOUNT.ordinal() == x.getDiscountCategoryOraEntity().getDcoId()).findAny().orElse(null);
					if(Objects.nonNull(roomVsOraDiscountEntity)) {
						discount = price - Double.parseDouble(roomVsOraDiscountEntity.getDiscount());
					}
					
					// Date Range
					roomVsOraDiscountEntity = roomEntity.getRoomVsOraDiscountEntities().stream() 
			                .filter(x -> OraDiscount.DATE_RANGE.ordinal() == x.getDiscountCategoryOraEntity().getDcoId()).findAny().orElse(null);
					if(Objects.nonNull(roomVsOraDiscountEntity)) {
						String startDate = roomVsOraDiscountEntity.getDateRange().split(",")[0];
						String endDate = roomVsOraDiscountEntity.getDateRange().split(",")[1];
						if(Util.getDateDiff1(startDate) >= 0 && Util.getDateDiff1(endDate) <= 0) { // TO be check later
							discount = price - Double.parseDouble(roomVsOraDiscountEntity.getDiscount()) * price / 100;
						}
					}
				}
				System.err.println("discount ==>> "+discount);
				System.out.println("discountedPrice ==>> "+discountedPrice);
				System.err.println("totalPrice ==>> "+totalPrice);
				discountedPrice = discountedPrice + totalPrice - discount;
				System.out.println("discountedPrice ==>> "+discountedPrice);
			}
		}
		
		//totalPrice = totalPrice * numOfDays;
		//discountedPrice = discountedPrice * numOfDays;
		prices.add(String.valueOf(totalPrice));
		prices.add(String.valueOf(discountedPrice));
		
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
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("review.server.url") +"fetch-review", propertyModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			gson = new Gson();
			Type listType = new TypeToken<List<UserReviewModel>>() {}.getType();
			List<UserReviewModel> userReviewModels = gson.fromJson(jsonString, listType);
			
			if (logger.isInfoEnabled()) {
				logger.info("userReviewModels ==>> "+userReviewModels);
			}
			
			if(!CollectionUtils.isEmpty(userReviewModels)) {
				// Calculate Rating
				Map<String, String> ratingTypes = new LinkedHashMap<>();
				for(UserReviewModel userReviewModel : userReviewModels) {
					if(!CollectionUtils.isEmpty(userReviewModel.getBookingVsRatingModels())) {
						for(BookingVsRatingModel bookingVsRatingModel : userReviewModel.getBookingVsRatingModels()) {
							if(ratingTypes.isEmpty()) { // First Time
								ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), bookingVsRatingModel.getRating());
							} else {
								
								String reviews = ratingTypes.get(bookingVsRatingModel.getRatingModel().getRatingId());
								if (StringUtils.isBlank(reviews)) { // No Such Rating ID Found
									ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), bookingVsRatingModel.getRating());
								} else {
									reviews = String.valueOf(Long.parseLong(reviews) + Long.parseLong(bookingVsRatingModel.getRating()));
									ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), reviews);
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
				
				if (!CollectionUtils.isEmpty(ratingTypes)) {
					ratings.add(0, String.valueOf(totalRating / ratingTypes.size())); // Rating
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
		if(Util.getDayDiff(filterCiteriaModel.getCheckInDate(), propertyEntity.getStartDate()) >= 0 && Util.getDayDiff(filterCiteriaModel.getCheckOutDate(), propertyEntity.getEndDate()) >= 0) {
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
			BookingModel bookingModel2 = gson.fromJson(jsonString, BookingModel.class);
			if (logger.isInfoEnabled()) {
				logger.info("bookingModel2 ==>> "+bookingModel2);
			}
			System.err.println("bookingModel2 ==>> "+bookingModel2);
			
			if(Objects.isNull(bookingModel2)) {
				flag = true;
			} else {
				// TODO Delete the rooms from PropertyEntity which are already booked
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
			BookingModel bookingModel = new BookingModel();
			bookingModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
			bookingModel.setCheckinDate(filterCiteriaModel.getCheckInDate());
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("review.server.url") +"fetch-review", bookingModel, ResponseModel.class);
			List<UserReviewModel> userReviewModels = new ArrayList<>();
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			userReviewModels = (List<UserReviewModel>) gson.fromJson(jsonString, UserReviewModel.class);
			if (logger.isInfoEnabled()) {
				logger.info("userReviewModels ==>> "+userReviewModels);
			}
			
			if(Objects.nonNull(userReviewModels) && !CollectionUtils.isEmpty(userReviewModels)) {
				List<String> ratingsInput = filterCiteriaModel.getRatings();
				List<String> ratings = new ArrayList<>();
				// Calculate Rating
				Map<String, String> ratingTypes = new LinkedHashMap<>();
				for(UserReviewModel userReviewModel : userReviewModels) {
					if(!CollectionUtils.isEmpty(userReviewModel.getBookingVsRatingModels())) {
						for(BookingVsRatingModel bookingVsRatingModel : userReviewModel.getBookingVsRatingModels()) {
							if(ratingTypes.isEmpty()) { // First Time
								ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), bookingVsRatingModel.getRating());
							} else {
								
								String reviews = ratingTypes.get(bookingVsRatingModel.getRatingModel().getRatingId());
								if (StringUtils.isBlank(reviews)) { // No Such Rating ID Found
									ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), bookingVsRatingModel.getRating());
								} else {
									reviews = String.valueOf(Long.parseLong(reviews) + Long.parseLong(bookingVsRatingModel.getRating()));
									ratingTypes.put(bookingVsRatingModel.getRatingModel().getRatingId(), reviews);
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
				logger.info("Exception in getRatingAndReview -- "+Util.errorToString(e));
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
				if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
					for(RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomEntity.getRoomVsAmenitiesEntities()) {
						amenitiesModels.add(amenitiesConverter.entityToModel(roomVsAmenitiesEntity.getAmenitiesEntity()).getAminitiesName());
					}
				}
			}
		}
		
		List<Boolean> count = new ArrayList<>();
		for(AmenitiesModel amenitiesModel : filterCiteriaModel.getAmenitiesModels()) {
			if(amenitiesModels.contains(amenitiesModel.getAminitiesName())) {
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
			for(PropertyVsSpaceRuleEntity propertyVsSpaceRuleEntity : propertyEntity.getPropertyVsSpaceRuleEntities()) {
				for(SpaceRuleModel spaceRuleModel : filterCiteriaModel.getSpaceRuleModels()) {
					if(StringUtils.equals(spaceRuleModel.getSpruleId(), spaceRuleConverter.entityToModel(propertyVsSpaceRuleEntity.getSpaceRuleEntity()).getSpruleId())) {
						count.add(true);
					} else {
						count.add(false);
					}
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
		if(filterCiteriaModel.getPgCategorySexModels().size() < 2) {
			if(StringUtils.equals(filterCiteriaModel.getPgCategorySexModels().get(0).getPgcsId(), String.valueOf(propertyEntity.getPgCategorySexEntity().getPgcsId()))) {
				flag = true;
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBySex -- END");
		}
		
		return flag;
	}
}
