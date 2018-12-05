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
@Table(name = "master_amenities_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AmenitiesTypeEntity extends CommonEntity{

	private static final long serialVersionUID = -7850034465030929606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aminities_type_id")
	private Long amiTypeId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "amenitiesTypeEntity", cascade = { CascadeType.ALL })
	private List<AmenitiesEntity> amenitiesEntities;
	
	@Column(name = "name")
	private String amiTypeName;
	
	@Override
	public String toString() {
		return Long.toString(amiTypeId);
	}
}
