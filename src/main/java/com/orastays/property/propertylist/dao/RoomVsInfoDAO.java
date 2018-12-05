package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.RoomVsInfoEntity;

@Repository
public class RoomVsInfoDAO extends GenericDAO<RoomVsInfoEntity, Long>{

	private static final long serialVersionUID = -4041918638841158305L;

	public RoomVsInfoDAO() {
		super(RoomVsInfoEntity.class);
	}
}
