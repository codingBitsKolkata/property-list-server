package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertyadd.entity.RoomVsAmenitiesEntity;

@Repository
public class RoomVsAmenitiesDAO  extends GenericDAO<RoomVsAmenitiesEntity, Long>{

	private static final long serialVersionUID = -6937519057315280276L;

	public RoomVsAmenitiesDAO() {
		super(RoomVsAmenitiesEntity.class);
	}
}
