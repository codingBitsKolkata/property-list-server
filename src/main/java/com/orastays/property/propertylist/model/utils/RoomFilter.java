package com.orastays.property.propertylist.model.utils;

import com.orastays.property.propertylist.entity.RoomEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFilter {
	private boolean isConsidered;
	private boolean isMatchingSearchCriteria;
	private RoomEntity roomEntity;
	
	//for shared room
	private int availableBeds;
}
