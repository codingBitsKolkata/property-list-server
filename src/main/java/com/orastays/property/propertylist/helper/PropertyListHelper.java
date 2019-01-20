package com.orastays.property.propertylist.helper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.property.propertylist.converter.AmenitiesConverter;
import com.orastays.property.propertylist.converter.SpaceRuleConverter;
import com.orastays.property.propertylist.entity.OfferEntity;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.PropertyVsPriceDropEntity;
import com.orastays.property.propertylist.entity.PropertyVsSpaceRuleEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.entity.RoomVsMealEntity;
import com.orastays.property.propertylist.entity.RoomVsOfferEntity;
import com.orastays.property.propertylist.model.AmenitiesModel;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.FilterRoomModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.SpaceRuleModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.booking.BookingVsRoomModel;
import com.orastays.property.propertylist.model.review.BookingVsRatingModel;
import com.orastays.property.propertylist.model.review.UserReviewModel;

@Component
public class PropertyListHelper {

	private static final Logger logger = LogManager.getLogger(PropertyListHelper.class);
	
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected AmenitiesConverter amenitiesConverter;
	
	@Autowired
	protected SpaceRuleConverter spaceRuleConverter;
	
	public Boolean filterByPropertyDate(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
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
	
	public Boolean filterByLocation(PropertyEntity propertyEntity, List<PropertyEntity> filteredPropertyEntitiesByRadius) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByLocation -- START");
		}
		
		boolean flag = false;
		
		PropertyEntity propertyEntity2 = filteredPropertyEntitiesByRadius.parallelStream().filter(p -> StringUtils.equals(String.valueOf(p.getPropertyId()), String.valueOf(propertyEntity.getPropertyId()))).findAny().orElse(null);
		if(Objects.nonNull(propertyEntity2)) {
			flag = true;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByLocation -- END");
		}
		
