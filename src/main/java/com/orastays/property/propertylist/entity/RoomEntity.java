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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomEntity extends CommonEntity {

	private static final long serialVersionUID = 6058717921502574720L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	@JsonProperty("roomId")
	private Long roomId;

	@Column(name = "shared_space")
	@JsonProperty("sharedSpace")
	private String sharedSpace;

	@Column(name = "cot_available")
	@JsonProperty("cotAvailable")
	private String cotAvailable;

	@Column(name = "no_of_guest")
	@JsonProperty("noOfGuest")
	private String noOfGuest;

	@Column(name = "no_of_child")
	@JsonProperty("noOfChild")
	private String noOfChild;

	@Column(name = "num_of_cot")
	@JsonProperty("numOfCot")
	private String numOfCot;

	@Column(name = "room_current_status")
	@JsonProperty("roomCurrentStatus")
	private String roomCurrentStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "accommodation_id", nullable = false)
	@JsonProperty("accommodation")
	private AccommodationEntity accommodationEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_cat_id", nullable = false)
	@JsonProperty("roomCategory")
	private RoomCategoryEntity roomCategoryEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_standard_id", nullable = true)
	@JsonProperty("roomStandard")
	private RoomStandardEntity roomStandardEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	@JsonProperty("property")
	private PropertyEntity propertyEntity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsAmenities")
	private List<RoomVsAmenitiesEntity> roomVsAmenitiesEntities;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsBed")
	private RoomVsBedEntity roomVsBedEntity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsCancellations")
	private List<RoomVsCancellationEntity> roomVsCancellationEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsImages")
	private List<RoomVsImageEntity> roomVsImageEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsPrices")
	private List<RoomVsPriceEntity> roomVsPriceEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsHostDiscounts")
	private List<RoomVsHostDiscountEntity> roomVsHostDiscountEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsOraDiscounts")
	private List<RoomVsOraDiscountEntity> roomVsOraDiscountEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsSpecialities")
	private List<RoomVsSpecialitiesEntity> roomVsSpecialitiesEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsOraPricePercentages")
	private List<RoomVsOraPricePercentageEntity> roomVsOraPricePercentageEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsMeals")
	private List<RoomVsMealEntity> roomVsMealEntities;

	@Override
	public String toString() {
		return Long.toString(roomId);
	}
}
