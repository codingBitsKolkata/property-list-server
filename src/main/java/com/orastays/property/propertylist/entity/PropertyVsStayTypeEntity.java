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
@Table(name = "property_vs_stay_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PropertyVsStayTypeEntity extends CommonEntity  {
	
	private static final long serialVersionUID = -6282731359926807359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_staytype_id")
	private Long propertyStayTypeId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "stay_type_id", nullable = false)
	private StayTypeEntity stayTypeEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	private PropertyEntity propertyEntity;
	
	@Override
	public String toString() {
		return Long.toString(propertyStayTypeId);
	}
}
