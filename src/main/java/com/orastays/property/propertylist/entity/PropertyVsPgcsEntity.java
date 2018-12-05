package com.orastays.property.propertylist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property_vs_pgcs")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PropertyVsPgcsEntity extends CommonEntity  {
	
	private static final long serialVersionUID = -2583362368709029721L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_pgcs_id")
	private Long propertyPGCSId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "pgcs_id", nullable = false)
	private PGCategorySexEntity pgCategorySexEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	private PropertyEntity propertyEntity;
	
	@Override
	public String toString() {
		return Long.toString(propertyPGCSId);
	}
}
