package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.PriceTypeEntity;

@Repository
public class PriceTypeDAO extends GenericDAO<PriceTypeEntity, Long>{

	private static final long serialVersionUID = -5065952725512145982L;

	public PriceTypeDAO() {
		super(PriceTypeEntity.class);
	}
}
