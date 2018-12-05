package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomEntity;

@Repository
public class RoomDAO extends GenericDAO<RoomEntity, Long>{

	private static final long serialVersionUID = 2219625016335072391L;

	public RoomDAO() {
		super(RoomEntity.class);
	}
}