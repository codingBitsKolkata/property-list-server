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
@Table(name = "master_property_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PropertyTypeEntity extends CommonEntity {

	private static final long serialVersionUID = 4064653909402332809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_type_id")
	private Long propertyTypeId;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyTypeEntity", cascade = { CascadeType.ALL })
	private List<PropertyEntity> propertyEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyTypeEntity", cascade = { CascadeType.ALL })
	private List<RoomCategoryEntity> roomCategoryEntities;

	@Override
	public String toString() {
		return Long.toString(propertyTypeId);
	}
}
