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
@Table(name = "master_room_standard")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomStandardEntity extends CommonEntity {
	
	private static final long serialVersionUID = 6541222811110498279L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_standard_id")
	@JsonProperty("roomStandardId")
	private Long roomStandardId;
	
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomStandardEntity", cascade = { CascadeType.ALL })
	@JsonProperty("rooms")
	private List<RoomEntity> roomEntities;
	
	@Override
	public String toString() {
		return Long.toString(roomStandardId);
	}
}