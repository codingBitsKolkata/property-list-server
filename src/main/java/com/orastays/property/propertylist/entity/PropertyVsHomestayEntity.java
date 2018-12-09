package com.orastays.property.propertylist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "property_vs_homestay")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PropertyVsHomestayEntity extends CommonEntity  {
	
	private static final long serialVersionUID = -5258976879782159042L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_homestay_id")
	@JsonProperty("propertyHomeStayId")
	private Long propertyHomeStayId;

	@Column(name = "immediate_booking")
	@JsonProperty("immediateBooking")
	private String immediateBooking;
	
	@Column(name = "strict_checkin")
	@JsonProperty("strictCheckin")
	private String strictCheckin;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	@JsonProperty("property")
	private PropertyEntity propertyEntity;
	
	@Override
	public String toString() {
		return Long.toString(propertyHomeStayId);
	}
}
