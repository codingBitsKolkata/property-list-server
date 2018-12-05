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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room_vs_info")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomVsInfoEntity extends CommonEntity {

	private static final long serialVersionUID = -8200708808109082930L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ri_id")
	private Long riId;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "description")
	private String description;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity roomEntity;

	@Override
	public String toString() {
		return Long.toString(riId);
	}

}
