package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsMealEntity;

@Repository
public class RoomVsMealDAO extends GenericDAO<RoomVsMealEntity, Long>{

	private static final long serialVersionUID = 6093448931917612948L;

	public RoomVsMealDAO() {
		super(RoomVsMealEntity.class);
	}
}