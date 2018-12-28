package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.PropertyVsPriceDropEntity;

@Repository
public class PropertyVsPriceDropDAO extends GenericDAO<PropertyVsPriceDropEntity, Long>{

	private static final long serialVersionUID = 3680342748086652139L;

	public PropertyVsPriceDropDAO() {
		super(PropertyVsPriceDropEntity.class);
	}
}
