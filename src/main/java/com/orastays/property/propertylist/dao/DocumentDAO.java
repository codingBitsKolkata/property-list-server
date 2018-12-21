package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.DocumentEntity;

@Repository
public class DocumentDAO extends GenericDAO<DocumentEntity, Long>{
	
	private static final long serialVersionUID = 2934314275641836859L;

	public DocumentDAO() {
		super(DocumentEntity.class);
	}

}
