package com.orastays.property.propertylist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.property.propertylist.converter.AccommodationConverter;
import com.orastays.property.propertylist.converter.AmenitiesConverter;
import com.orastays.property.propertylist.converter.AmenitiesTypeConverter;
import com.orastays.property.propertylist.converter.CancellationSlabConverter;
import com.orastays.property.propertylist.converter.PGCategorySexConverter;
import com.orastays.property.propertylist.converter.PriceDropConverter;
import com.orastays.property.propertylist.converter.PriceTypeConverter;
import com.orastays.property.propertylist.converter.PropertyConverter;
import com.orastays.property.propertylist.converter.PropertyTypeConverter;
import com.orastays.property.propertylist.converter.PropertyVsDescriptionConverter;
import com.orastays.property.propertylist.converter.PropertyVsDocumentConverter;
import com.orastays.property.propertylist.converter.PropertyVsGuestAccessConverter;
import com.orastays.property.propertylist.converter.PropertyVsImageConverter;
import com.orastays.property.propertylist.converter.PropertyVsNearbyConverter;
import com.orastays.property.propertylist.converter.PropertyVsPriceDropConverter;
import com.orastays.property.propertylist.converter.PropertyVsSpaceRuleConverter;
import com.orastays.property.propertylist.converter.PropertyVsSpecialExperienceConverter;
import com.orastays.property.propertylist.converter.RoomCategoryConverter;
import com.orastays.property.propertylist.converter.RoomConverter;
import com.orastays.property.propertylist.converter.RoomVsAmenitiesConverter;
import com.orastays.property.propertylist.converter.RoomVsBedConverter;
import com.orastays.property.propertylist.converter.RoomVsCancellationConverter;
import com.orastays.property.propertylist.converter.RoomVsHostDiscountConverter;
import com.orastays.property.propertylist.converter.RoomVsImageConverter;
import com.orastays.property.propertylist.converter.RoomVsMealConverter;
import com.orastays.property.propertylist.converter.RoomVsOraDiscountConverter;
import com.orastays.property.propertylist.converter.RoomVsOraPricePercentageConverter;
import com.orastays.property.propertylist.converter.RoomVsPriceConverter;
import com.orastays.property.propertylist.converter.RoomVsSpecialitiesConverter;
import com.orastays.property.propertylist.converter.SpaceRuleConverter;
import com.orastays.property.propertylist.converter.SpecialExperienceConverter;
import com.orastays.property.propertylist.converter.SpecialtiesConverter;
import com.orastays.property.propertylist.converter.StayTypeConverter;
import com.orastays.property.propertylist.converter.UserVsAccountConverter;
import com.orastays.property.propertylist.dao.AccommodationDAO;
import com.orastays.property.propertylist.dao.AmenitiesDAO;
import com.orastays.property.propertylist.dao.AmenitiesTypeDAO;
import com.orastays.property.propertylist.dao.CancellationSlabDAO;
import com.orastays.property.propertylist.dao.DiscountCategoryOraDAO;
import com.orastays.property.propertylist.dao.PGCategorySexDAO;
import com.orastays.property.propertylist.dao.PriceDropDAO;
import com.orastays.property.propertylist.dao.PriceTypeDAO;
import com.orastays.property.propertylist.dao.PropertyDAO;
import com.orastays.property.propertylist.dao.PropertyTypeDAO;
import com.orastays.property.propertylist.dao.PropertyVsDescriptionDAO;
import com.orastays.property.propertylist.dao.PropertyVsDocumentDAO;
import com.orastays.property.propertylist.dao.PropertyVsGuestAccessDAO;
import com.orastays.property.propertylist.dao.PropertyVsImageDAO;
import com.orastays.property.propertylist.dao.PropertyVsNearbyDAO;
import com.orastays.property.propertylist.dao.PropertyVsPriceDropDAO;
import com.orastays.property.propertylist.dao.PropertyVsSpaceRuleDAO;
import com.orastays.property.propertylist.dao.PropertyVsSpecialExperienceDAO;
import com.orastays.property.propertylist.dao.RoomCategoryDAO;
import com.orastays.property.propertylist.dao.RoomDAO;
import com.orastays.property.propertylist.dao.RoomVsAmenitiesDAO;
import com.orastays.property.propertylist.dao.RoomVsBedDAO;
import com.orastays.property.propertylist.dao.RoomVsCancellationDAO;
import com.orastays.property.propertylist.dao.RoomVsHostDiscountDAO;
import com.orastays.property.propertylist.dao.RoomVsImageDAO;
import com.orastays.property.propertylist.dao.RoomVsMealDAO;
import com.orastays.property.propertylist.dao.RoomVsOraDiscountDAO;
import com.orastays.property.propertylist.dao.RoomVsOraPricePercentageDAO;
import com.orastays.property.propertylist.dao.RoomVsPriceDAO;
import com.orastays.property.propertylist.dao.RoomVsSpecialitiesDAO;
import com.orastays.property.propertylist.dao.SpaceRuleDAO;
import com.orastays.property.propertylist.dao.SpecialExperienceDAO;
import com.orastays.property.propertylist.dao.SpecialtiesDAO;
import com.orastays.property.propertylist.dao.StayTypeDAO;
import com.orastays.property.propertylist.dao.UserVsAccountDAO;
import com.orastays.property.propertylist.validation.PropertyListValidation;

