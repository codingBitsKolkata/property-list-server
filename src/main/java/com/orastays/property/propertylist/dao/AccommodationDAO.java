package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.AccommodationEntity;

@Repository
public class AccommodationDAO extends GenericDAO<AccommodationEntity, Long> {

	private static final long serialVersionUID = 739899490273389165L;

	public AccommodationDAO() {
		super(AccommodationEntity.class);

	}
}
