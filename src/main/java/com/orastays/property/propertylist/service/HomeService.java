package com.orastays.property.propertylist.service;

import java.util.List;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.OfferModel;
import com.orastays.property.propertylist.model.PriceCalculatorModel;
import com.orastays.property.propertylist.model.PropertyListViewModel;

public interface HomeService {

	List<PropertyListViewModel> fetchPropertyByType(String propertyTypeId, String userToken) throws FormExceptions;

	Object priceCalculator(PriceCalculatorModel priceCalculatorModel) throws FormExceptions;
	
	List<OfferModel> fetchOffer() throws FormExceptions;
	
	
}