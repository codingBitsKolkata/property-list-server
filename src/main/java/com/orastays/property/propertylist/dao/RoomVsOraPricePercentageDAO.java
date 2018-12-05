package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsOraPricePercentageEntity;

@Repository
public class RoomVsOraPricePercentageDAO extends GenericDAO<RoomVsOraPricePercentageEntity, Long>{

	private static final long serialVersionUID = 463134277021825390L;

	public RoomVsOraPricePercentageDAO() {
		super(RoomVsOraPricePercentageEntity.class);
	}
}
