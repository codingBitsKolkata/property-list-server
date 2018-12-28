package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertyadd.entity.SpaceRuleEntity;

@Repository
public class SpaceRuleDAO extends GenericDAO<SpaceRuleEntity, Long>{

	private static final long serialVersionUID = -5419677564975665308L;

	public SpaceRuleDAO() {
		super(SpaceRuleEntity.class);
	}
}