public abstract class BaseServiceImpl {
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected PropertyListValidation propertyListValidation;
	
	@Autowired
	protected PropertyTypeDAO propertyTypeDAO;
	
	@Autowired
	protected PropertyTypeConverter propertyTypeConverter;
	
	@Autowired
	protected StayTypeConverter stayTypeConverter;
	
	@Autowired
	protected StayTypeDAO stayTypeDAO;
	
	@Autowired
	protected AccommodationConverter accommodationConverter;
	
	@Autowired
	protected AccommodationDAO accommodationDAO;
	
	@Autowired
	protected PGCategorySexConverter pgCategorySexConverter;
	
	@Autowired
	protected PGCategorySexDAO pgCategorySexDAO;
	
	@Autowired
	protected AmenitiesTypeConverter amenitiesTypeConverter;
	
	@Autowired
	protected AmenitiesTypeDAO amenitiesTypeDAO;
	
	@Autowired
	protected AmenitiesConverter amenitiesConverter;
	
	@Autowired
	protected AmenitiesDAO amenitiesDAO;
	
	@Autowired
	protected SpecialExperienceConverter specialExperienceConverter;
	
	@Autowired
	protected SpecialExperienceDAO specialExperienceDAO;
	
	@Autowired
	protected SpaceRuleConverter spaceRuleConverter;
	
	@Autowired
	protected SpaceRuleDAO spaceRuleDAO;
	
	@Autowired
	protected SpecialtiesConverter specialtiesConverter;
	
	@Autowired
	protected SpecialtiesDAO specialtiesDAO;
	
	@Autowired
	protected RoomCategoryConverter roomCategoryConverter;
	
	@Autowired
	protected RoomCategoryDAO roomCategoryDAO;
	
	@Autowired
	protected PriceTypeConverter priceTypeConverter;
	
	@Autowired
	protected PriceTypeDAO priceTypeDAO;
	
	@Autowired
	protected CancellationSlabConverter cancellationSlabConverter;
	
	@Autowired
	protected CancellationSlabDAO cancellationSlabDAO;
	
	@Autowired
	protected PriceDropDAO priceDropDAO;
	
	@Autowired
	protected PriceDropConverter priceDropConverter;
	
	@Autowired
	protected PropertyConverter propertyConverter;
	
	@Autowired
	protected PropertyDAO propertyDAO;
	
	@Autowired
	protected UserVsAccountConverter userVsAccountConverter;
	
