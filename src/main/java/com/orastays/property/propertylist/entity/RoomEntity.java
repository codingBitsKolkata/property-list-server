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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomEntity extends CommonEntity{
	
	private static final long serialVersionUID = 6058717921502574720L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long roomId;
	
	@Column(name = "shared_space")
	private String sharedSpace;
	
	@Column(name = "cot_available")
	private String cotAvailable;

	@Column(name = "no_of_guest")
	private String noOfGuest;
	
	@Column(name = "no_of_child")
	private String noOfChild;
	
	@Column(name = "num_of_cot")
	private String numOfCot;
	
	@Column(name = "commision")
	private String commision;
	
	@Column(name = "floor_no")
	private String floorNo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "accommodation_id", nullable = false)
	private AccommodationEntity accommodationEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "dco_id", nullable = false)
	private DiscountCategoryOraEntity discountCategoryOraEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_cat_id", nullable = false)
	private RoomCategoryEntity roomCategoryEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_standard_id", nullable = false)
	private RoomStandardEntity roomStandardEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	private PropertyTypeEntity propertyEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsAmenitiesEntity> roomVsAmenitiesEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private RoomVsInfoEntity roomVsInfoEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsBedEntity> roomVsBedEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsCancellationEntity> roomVsCancellationEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsHostDiscountEntity> roomVsHostDiscountEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsImageEntity> roomVsImageEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsOraDiscountEntity> roomVsOraDiscountEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsOraPricePercentageEntity> roomVsOraPricePercentageEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsPriceEntity> roomVsPriceEntityEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsSpecialitiesEntity> roomVsSpecialitiesEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	private List<RoomVsMealEntity> roomVsMealEntities;
	
	
	@Override
	public String toString() {
		return Long.toString(roomId);
	}
}
