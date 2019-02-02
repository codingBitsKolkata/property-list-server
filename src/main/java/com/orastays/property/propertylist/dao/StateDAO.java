package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.StateEntity;

@Repository
public class StateDAO extends GenericDAO<StateEntity, Long>{

	private static final long serialVersionUID = 4740229874513219167L;

	public StateDAO() {
		super(StateEntity.class);
	}
}
