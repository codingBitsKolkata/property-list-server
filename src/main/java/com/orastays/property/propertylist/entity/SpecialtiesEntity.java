package com.orastays.property.propertylist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_specialties")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SpecialtiesEntity extends CommonEntity {

	private static final long serialVersionUID = -6613026495348387173L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "specialties_id")
	private Long specialtiesId;

	@Column(name = "name")
	private String specialitiesName;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "SpecialtiesEntity", cascade = { CascadeType.ALL })
	private List<RoomVsSpecialitiesEntity> roomVsSpecialitiesEntities;

	@Override
	public String toString() {
		return Long.toString(specialtiesId);
	}
}
