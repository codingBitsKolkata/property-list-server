package com.orastays.property.propertylist.utils;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import com.orastays.property.propertylist.model.utils.RoomFilter;

public class SharedRoomComparator implements Comparator<RoomFilter> {

	@Override
	public int compare(RoomFilter arg0, RoomFilter arg1) {
		
		double bedPriceFirst = 0.0D;
		if(StringUtils.isEmpty(arg0.getRoomEntity().getSharedBedPricePerNight())) {
			bedPriceFirst = Double.parseDouble(arg0.getRoomEntity().getSharedBedPricePerMonth())/30;
		} else {
			bedPriceFirst = Double.parseDouble(arg0.getRoomEntity().getSharedBedPricePerNight());
		}
		
		double bedPriceSecond = 0.0D;
		if(StringUtils.isEmpty(arg1.getRoomEntity().getSharedBedPricePerNight())) {
			bedPriceSecond = Double.parseDouble(arg1.getRoomEntity().getSharedBedPricePerMonth())/30;
		} else {
			bedPriceSecond = Double.parseDouble(arg1.getRoomEntity().getSharedBedPricePerNight());
		}
		
		if (bedPriceFirst < bedPriceSecond) {
			return -1;
		} else if (bedPriceFirst > bedPriceSecond) {
			return 1;
		} else
			return 0;
	}

}
