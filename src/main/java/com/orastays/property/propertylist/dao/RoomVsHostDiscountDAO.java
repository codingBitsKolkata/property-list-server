package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsHostDiscountEntity;

@Repository
public class RoomVsHostDiscountDAO extends GenericDAO<RoomVsHostDiscountEntity, Long>{

	private static final long serialVersionUID = -2769761413488892581L;

	public RoomVsHostDiscountDAO() {
		super(RoomVsHostDiscountEntity.class);
	}
}
