package com.orastays.property.propertylist.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.orastays.property.propertyadd.dao.AmenitiesDAO;
import com.orastays.property.propertyadd.dao.CancellationSlabDAO;
import com.orastays.property.propertyadd.dao.DocumentDAO;
import com.orastays.property.propertyadd.dao.HostVsAccountDAO;
import com.orastays.property.propertyadd.dao.MealPlanCatVsMealPlanDAO;
import com.orastays.property.propertyadd.dao.PriceDropDAO;
import com.orastays.property.propertyadd.dao.PropertyTypeDAO;
import com.orastays.property.propertyadd.dao.RoomCategoryDAO;
import com.orastays.property.propertyadd.dao.SpaceRuleDAO;
import com.orastays.property.propertyadd.dao.SpecialExperienceDAO;
import com.orastays.property.propertyadd.dao.SpecialtiesDAO;
import com.orastays.property.propertyadd.dao.StayTypeDAO;
import com.orastays.property.propertyadd.helper.MessageUtil;

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
	protected StayTypeDAO stayTypeDAO;

	@Autowired
	protected HostVsAccountDAO userVsAccountDAO;

	@Autowired
	protected DocumentDAO documentDAO;

	@Autowired
	protected PriceDropDAO priceDropDAO;

	@Autowired
	protected SpaceRuleDAO spaceRuleDAO;

	@Autowired
	protected RoomCategoryDAO roomCategoryDAO;

	@Autowired
	protected AmenitiesDAO amenitiesDAO;

	@Autowired
	protected CancellationSlabDAO cancellationSlabDAO;

	@Autowired
	protected MealPlanCatVsMealPlanDAO mealPlanCatVsMealPlanDAO;

	@Autowired
	protected SpecialtiesDAO specialtiesDAO;

	@Autowired
	protected PropertyTypeConverter propertyTypeConverter;

	@Autowired
	protected StayTypeConverter stayTypeConverter;

	@Autowired
	protected HostVsAccountConverter userVsAccountConverter;

	@Autowired
	protected PropertyVsDocumentConverter propertyVsDocumentConverter;

	@Autowired
	protected PropertyVsDescriptionConverter propertyVsDescriptionConverter;

	@Autowired
	protected PropertyVsGuestAccessConverter propertyVsGuestAccessConverter;

	@Autowired
	protected PropertyVsImageConverter propertyVsImageConverter;

	@Autowired
	protected PropertyVsNearbyConverter propertyVsNearbyConverter;

	@Autowired
	protected PropertyVsPriceDropConverter propertyVsPriceDropConverter;

	@Autowired
	protected PropertyVsSpaceRuleConverter propertyVsSpaceRuleConverter;

	@Autowired
	protected PropertyVsSpecialExperienceConverter vsSpecialExperienceConverter;

	@Autowired
	protected DocumentConverter documentConverter;

	@Autowired
	protected PriceDropConverter priceDropConverter;

	@Autowired
	protected SpaceRuleConverter spaceRuleConverter;

	@Autowired
	protected SpecialExperienceConverter specialExperienceConverter;

	@Autowired
	protected RoomConverter roomConverter;

	@Autowired
	protected RoomCategoryConverter roomCategoryConverter;

	@Autowired
	protected RoomVsAmenitiesConverter roomVsAmenitiesConverter;

	@Autowired
	protected AmenitiesConverter amenitiesConverter;

	@Autowired
	protected RoomVsCancellationConverter roomVsCancellationConverter;

	@Autowired
	protected CancellationSlabConverter cancellationSlabConverter;

	@Autowired
	protected RoomVsImageConverter roomVsImageConverter;

	@Autowired
	protected RoomVsSpecialitiesConverter roomVsSpecialitiesConverter;

	@Autowired
	protected SpecialtiesConverter specialtiesConverter;

	@Autowired
	protected RoomVsMealConverter roomVsMealConverter;

	@Autowired
	protected MealPlanCatVsMealPlanConverter mealPlanCatVsMealPlanConverter;

	@Autowired
	protected MealPlanCategoryConverter mealPlanCategoryConverter;

	@Autowired
	protected MealPlanConverter mealPlanConverter;

}