	@Autowired
	protected UserVsAccountDAO userVsAccountDAO;
	
	@Autowired
	protected PropertyVsDescriptionConverter propertyVsDescriptionConverter;
	
	@Autowired
	protected PropertyVsDescriptionDAO propertyVsDescriptionDAO;
	
	@Autowired
	protected PropertyVsDocumentConverter propertyVsDocumentConverter;
	
	@Autowired
	protected PropertyVsDocumentDAO propertyVsDocumentDAO;
	
	@Autowired
	protected PropertyVsSpecialExperienceConverter pVsSpecialExperienceConverter;
	
	@Autowired
	protected PropertyVsSpecialExperienceDAO propertyVsSpecialExperienceDAO;
	
	@Autowired
	protected PropertyVsGuestAccessConverter propertyVsGuestAccessConverter;
	
	@Autowired
	protected PropertyVsGuestAccessDAO propertyVsGuestAccessDAO;
	
	@Autowired
	protected PropertyVsImageConverter propertyVsImageConverter;
	
	@Autowired
	protected PropertyVsImageDAO propertyVsImageDAO;
	
	@Autowired
	protected PropertyVsNearbyConverter propertyVsNearbyConverter;
	
	@Autowired
	protected PropertyVsNearbyDAO propertyVsNearbyDAO;
	
	@Autowired
	protected PropertyVsPriceDropConverter propertyVsPriceDropConverter;
	
	@Autowired
	protected PropertyVsPriceDropDAO propertyVsPriceDropDAO;
	
	@Autowired
	protected PropertyVsSpaceRuleConverter propertyVsSpaceRuleConverter;
	
	@Autowired
	protected PropertyVsSpaceRuleDAO propertyVsSpaceRuleDAO;
	
	@Autowired
	protected RoomConverter roomConverter;
	
	@Autowired
	protected RoomDAO roomDAO;
	
	@Autowired
	protected RoomVsAmenitiesDAO roomVsAmenitiesDAO;
	
	@Autowired
	protected RoomVsAmenitiesConverter roomVsAmenitiesConverter;
	
	@Autowired
	protected RoomVsImageDAO roomVsImageDAO;
	
	@Autowired
	protected RoomVsImageConverter roomVsImageConverter;
	
	@Autowired
	protected RoomVsHostDiscountDAO roomVsHostDiscountDAO;
	
	@Autowired
	protected RoomVsHostDiscountConverter roomVsHostDiscountConverter;
	
	@Autowired
	protected RoomVsOraDiscountConverter roomVsOraDiscountConverter;
	
	@Autowired
	protected RoomVsOraDiscountDAO roomVsOraDiscountDAO;
	
	@Autowired
	protected RoomVsOraPricePercentageConverter roomVsOraPricePercentageConverter;
	
	@Autowired
	protected RoomVsOraPricePercentageDAO roomVsOraPricePercentageDAO;
	
	@Autowired
	protected RoomVsPriceConverter roomVsPriceConverter;
	
	@Autowired
	protected RoomVsPriceDAO roomVsPriceDAO;
	
	@Autowired
	protected RoomVsSpecialitiesConverter roomVsSpecialitiesConverter;
	
	@Autowired
	protected RoomVsSpecialitiesDAO roomVsSpecialitiesDAO;
	
	@Autowired
	protected RoomVsMealConverter roomVsMealConverter;
	
	@Autowired
	protected RoomVsMealDAO roomVsMealDAO;
	
	@Autowired
	protected RoomVsCancellationConverter roomVsCancellationConverter;
	
	@Autowired
	protected RoomVsCancellationDAO roomVsCancellationDAO;
	
	@Autowired
	protected DiscountCategoryOraDAO discountCategoryOraDAO;
	
	@Autowired
	protected RoomVsBedConverter roomVsBedConverter;
	
	@Autowired
	protected RoomVsBedDAO roomVsBedDAO;
}