		return flag;
	}
	
	public Boolean filterByRating(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
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
				
				ratings.add(0, String.valueOf(Math.round(totalRating / ratingTypes.size() / userReviewModels.size()) * 100D / 100D)); // Rating
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
	
	public Boolean filterByAmmenities(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
			
		if (logger.isInfoEnabled()) {
			logger.info("filterByAmmenities -- START");
		}
		
		boolean flag = false;
		Set<String> amenitiesModels = new LinkedHashSet<>();
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			List<RoomEntity> roomEntities2 = propertyEntity.getRoomEntities().stream().collect(Collectors.toList());
			roomEntities2.parallelStream().forEach(roomEntity -> {
			//for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
				if(Objects.nonNull(roomEntity)) {
					if(!CollectionUtils.isEmpty(roomEntity.getRoomVsAmenitiesEntities())) {
						for(RoomVsAmenitiesEntity roomVsAmenitiesEntity : roomEntity.getRoomVsAmenitiesEntities()) {
							amenitiesModels.add(amenitiesConverter.entityToModel(roomVsAmenitiesEntity.getAmenitiesEntity()).getAminitiesId());
						}
					}
				}
			});
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
	
	public Boolean filterByPopularLocation(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPopularLocation -- START");
		}
		
		boolean flag = false;
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByPopularLocation -- END");
		}
		
		return flag;
	}
	
	public Boolean filterBySpaceRule(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
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
	
	public Boolean filterBySex(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
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
	
	public Boolean filterByBudget(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel, Map<String, FilterRoomModel> filteredRooms) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterByBudget -- START");
		}
		
		boolean flag = false;
		List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel, filteredRooms);
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
	
	public Map<Boolean, Map<String, FilterRoomModel>> filterBycheckInDate(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBycheckInDate -- START");
		}
		
		Boolean flag = false;
		Map<Boolean, Map<String, FilterRoomModel>> filterResult = new HashMap<>();
		Map<String, FilterRoomModel> filteredRooms = new ConcurrentHashMap<>();
		try {
			
			int noOfGuest = Integer.parseInt(filterCiteriaModel.getNoOfGuest());
			System.err.println("noOfGuest ==>> "+noOfGuest);
			if (StringUtils.equals(filterCiteriaModel.getStayType(), PropertyListConstant.SHARED)) { // Customer Wants SHARED Rooms
				
				if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
					System.err.println("propertyEntity.getRoomEntities() ==>> "+propertyEntity.getRoomEntities());
					List<RoomEntity> roomEntities2 = propertyEntity.getRoomEntities().stream().collect(Collectors.toList());
					roomEntities2.parallelStream().forEach(roomEntity -> {
					//for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
						
						if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
							System.err.println("roomEntity ==>> "+roomEntity);
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								
								FilterRoomModel filterRoomModel = new FilterRoomModel();
								filterRoomModel.setRoomId(roomEntity.getRoomId());
								
								if(Integer.parseInt(roomEntity.getNumOfBed()) >= noOfGuest) {
									filterRoomModel.setBedAvailable(Integer.parseInt(roomEntity.getNumOfBed()));
									filterRoomModel.setBedAllocated(noOfGuest);
								} else {
									filterRoomModel.setBedAvailable(Integer.parseInt(roomEntity.getNumOfBed()));
									filterRoomModel.setBedAllocated(Integer.parseInt(roomEntity.getNumOfBed()) - noOfGuest);
								}
								
								filteredRooms.put(roomEntity.getOraRoomName(), filterRoomModel);
							}
						}
					});
				}
				
				Map<String, List<BookingVsRoomModel>> roomByOraName = callBookingService(String.valueOf(propertyEntity.getPropertyId()), filterCiteriaModel.getCheckInDate(), Accommodation.SHARED.name());
				if(CollectionUtils.isEmpty(roomByOraName)) {
					int guestCanHave = 0;
					for (Map.Entry<String, FilterRoomModel> entry : filteredRooms.entrySet()) {
						guestCanHave = guestCanHave + entry.getValue().getBedAvailable();
				    }
					
					if(guestCanHave >= noOfGuest) {
						flag = true;
					}
				} else {
					int guestCanHave = 0;
					for (Map.Entry<String, FilterRoomModel> entry : filteredRooms.entrySet()) {
						if(roomByOraName.containsKey(entry.getKey())) {
							int maxBedBooked = 0;
							List<BookingVsRoomModel> roomBooked = roomByOraName.get(entry.getKey());
							for(BookingVsRoomModel bookingVsRoomModel : roomBooked) {
								if(maxBedBooked < Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed())) {
									maxBedBooked = Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed());
								}
							}
							guestCanHave = guestCanHave + entry.getValue().getBedAvailable() - maxBedBooked;
						} else {
							guestCanHave = guestCanHave + entry.getValue().getBedAvailable();
						}
				    }
					
					if(guestCanHave >= noOfGuest) {
						flag = true;
					}
				}
				
			} else { // Customer Wants PRIVATE Rooms
				
				Map<String, List<BookingVsRoomModel>> roomByOraName = callBookingService(String.valueOf(propertyEntity.getPropertyId()), filterCiteriaModel.getCheckInDate(), Accommodation.PRIVATE.name());
				AtomicInteger guestCanHave = new AtomicInteger(0);
				if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
					System.err.println("propertyEntity.getRoomEntities() ==>> "+propertyEntity.getRoomEntities());
					List<RoomEntity> roomEntities2 = propertyEntity.getRoomEntities().stream().collect(Collectors.toList());
					roomEntities2.parallelStream().forEach(roomEntity -> {
					//for(RoomEntity roomEntity : propertyEntity.getRoomEntities()) {
						
						if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
							System.err.println("roomEntity ==>> "+roomEntity);
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.PRIVATE.name())) { //private
								if(CollectionUtils.isEmpty(roomByOraName)) {
									guestCanHave.addAndGet(Integer.parseInt(roomEntity.getNoOfGuest()));
									FilterRoomModel filterRoomModel = new FilterRoomModel();
									filterRoomModel.setRoomId(roomEntity.getRoomId());
									filterRoomModel.setNumOfAdult(Integer.parseInt(roomEntity.getNoOfGuest()));
									filterRoomModel.setNumOfChild(Integer.parseInt(roomEntity.getNoOfGuest()));
									filteredRooms.put(roomEntity.getOraRoomName(), filterRoomModel);
								} else {
									if(!roomByOraName.containsKey(roomEntity.getOraRoomName())) {
										guestCanHave.addAndGet(Integer.parseInt(roomEntity.getNoOfGuest()));
										FilterRoomModel filterRoomModel = new FilterRoomModel();
										filterRoomModel.setRoomId(roomEntity.getRoomId());
										filterRoomModel.setNumOfAdult(Integer.parseInt(roomEntity.getNoOfGuest()));
										filterRoomModel.setNumOfChild(Integer.parseInt(roomEntity.getNoOfGuest()));
										filteredRooms.put(roomEntity.getOraRoomName(), filterRoomModel);
									}
								}
								
							}
						}
					});
				}
				
				if(guestCanHave.get() >= noOfGuest) {
					flag = true;
				}
			}
			
			filterResult.put(flag, filteredRooms);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("Exception in filterBycheckInDate -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("filterBycheckInDate -- END");
		}
		
		return filterResult;
	}
	
	private Map<String, List<BookingVsRoomModel>> callBookingService(String propertyId, String checkInDate, String accomodationType) {
		
		if (logger.isInfoEnabled()) {
			logger.info("callBookingService -- START");
		}
		
		Map<String, List<BookingVsRoomModel>> roomByOraName = new LinkedHashMap<>();
		try {
			BookingModel bookingModel = new BookingModel();
			bookingModel.setPropertyId(propertyId);
			bookingModel.setCheckinDate(checkInDate);
			bookingModel.setAccomodationType(accomodationType);
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("booking.server.url") + "get-bookings", bookingModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			gson = new Gson();
			Type listType = new TypeToken<List<BookingModel>>() {}.getType();
			List<BookingModel> bookingModels = gson.fromJson(jsonString, listType);

			
			if (!CollectionUtils.isEmpty(bookingModels)) {

				List<BookingVsRoomModel> bookingVsRoomModels = new ArrayList<>();
				for (BookingModel bookingModel2 : bookingModels) {
					if (Objects.nonNull(bookingModel2) && !CollectionUtils.isEmpty(bookingModel2.getBookingVsRooms())) {
						bookingVsRoomModels.addAll(bookingModel2.getBookingVsRooms());
					}
				}

				if (!CollectionUtils.isEmpty(bookingVsRoomModels)) {
					for (BookingVsRoomModel bookingVsRoomModel : bookingVsRoomModels) {
						if (roomByOraName.isEmpty()) { // First Time
							List<BookingVsRoomModel> mapBookingVsRoomModels = new ArrayList<>();
							mapBookingVsRoomModels.add(bookingVsRoomModel);
							roomByOraName.put(bookingVsRoomModel.getOraRoomName(), mapBookingVsRoomModels);
						} else {
							if (roomByOraName.containsKey(bookingVsRoomModel.getOraRoomName())) { // KEY Present
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
		} catch(HttpClientErrorException e) {
			System.err.println(e.getRawStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("roomByOraName ==>> "+roomByOraName);
		
		if (logger.isInfoEnabled()) {
			logger.info("callBookingService -- END");
		}
		
		return roomByOraName;
	}
	
	private List<String> priceCalculation(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel, Map<String, FilterRoomModel> filteredRooms) {
		
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
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(filteredRooms.containsKey(roomEntity.getOraRoomName())) { // Room Taken into Consideration
					
					System.err.println("priceCalculation roomEntity ==>> "+roomEntity);
					if(Objects.nonNull(roomEntity)) {
						
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
						if(Util.getDateDiff1(filterCiteriaModel.getCheckInDate()) == 0) { // Current Date
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
	
	public PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity, FilterCiteriaModel filterCiteriaModel, Map<String, FilterRoomModel> filteredRooms) {
		
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
		List<String> prices = priceCalculation(propertyEntity, filterCiteriaModel, filteredRooms);
		propertyListViewModel.setTotalPrice(prices.get(0));
		propertyListViewModel.setDiscountedPrice(prices.get(1));
		
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
		
		// TODO Bookmark
		propertyListViewModel.setIsBookmark(false);
		
		// TODO Analysis Text
		propertyListViewModel.setAnalyticsText("Booked "+propertyEntity.getRoomEntities().size()+ " times in the last 24 hours");
		
		if (logger.isInfoEnabled()) {
			logger.info("setPropertyListView -- END");
		}
		
		return propertyListViewModel;
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
					ratings.add(0, String.valueOf(Math.round(totalRating / ratingTypes.size() / userReviewModels.size()) * 100D / 100D)); // Rating
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
}
