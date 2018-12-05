package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsOraDiscountEntity;

@Repository
public class RoomVsOraDiscountDAO extends GenericDAO<RoomVsOraDiscountEntity, Long>{

	private static final long serialVersionUID = -3343008999790024312L;

	public RoomVsOraDiscountDAO() {
		super(RoomVsOraDiscountEntity.class);
	}
}
