package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertyadd.entity.MealPlanEntity;

@Repository
public class MealPlanDAO extends GenericDAO<MealPlanEntity, Long>{

	private static final long serialVersionUID = -1053659675187967291L;

	public MealPlanDAO() {
		super(MealPlanEntity.class);
	}
}
