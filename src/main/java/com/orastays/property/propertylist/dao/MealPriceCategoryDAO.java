package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.MealPriceCategoryEntity;

@Repository
public class MealPriceCategoryDAO extends GenericDAO<MealPriceCategoryEntity, Long>{

	private static final long serialVersionUID = 1585242654830887252L;

	public MealPriceCategoryDAO() {
		super(MealPriceCategoryEntity.class);
	}
}
