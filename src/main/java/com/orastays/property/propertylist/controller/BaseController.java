package com.orastays.property.propertylist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.orastays.property.propertylist.helper.MessageUtil;
import com.orastays.property.propertylist.service.PropertyService;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected PropertyService propertyService;
	
	@Autowired
	protected MessageUtil messageUtil;
}
