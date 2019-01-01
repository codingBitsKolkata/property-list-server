package com.orastays.property.propertylist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Landing Page", tags = "Landing Page")
public class HomeController extends BaseController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);
}
