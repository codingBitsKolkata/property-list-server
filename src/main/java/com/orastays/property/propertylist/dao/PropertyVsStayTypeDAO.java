package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.PropertyVsStayTypeEntity;

@Repository
public class PropertyVsStayTypeDAO extends GenericDAO<PropertyVsStayTypeEntity, Long>{

	private static final long serialVersionUID = -7212073762458870880L;

	public PropertyVsStayTypeDAO() {
		super(PropertyVsStayTypeEntity.class);
	}
}
