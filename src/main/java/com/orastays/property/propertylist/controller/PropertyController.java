package com.orastays.property.propertylist.controller;

import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.helper.PropertyAddConstant;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.AccommodationModel;
import com.orastays.property.propertylist.model.CommonModel;
import com.orastays.property.propertylist.model.PropertyTypeModel;
import com.orastays.property.propertylist.model.ResponseModel;
import com.orastays.property.propertylist.model.StayTypeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "property", description = "Rest API for Property Add Form", tags = "Property Add Form API")
public class PropertyController extends BaseController{
	
	private static final Logger logger = LogManager.getLogger(PropertyController.class);
	
	@PostMapping(value = "/fetch-property-types", produces = "application/json")
	@ApiOperation(value = "Property Type Listing", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 202, message = "Token Required"),
			@ApiResponse(code = 203, message = "Token Expires!!!Please login to continue..."),
			@ApiResponse(code = 204, message = "Language Id Required"),
			@ApiResponse(code = 204, message = "Invalid Language ID") })
	public ResponseEntity<ResponseModel> fetchPropertyTypes(@RequestBody CommonModel commonModel) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyTypes -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		
		try {
		
			List<PropertyTypeModel> propertyTypeModels = propertyService.fetchPropertyTypes(commonModel);
			responseModel.setResponseBody(propertyTypeModels);
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_MESSAGE));
			
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, PropertyAddConstant.OUTGOING, "Fetch Property Types", request);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPropertyTypes -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseModel> fetchStayTypeList(@RequestBody CommonModel commonModel) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchStayTypeList -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		
		try {
		
			List<StayTypeModel> stayTypeModels = propertyService.fetchStayTypeList(commonModel.getLanguageId());
			responseModel.setResponseBody(stayTypeModels);
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_MESSAGE));

		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_MESSAGE));
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchStayTypeList -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseModel> fetchAccommodationList(@RequestBody CommonModel commonModel) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchAccommodationList -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		
		try {
		
			List<AccommodationModel> accommodationModels = propertyService.fetchAllAccommodationTypeByLanguage(commonModel.getLanguageId());
			responseModel.setResponseBody(accommodationModels);
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_MESSAGE));

		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setResponseCode(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyAddConstant.COMMON_ERROR_MESSAGE));
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchAccommodationList -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(PropertyAddConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
