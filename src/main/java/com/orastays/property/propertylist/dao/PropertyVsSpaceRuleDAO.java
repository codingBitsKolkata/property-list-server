package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertyadd.entity.PropertyVsSpaceRuleEntity;

@Repository
public class PropertyVsSpaceRuleDAO extends GenericDAO<PropertyVsSpaceRuleEntity, Long>{

	private static final long serialVersionUID = -4009437579671339098L;

	public PropertyVsSpaceRuleDAO() {
		super(PropertyVsSpaceRuleEntity.class);
	}
}
