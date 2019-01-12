package com.orastays.property.propertylist.utils;

import java.util.List;

import com.orastays.property.propertylist.model.utils.RoomFilter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomSelector {
	
	private RoomFilter selectedRoom;
	private List<RoomFilter> availableRooms;
}
