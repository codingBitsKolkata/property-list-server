package com.orastays.property.propertylist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.orastays.property.propertylist.converter.AmenitiesConverter;
import com.orastays.property.propertylist.converter.CancellationSlabConverter;
import com.orastays.property.propertylist.converter.ConvenienceConverter;
import com.orastays.property.propertylist.converter.GstSlabConverter;
import com.orastays.property.propertylist.converter.OfferConverter;
import com.orastays.property.propertylist.converter.PriceDropConverter;
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
import com.orastays.property.propertylist.converter.RoomVsCancellationConverter;
import com.orastays.property.propertylist.converter.RoomVsImageConverter;
import com.orastays.property.propertylist.converter.RoomVsMealConverter;
import com.orastays.property.propertylist.converter.RoomVsSpecialitiesConverter;
import com.orastays.property.propertylist.converter.SpaceRuleConverter;
import com.orastays.property.propertylist.converter.SpecialExperienceConverter;
import com.orastays.property.propertylist.converter.SpecialtiesConverter;
import com.orastays.property.propertylist.converter.StayTypeConverter;
import com.orastays.property.propertylist.dao.AmenitiesDAO;
import com.orastays.property.propertylist.dao.CancellationSlabDAO;
import com.orastays.property.propertylist.dao.ConvenienceDAO;
import com.orastays.property.propertylist.dao.GstSlabDAO;
import com.orastays.property.propertylist.dao.OfferDAO;
import com.orastays.property.propertylist.dao.PriceDropDAO;
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
import com.orastays.property.propertylist.dao.RoomVsCancellationDAO;
import com.orastays.property.propertylist.dao.RoomVsImageDAO;
import com.orastays.property.propertylist.dao.RoomVsMealDAO;
import com.orastays.property.propertylist.dao.RoomVsSpecialitiesDAO;
import com.orastays.property.propertylist.dao.SpaceRuleDAO;
import com.orastays.property.propertylist.dao.SpecialExperienceDAO;
import com.orastays.property.propertylist.dao.SpecialtiesDAO;
import com.orastays.property.propertylist.dao.StayTypeDAO;
import com.orastays.property.propertylist.helper.MessageUtil;
import com.orastays.property.propertylist.service.ConvenienceService;
import com.orastays.property.propertylist.service.GstSlabService;
import com.orastays.property.propertylist.service.PropertyListService;
import com.orastays.property.propertylist.utils.FilterRoomsUtil;
import com.orastays.property.propertylist.validation.BookingValidation;
import com.orastays.property.propertylist.validation.HomeValidation;
import com.orastays.property.propertylist.validation.PropertyListValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected PropertyListValidation propertyListValidation;

	@Autowired
	protected HomeValidation homeValidation;

	@Autowired
	protected BookingValidation bookingValidation;

	@Autowired
	protected PropertyTypeDAO propertyTypeDAO;

	@Autowired
	protected PropertyTypeConverter propertyTypeConverter;

	@Autowired
	protected StayTypeConverter stayTypeConverter;

	@Autowired
	protected StayTypeDAO stayTypeDAO;

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
	protected RoomVsSpecialitiesConverter roomVsSpecialitiesConverter;

	@Autowired
	protected RoomVsSpecialitiesDAO roomVsSpecialitiesDAO;

	@Autowired
	protected RoomVsMealConverter roomVsMealConverter;

	@Autowired
	protected OfferConverter offerConverter;

	@Autowired
	protected OfferDAO offerDAO;

	@Autowired
	protected RoomVsMealDAO roomVsMealDAO;

	@Autowired
	protected RoomVsCancellationConverter roomVsCancellationConverter;

	@Autowired
	protected RoomVsCancellationDAO roomVsCancellationDAO;

	@Autowired
	protected PropertyListService propertyListService;

	@Autowired
	protected FilterRoomsUtil filterRoomsUtil;

	@Autowired
	protected ConvenienceService convenienceService;

	@Autowired
	protected GstSlabService gstSlabService;

	@Autowired
	protected GstSlabDAO gstSlabDAO;

	@Autowired
	protected GstSlabConverter gstSlabConverter;

	@Autowired
	protected ConvenienceDAO convenienceDAO;

	@Autowired
	protected ConvenienceConverter convenienceConverter;

}