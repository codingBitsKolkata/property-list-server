package com.orastays.property.propertylist.dao;

import org.springframework.stereotype.Repository;

import com.orastays.property.propertylist.entity.UserVsAccountEntity;

@Repository
public class UserVsAccountDAO extends GenericDAO<UserVsAccountEntity, Long> {

	private static final long serialVersionUID = 4069213737579806195L;

	public UserVsAccountDAO() {
		super(UserVsAccountEntity.class);
	}
}
