package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.PGCategorySexEntity;

@Repository
public class PGCategorySexDAO extends GenericDAO<PGCategorySexEntity, Long>{

	private static final long serialVersionUID = 813591451422499177L;

	public PGCategorySexDAO() {
		super(PGCategorySexEntity.class);
	}
}
