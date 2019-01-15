package com.orastays.property.propertylist.utils;

import java.util.Comparator;

import com.orastays.property.propertylist.model.utils.RoomFilter;

public class PrivateRoomComparator implements Comparator<RoomFilter> {

	@Override
	public int compare(RoomFilter o1, RoomFilter o2) {

		double firstObjectBedPrice = Double.parseDouble(o1.getRoomEntity().getRoomPricePerNight());
		double secondObjectBedPrice = Double.parseDouble(o2.getRoomEntity().getRoomPricePerNight());
		double firstObjectCotPrice = o1.getRoomEntity().getCotPrice() == null ? 0
				: Double.parseDouble(o1.getRoomEntity().getCotPrice());
		double secondObjectCotPrice = o2.getRoomEntity().getCotPrice() == null ? 0
				: Double.parseDouble(o2.getRoomEntity().getCotPrice());

		if ((firstObjectBedPrice + firstObjectCotPrice) < (secondObjectBedPrice + secondObjectCotPrice)) {
			return -1;
		} else if ((firstObjectBedPrice + firstObjectCotPrice) > (secondObjectBedPrice + secondObjectCotPrice)) {
			return 1;
		} else
			return 0;
	}

}
