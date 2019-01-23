package com.orastays.property.propertylist.helper;

import java.util.Comparator;

import com.orastays.property.propertylist.model.FilterRoomModel;

public class FilterRoomComparatorByAdult implements Comparator<FilterRoomModel> {

	@Override
	public int compare(FilterRoomModel obj1, FilterRoomModel obj2) {

		if (obj1.getNumOfAdult() > obj2.getNumOfAdult())
			return 1;
		else if (obj1.getNumOfAdult() < obj2.getNumOfAdult())
			return -1;
		else
			return 0;
	}
}
