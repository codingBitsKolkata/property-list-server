package com.orastays.property.propertylist.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.orastays.property.propertylist.dao.AccommodationDAO;
import com.orastays.property.propertylist.dao.AmenitiesDAO;
import com.orastays.property.propertylist.dao.CancellationSlabDAO;
import com.orastays.property.propertylist.dao.DiscountCategoryHostDAO;
import com.orastays.property.propertylist.dao.DiscountCategoryOraDAO;
import com.orastays.property.propertylist.dao.DocumentDAO;
import com.orastays.property.propertylist.dao.MealCategoryDAO;
import com.orastays.property.propertylist.dao.MealDaysDAO;
import com.orastays.property.propertylist.dao.MealPlanCatVsMealPlanDAO;
import com.orastays.property.propertylist.dao.MealPriceCategoryDAO;
import com.orastays.property.propertylist.dao.MealTypeDAO;
import com.orastays.property.propertylist.dao.PGCategorySexDAO;
import com.orastays.property.propertylist.dao.PriceDropDAO;
import com.orastays.property.propertylist.dao.PriceTypeDAO;
import com.orastays.property.propertylist.dao.PropertyTypeDAO;
import com.orastays.property.propertylist.dao.RoomCategoryDAO;
import com.orastays.property.propertylist.dao.RoomStandardDAO;
import com.orastays.property.propertylist.dao.SpaceRuleDAO;
import com.orastays.property.propertylist.dao.SpecialExperienceDAO;
import com.orastays.property.propertylist.dao.SpecialtiesDAO;
import com.orastays.property.propertylist.dao.StayTypeDAO;
import com.orastays.property.propertylist.dao.UserVsAccountDAO;
import com.orastays.property.propertylist.helper.MessageUtil;

public class CommonConverter {

	@Autowired
	protected ModelMapper modelMapper;

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected PropertyTypeDAO propertyTypeDAO;

	@Autowired
	protected SpecialExperienceDAO specialExperienceDAO;

	@Autowired
	protected PGCategorySexDAO pgCategorySexDAO;

	@Autowired
	protected StayTypeDAO stayTypeDAO;
	
	@Autowired
	protected UserVsAccountDAO userVsAccountDAO;
	
	@Autowired
	protected DocumentDAO documentDAO;
	
	@Autowired
	protected PriceDropDAO priceDropDAO;
	
	@Autowired
	protected SpaceRuleDAO spaceRuleDAO;
	
	@Autowired
	protected AccommodationDAO accommodationDAO;
	
	@Autowired
	protected RoomCategoryDAO roomCategoryDAO;
	
	@Autowired
	protected RoomStandardDAO roomStandardDAO;
	
	@Autowired
	protected AmenitiesDAO amenitiesDAO;
	
	@Autowired
	protected CancellationSlabDAO cancellationSlabDAO;
	
	@Autowired
	protected DiscountCategoryHostDAO discountCategoryHostDAO;
	
	@Autowired
	protected MealCategoryDAO mealCategoryDAO;
	
	@Autowired
	protected MealDaysDAO mealDaysDAO;
	
	@Autowired
	protected MealPlanCatVsMealPlanDAO mealPlanCatVsMealPlanDAO;
	
	@Autowired
	protected MealPriceCategoryDAO mealPriceCategoryDAO;
	
	@Autowired
	protected MealTypeDAO mealTypeDAO; 
	
	@Autowired
	protected DiscountCategoryOraDAO discountCategoryOraDAO;
	
	@Autowired
	protected PriceTypeDAO priceTypeDAO;
	
	@Autowired
	protected SpecialtiesDAO specialtiesDAO;
	
}
