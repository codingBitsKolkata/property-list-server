package com.orastays.property.propertylist.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.orastays.property.propertylist.entity.PropertyEntity;
import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.PropertyModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.booking.BookingModel;
import com.orastays.property.propertylist.model.booking.PaymentModel;
import com.orastays.property.propertylist.model.user.UserModel;
import com.orastays.property.propertylist.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl extends BaseServiceImpl implements BookingService {

	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	@Override
	public PaymentModel propertyBooking(FilterCiteriaModel filterCiteriaModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("propertyBooking -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		PaymentModel paymentModel = null;
		List<Object> obj = bookingValidation.validatePropertyBooking(filterCiteriaModel);
		UserModel userModel = (UserModel) obj.get(0);
		PropertyEntity propertyEntity = (PropertyEntity) obj.get(1);
		PropertyModel propertyModel = propertyListService.fetchPropertyDetails(filterCiteriaModel);
		if(Objects.nonNull(propertyModel)) {
			BookingModel bookingModel = new BookingModel();
			// TODO Set BookingModel
			
			
			Gson gson = new Gson();
			try {
				ResponseModel responseModel = restTemplate.postForObject(messageUtil.getBundle("booking.server.url") +"add-booking", bookingModel, ResponseModel.class);
				String jsonString = gson.toJson(responseModel.getResponseBody());
				paymentModel = gson.fromJson(jsonString, PaymentModel.class);
				
				if (logger.isInfoEnabled()) {
					logger.info("paymentModel ==>> "+paymentModel);
				}
				System.err.println("paymentModel ==>> "+paymentModel);
				
			} catch(HttpClientErrorException e) {
				String jsonString = gson.toJson(e.getResponseBodyAsString());
				ResponseModel responseModel = gson.fromJson(jsonString, ResponseModel.class);
				exceptions.put(responseModel.getResponseCode(), new Exception(responseModel.getResponseMessage()));
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					logger.info("Exception in propertyBooking -- "+Util.errorToString(e));
				}
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- END");
		}
		
		return paymentModel;
	}
}