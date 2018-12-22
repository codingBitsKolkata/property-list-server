package com.orastays.property.propertylist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.property.propertylist.helper.PropertyListConstant;
import com.orastays.property.propertylist.helper.Util;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Property List", tags = "Property List")
public class PropertyListController extends BaseController {

	private static final Logger logger = LogManager.getLogger(PropertyListController.class);
	
	@GetMapping(value = "/fetch-properties", produces = "application/json")
	@ApiOperation(value = "Fetch Properties", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token") })
	public ResponseEntity<ResponseModel> fetchProperties(@RequestBody FilterCiteriaModel filterCiteriaModel) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(null, PropertyListConstant.INCOMING, "Fetch Properties", request);
		try {
			/*
			 * List<ProModel> countryModels = countryService.fetchCountries();
			 * responseModel.setResponseBody(countryModels);
			 */
			responseModel.setResponseCode(messageUtil.getBundle(PropertyListConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyListConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchCountries -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(PropertyListConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(PropertyListConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, PropertyListConstant.OUTGOING, "Fetch Properties", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchProperties -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(PropertyListConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
