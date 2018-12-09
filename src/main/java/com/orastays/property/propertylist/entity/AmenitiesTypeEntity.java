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

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("aminitiesTypeId")
	private Long aminitiesTypeId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "amenitiesTypeEntity", cascade = { CascadeType.ALL })
	@JsonProperty("amenities")
	private List<AmenitiesEntity> amenitiesEntities;
	
	@Column(name = "aminities_type_name")
	@JsonProperty("aminitiesType")
	private String aminitiesTypeName;
	
	@Override
	public String toString() {
		return Long.toString(aminitiesTypeId);
	}
}
