package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.DiscountCategoryHostEntity;

@Repository
public class DiscountCategoryHostDAO extends GenericDAO<DiscountCategoryHostEntity, Long>{
	
	private static final long serialVersionUID = 2324954236102901285L;

	public DiscountCategoryHostDAO() {
		super(DiscountCategoryHostEntity.class);
	}
}
