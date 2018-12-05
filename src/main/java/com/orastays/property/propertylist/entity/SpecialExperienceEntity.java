package com.orastays.property.propertylist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_special_experience")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SpecialExperienceEntity extends CommonEntity {

	private static final long serialVersionUID = 8930860447409208950L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private Long experienceId;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "experience_name")
	private String experienceName;

	@Override
	public String toString() {
		return Long.toString(experienceId);
	}
}
