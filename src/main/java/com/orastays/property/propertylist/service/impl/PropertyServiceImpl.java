package com.orastays.property.propertylist.service.impl;

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
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.entity.PropertyVsSpaceRuleEntity;
import com.orastays.property.propertylist.entity.RoomEntity;
import com.orastays.property.propertylist.entity.RoomVsAmenitiesEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
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
			
			if(!CollectionUtils.isEmpty(propertyEntities)) {
				
				for(PropertyEntity propertyEntity : propertyEntities) {
					
					// Filter by propertyTypeId // Mandatory
					if (StringUtils.equals(filterCiteriaModel.getPropertyTypeId(), String.valueOf(propertyEntity.getPropertyTypeEntity().getPropertyTypeId()))) {
						// Filter by location // Mandatory
						if(filterByLocation(propertyEntity, filterCiteriaModel)) {
							// Filter by checkInDate // Mandatory
							// Filter by checkOutDate // Mandatory
							// Filter by roomModels // Mandatory
							if(filterBycheckInDate(propertyEntity, filterCiteriaModel)) {
								
 								if(!CollectionUtils.isEmpty(filterCiteriaModel.getRatings()) && filterByRating(propertyEntity, filterCiteriaModel)) {
									
									// Filter by amenitiesModels
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels()) && filterByAmmenities(propertyEntity, filterCiteriaModel)) {
										
										// Filter by budgets
										if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets()) && filterByBudget(propertyEntity, filterCiteriaModel)) {
											
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										} else {
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										}
									} else {
										// Filter by budgets
										if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets()) && filterByBudget(propertyEntity, filterCiteriaModel)) {
											
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										} else {
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										}
									}
								} else {
									// Filter by amenitiesModels
									if(!CollectionUtils.isEmpty(filterCiteriaModel.getAmenitiesModels()) && filterByAmmenities(propertyEntity, filterCiteriaModel)) {
										
										// Filter by budgets
										if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets()) && filterByBudget(propertyEntity, filterCiteriaModel)) {
											
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										} else {
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										}
									} else {
										// Filter by budgets
										if(!CollectionUtils.isEmpty(filterCiteriaModel.getBudgets()) && filterByBudget(propertyEntity, filterCiteriaModel)) {
											
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										} else {
											// Filter by popularLocations
											if(!CollectionUtils.isEmpty(filterCiteriaModel.getPopularLocations()) && filterByPopularLocation(propertyEntity, filterCiteriaModel)) {
												
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											} else {
												// Filter by spaceRuleModels // Couple Friendly, Pet Friendly
												if(!CollectionUtils.isEmpty(filterCiteriaModel.getSpaceRuleModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
													
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySpaceRule(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												} else {
													// Filter by pgCategorySexModels // Male/Female
													if(!CollectionUtils.isEmpty(filterCiteriaModel.getPgCategorySexModels()) && filterBySex(propertyEntity, filterCiteriaModel)) {
														
														propertyListViewModels.add(setPropertyListView(propertyEntity));
													} else {
														break;
													}
												}
											}
										}
									}
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
	
	private PropertyListViewModel setPropertyListView(PropertyEntity propertyEntity) {
		
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
				} else {
					propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_BASIC);
				}
			}
		} else {
			propertyListViewModel.setRoomStandard(PropertyListConstant.ROOM_STANDARD_BASIC);
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
		
		/*propertyListViewModel.setTotalPrice(totalPrice);
		propertyListViewModel.setDiscountedPrice(discountedPrice);*/
		
		propertyListViewModel.setMealFlag(false);
		if(!CollectionUtils.isEmpty(propertyEntity.getRoomEntities())) {
			for(RoomEntity roomEntity :propertyEntity.getRoomEntities()) {
				if(!CollectionUtils.isEmpty(roomEntity.getRoomVsMealEntities())) {
					propertyListViewModel.setMealFlag(true);
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
	
	private List<String> getRatingAndReview(PropertyEntity propertyEntity) {
		
		if (logger.isInfoEnabled()) {
			logger.info("getRatingAndReview -- START");
		}
		
		List<String> ratings = new ArrayList<>();
		try {
			PropertyModel propertyModel = new PropertyModel();
			propertyModel.setPropertyId(String.valueOf(propertyEntity.getPropertyId()));
			ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("review.server.url") +"fetch-review", propertyModel, ResponseModel.class);
			List<UserReviewModel> userReviewModels = new ArrayList<UserReviewModel>();
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			userReviewModels = (List<UserReviewModel>) gson.fromJson(jsonString, UserReviewModel.class);
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
				ratings.add(0, String.valueOf(totalRating / ratingTypes.size())); // Rating
				
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
			BookingModel bookingModel2 = (BookingModel) responseModel.getResponseBody();
			if (logger.isInfoEnabled()) {
				logger.info("bookingModel2 ==>> "+bookingModel2);
			}
			
			if(Objects.isNull(bookingModel2)) {
				flag = true;
			} else {
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
			List<UserReviewModel> userReviewModels = new ArrayList<UserReviewModel>();
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
