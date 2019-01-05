package com.orastays.property.propertylist.service;

import com.orastays.property.propertylist.exceptions.FormExceptions;
import com.orastays.property.propertylist.model.FilterCiteriaModel;
import com.orastays.property.propertylist.model.booking.PaymentModel;

public interface BookingService {

	PaymentModel propertyBooking(FilterCiteriaModel filterCiteriaModel) throws FormExceptions;
}