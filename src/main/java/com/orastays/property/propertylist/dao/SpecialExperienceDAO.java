package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.SpecialExperienceEntity;

@Repository
public class SpecialExperienceDAO extends GenericDAO<SpecialExperienceEntity, Long>{

	private static final long serialVersionUID = 8791719028635636471L;

	public SpecialExperienceDAO() {
		super(SpecialExperienceEntity.class);
	}
}
