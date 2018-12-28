package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.HostVsAccountEntity;

@Repository
public class HostVsAccountDAO extends GenericDAO<HostVsAccountEntity, Long> {

	private static final long serialVersionUID = 4069213737579806195L;

	public HostVsAccountDAO() {
		super(HostVsAccountEntity.class);
	}
}