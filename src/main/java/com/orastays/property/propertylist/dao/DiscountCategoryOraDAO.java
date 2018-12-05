package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.DiscountCategoryOraEntity;

@Repository
public class DiscountCategoryOraDAO extends GenericDAO<DiscountCategoryOraEntity, Long>{

	private static final long serialVersionUID = 6362681595678636623L;

	public DiscountCategoryOraDAO() {
		super(DiscountCategoryOraEntity.class);
	}
}
