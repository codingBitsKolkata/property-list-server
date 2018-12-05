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
@Table(name = "room_vs_bed")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomVsBedEntity extends CommonEntity {

	private static final long serialVersionUID = 1473001658995093297L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rb_id")
	private Long rbId;

	@Column(name = "no_of_beds")
	private String noOfBeds;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity roomEntity;

	@Override
	public String toString() {
		return Long.toString(rbId);
	}
}
