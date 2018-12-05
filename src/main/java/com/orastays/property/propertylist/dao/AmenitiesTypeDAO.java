package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.AmenitiesTypeEntity;

@Repository
public class AmenitiesTypeDAO extends GenericDAO<AmenitiesTypeEntity, Long> {

	private static final long serialVersionUID = 169737871391607836L;

	public AmenitiesTypeDAO() {
		super(AmenitiesTypeEntity.class);

	}
}
