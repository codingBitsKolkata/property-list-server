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
@Table(name = "room_vs_price")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomVsPriceEntity extends CommonEntity {

	private static final long serialVersionUID = -2273919889166166287L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_vs_price_id")
	private Long roomVsPriceId;

	@Column(name = "price")
	private String price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "price_type_id", nullable = false)
	private PriceTypeEntity priceTypeEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity roomEntity;

	@Override
	public String toString() {
		return Long.toString(roomVsPriceId);
	}

}
