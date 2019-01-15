package com.orastays.property.propertylist.model.utils;

import com.orastays.property.propertylist.entity.RoomEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomFilter {
	
	private boolean isConsidered;
	private boolean isMatchingSearchCriteria;
	private RoomEntity roomEntity;
	
	//for shared room
	private int availableBeds;
	private int selectedNumberOfBeds;
	private int selectedNumberOfCots;
}
