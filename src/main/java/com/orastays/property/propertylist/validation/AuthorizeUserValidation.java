package com.orastays.property.propertylist.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.orastays.property.propertylist.dao.AccommodationDAO;
import com.orastays.property.propertylist.dao.AmenitiesDAO;
import com.orastays.property.propertylist.dao.CancellationSlabDAO;
import com.orastays.property.propertylist.dao.DiscountCategoryHostDAO;
import com.orastays.property.propertylist.dao.DiscountCategoryOraDAO;
import com.orastays.property.propertylist.dao.DocumentDAO;
import com.orastays.property.propertylist.dao.MealCategoryDAO;
import com.orastays.property.propertylist.dao.MealDaysDAO;
import com.orastays.property.propertylist.dao.MealPriceCategoryDAO;
import com.orastays.property.propertylist.dao.MealTypeDAO;
import com.orastays.property.propertylist.dao.PGCategorySexDAO;
import com.orastays.property.propertylist.dao.PriceTypeDAO;
import com.orastays.property.propertylist.dao.PropertyTypeDAO;
import com.orastays.property.propertylist.dao.RoomCategoryDAO;
import com.orastays.property.propertylist.dao.RoomStandardDAO;
import com.orastays.property.propertylist.dao.RoomVsAmenitiesDAO;
import com.orastays.property.propertylist.dao.RoomVsBedDAO;
import com.orastays.property.propertylist.dao.RoomVsCancellationDAO;
import com.orastays.property.propertylist.dao.SpaceRuleDAO;
import com.orastays.property.propertylist.dao.SpecialExperienceDAO;
import com.orastays.property.propertylist.dao.SpecialtiesDAO;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.MessageUtil;
import com.orastays.property.propertylist.model.CommonModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.UserModel;

@Component
public class AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(AuthorizeUserValidation.class);
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected PropertyTypeDAO propertyTypeDAO;
	
	@Autowired
	protected SpaceRuleDAO spaceRuleDAO;
	
	@Autowired
	protected SpecialExperienceDAO specialExperienceDAO;
	
	@Autowired
	protected PGCategorySexDAO pgCategorySexDAO;
	
	@Autowired
	protected DocumentDAO documentDAO;
	
	@Autowired
	protected AccommodationDAO accommodationDAO;
	
	@Autowired
	protected DiscountCategoryOraDAO discountCategoryOraDAO;
	
	@Autowired
	protected RoomCategoryDAO roomCategoryDAO;
	
	@Autowired
	protected RoomStandardDAO roomStandardDAO;
	
	@Autowired
	protected RoomVsBedDAO roomVsBedDAO;
	
	@Autowired
	protected RoomVsAmenitiesDAO roomVsAmenitiesDAO;
	
	@Autowired
	protected RoomVsCancellationDAO roomVsCancellationDAO;
	
	@Autowired
	protected CancellationSlabDAO cancellationSlabDAO;
	
	@Autowired
	protected PriceTypeDAO priceTypeDAO;
	
	@Autowired
	protected DiscountCategoryHostDAO discountCategoryHostDAO;	
	
	@Autowired
	protected SpecialtiesDAO specialtiesDAO;
	
	@Autowired
	protected MealCategoryDAO mealCategoryDAO;
	
	@Autowired
	protected MealDaysDAO mealDaysDAO;
	
	@Autowired
	protected MealPriceCategoryDAO mealPriceCategoryDAO;
	
	@Autowired
	protected MealTypeDAO mealTypeDAO;
	
	@Autowired
	protected AmenitiesDAO amenitiesDAO;
	
	public UserModel getUserDetails(String userToken) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel userModel = null;
		try {
			ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("auth.server.url") +"check-token?userToken="+userToken, ResponseModel.class);
			userModel = (UserModel) responseModel.getResponseBody();
			if(Objects.isNull(userModel)) {
				exceptions.put(messageUtil.getBundle("token.invalid.code"), new Exception(messageUtil.getBundle("token.invalid.message")));
			}
			
			if (logger.isInfoEnabled()) {
				logger.info("userModel ==>> "+userModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Disabled the below line to pass the Token Validation
			exceptions.put(messageUtil.getBundle("token.invalid.code"), new Exception(messageUtil.getBundle("token.invalid.message")));
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- END");
		}
		
		return userModel;
	}
	
	public CommonModel validateLanguage(String languageId) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("validateLanguage -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		CommonModel commonModel = null;
		try {
			ResponseModel responseModel = restTemplate.getForObject(messageUtil.getBundle("auth.server.url") +"check-language?languageId="+languageId, ResponseModel.class);
			commonModel = (CommonModel) responseModel.getResponseBody();
			if(Objects.isNull(commonModel)) {
				exceptions.put(messageUtil.getBundle("language.id.invalid.code"), new Exception(messageUtil.getBundle("language.id.invalid.message")));
			}
			
			if (logger.isInfoEnabled()) {
				logger.info("commonModel ==>> "+commonModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Disabled the below line to pass the Language Validation
			exceptions.put(messageUtil.getBundle("language.id.invalid.code"), new Exception(messageUtil.getBundle("language.id.invalid.message")));
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateLanguage -- END");
		}
		
		return commonModel;
	}
}