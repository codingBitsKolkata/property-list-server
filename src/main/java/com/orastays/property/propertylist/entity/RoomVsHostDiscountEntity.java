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
@Table(name = "room_vs_host_discount")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomVsHostDiscountEntity extends CommonEntity {

	private static final long serialVersionUID = -5073041377859715363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rhd_id")
	private Long rhdId;

	@Column(name = "percentage")
	private String percentage;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "dch_id", nullable = false)
	private DiscountCategoryHostEntity discountCategoryHostEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity roomEntity;

	@Override
	public String toString() {
		return Long.toString(rhdId);
	}
}