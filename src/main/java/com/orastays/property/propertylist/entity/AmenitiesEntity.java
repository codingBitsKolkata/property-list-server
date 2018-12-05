package com.orastays.property.propertylist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_amenities")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AmenitiesEntity extends CommonEntity{
	
	private static final long serialVersionUID = 5562924720677171528L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aminities_id")
	private Long aminitiesId;
	
	@Column(name = "name")
	private String amiName;
	
	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "amenitiesEntity", cascade = { CascadeType.ALL })
	private List<RoomVsAmenitiesEntity> roomVsAmenitiesEntities;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "aminities_type_id", nullable = false)
	private AmenitiesTypeEntity amenitiesTypeEntity;
	
	@Override
	public String toString() {
		return Long.toString(aminitiesId);
	}
}