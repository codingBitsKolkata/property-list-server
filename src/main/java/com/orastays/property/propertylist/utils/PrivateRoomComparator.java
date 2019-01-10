package com.orastays.property.propertylist.utils;

import java.util.Comparator;

import com.orastays.property.propertylist.model.utils.RoomFilter;

public class PrivateRoomComparator implements Comparator<RoomFilter> {

	@Override
	public int compare(RoomFilter o1, RoomFilter o2) {
		// TODO Auto-generated method stub
		Double firstObjectBedPrice = Double.parseDouble(o1.getRoomEntity().getRoomPricePerNight());
		Double secondObjectBedPrice = Double.parseDouble(o2.getRoomEntity().getRoomPricePerNight());
		if (firstObjectBedPrice < secondObjectBedPrice) {
			return -1;
		} else if (firstObjectBedPrice > secondObjectBedPrice) {
			return 1;
		} else
			return 0;
	}

}
