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
@Table(name = "master_accommodation")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AccommodationEntity extends CommonEntity {
	
	private static final long serialVersionUID = 3095403892373628224L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accommodation_id")
	@JsonProperty("accommodationId")
	private Long accommodationId;
	
	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;

	@Column(name = "accommodation_name")
	@JsonProperty("accommodationName")
	private String accommodationName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodationEntity", cascade = { CascadeType.ALL })
	@JsonProperty("rooms")
	private List<RoomEntity> roomEntities;

	@Override
	public String toString() {
		return Long.toString(accommodationId);
	}
}
