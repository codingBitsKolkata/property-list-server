package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.PropertyVsHomestayEntity;

@Repository
public class PropertyVsHomestayDAO extends GenericDAO<PropertyVsHomestayEntity, Long>{

	private static final long serialVersionUID = 8537909831573776286L;

	public PropertyVsHomestayDAO() {
		super(PropertyVsHomestayEntity.class);
	}
}
