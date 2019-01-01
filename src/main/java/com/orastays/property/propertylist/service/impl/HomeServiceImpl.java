package com.orastays.property.propertylist.service.impl;

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

import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Accommodation;
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
		List<PropertyListViewModel> propertyListViewModels = new ArrayList<>();
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
		
		PropertyListViewModel propertyListViewModel = new PropertyListViewModel();
		/*try {
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
				logger.info("Exception in findByRating -- "+Util.errorToString(e));
			}
		}*/
		
		if (logger.isInfoEnabled()) {
			logger.info("findByRating -- END");
		}
		
		return propertyListViewModel;
	}
	
	private PropertyListViewModel findByBooking(List<PropertyEntity> propertyEntities) {
		
		if (logger.isInfoEnabled()) {
			logger.info("findByBooking -- START");
		}
		
		PropertyListViewModel propertyListViewModel = new PropertyListViewModel();
		/*try {
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
			
			if (CollectionUtils.isEmpty(bookingModels)) {
				List<RoomEntity> roomEntities = new ArrayList<>();
				int roomRequired = filterCiteriaModel.getRoomModels().size();
				System.err.println("roomRequired ==>> "+roomRequired);
				int bedRequired = Integer.parseInt(filterCiteriaModel.getRoomModels().get(0).getNoOfGuest());
				System.err.println("bedRequired ==>> "+bedRequired);
				if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
					System.out.println("propertyEntity ==>> "+propertyEntity);
					System.err.println("propertyEntity.getRoomEntities().size()1 ==>> "+propertyEntity.getRoomEntities().size());
					for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
						
						if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
							System.err.println("roomEntity ==>> "+roomEntity);
							if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
								
								if(Integer.parseInt(roomEntity.getNumOfBed()) <= bedRequired) {
									roomEntities.add(roomEntity);
									propertyEntity.setRoomEntities(null); // Delete All Rooms
									propertyEntity.setRoomEntities(roomEntities); // Insert Available Rooms
									break;
								} else {
									roomEntities.add(roomEntity);
									bedRequired = bedRequired - Integer.parseInt(roomEntity.getNumOfBed());
								}
								
							} else { //private
								if(roomEntities.size() < roomRequired) {
									roomEntities.add(roomEntity);
								} else {
									propertyEntity.setRoomEntities(null); // Delete All Rooms
									propertyEntity.setRoomEntities(roomEntities); // Insert Available Rooms
									break;
								}
							}
						}
					}
				}
				flag = true;
			} else {
				 // Delete the rooms from PropertyEntity which are already booked
				List<BookingVsRoomModel> bookingVsRoomModels = new ArrayList<>();
				for(BookingModel bookingModel2 : bookingModels) {
					if(Objects.nonNull(bookingModel2) && !CollectionUtils.isEmpty(bookingModel2.getBookingVsRooms())) {
						bookingVsRoomModels.addAll(bookingModel2.getBookingVsRooms());
					}
				}
				
				if(!CollectionUtils.isEmpty(bookingVsRoomModels)) {
					Map<String, List<BookingVsRoomModel>> roomByOraName = new LinkedHashMap<>();
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
					
					Set<RoomEntity> roomEntities = new HashSet<>();
					int roomRequired = filterCiteriaModel.getRoomModels().size();
					System.err.println("roomRequired ==>> "+roomRequired);
					int bedRequired = Integer.parseInt(filterCiteriaModel.getRoomModels().get(0).getNoOfGuest());
					System.out.println("bedRequired ==>> "+bedRequired);
					if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
						int count = 0;
						for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
							
							if(roomEntity.getStatus() == Status.ACTIVE.ordinal()) { // Fetching only ACTIVE Rooms
								System.err.println("roomEntity ==>> "+roomEntity);
								
								if(roomByOraName.containsKey(roomEntity.getOraRoomName())) { // KEY Present
									
									if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
										
										int maxBedBooked = 0;
										List<BookingVsRoomModel> roomBooked = roomByOraName.get(roomEntity.getOraRoomName());
										for(BookingVsRoomModel bookingVsRoomModel : roomBooked) {
											if(maxBedBooked < Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed())) {
												maxBedBooked = Integer.parseInt(bookingVsRoomModel.getNumOfSharedBed());
											}
										}
										System.out.println("maxBedBooked ==>> "+maxBedBooked);
										if(Integer.parseInt(roomEntity.getNumOfBed()) <= bedRequired && maxBedBooked <= bedRequired) {
											roomEntities.add(roomEntity);
											propertyEntity.setRoomEntities(null); // Delete All Rooms
											List<RoomEntity> listRoomEntities = new ArrayList<>(roomEntities);
											propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
											flag = true;
											break;
										} else {
											roomEntities.add(roomEntity);
											bedRequired = bedRequired - (Integer.parseInt(roomEntity.getNumOfBed()) - maxBedBooked);
											if(bedRequired <= 0) {
												roomEntities.add(roomEntity);
												propertyEntity.setRoomEntities(null); // Delete All Rooms
												List<RoomEntity> listRoomEntities = new ArrayList<>(roomEntities);
												propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
												flag = true;
												break;
											}
											
										}
										
									} else { //private
										propertyEntity.getRoomEntities().set(count, null); // S
									}
								} else { // KEY NOT Present that means the room is not booked.
									if(StringUtils.equals(roomEntity.getAccomodationName(), Accommodation.SHARED.name())) { //shared
										
										if(Integer.parseInt(roomEntity.getNumOfBed()) <= bedRequired) {
											roomEntities.add(roomEntity);
											propertyEntity.setRoomEntities(null); // Delete All Rooms
											List<RoomEntity> listRoomEntities = new ArrayList<>(roomEntities);
											propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
											flag = true;
											break;
										} else {
											roomEntities.add(roomEntity);
											bedRequired = bedRequired - Integer.parseInt(roomEntity.getNumOfBed());
										}
										
									} else { //private
										if(roomEntities.size() < roomRequired) {
											roomEntities.add(roomEntity);
										} else {
											propertyEntity.setRoomEntities(null); // Delete All Rooms
											List<RoomEntity> listRoomEntities = new ArrayList<>(roomEntities);
											propertyEntity.setRoomEntities(listRoomEntities); // Insert Available Rooms
											flag = true;
											break;
										}
									}
								}
							}
							count++;
						}
					}
				}
			}
			System.out.println("propertyEntity ==>> "+propertyEntity);
			System.err.println("propertyEntity.getRoomEntities().size()2 ==>> "+propertyEntity.getRoomEntities().size());
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("Exception in findByRating -- "+Util.errorToString(e));
			}
		}*/
		
		if (logger.isInfoEnabled()) {
			logger.info("findByRating -- END");
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