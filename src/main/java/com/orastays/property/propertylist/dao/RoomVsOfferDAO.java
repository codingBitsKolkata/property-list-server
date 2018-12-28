package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsOfferEntity;

@Repository
public class RoomVsOfferDAO extends GenericDAO<RoomVsOfferEntity, Long> {

	private static final long serialVersionUID = -8551172866673213126L;

	public RoomVsOfferDAO() {
		super(RoomVsOfferEntity.class);
	}
}
