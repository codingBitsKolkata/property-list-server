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
@Table(name = "room_vs_meal")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoomVsMealEntity extends CommonEntity{
	
	private static final long serialVersionUID = -8324710712961275540L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_vs_meal_id")
	private Long roomVsMealId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "meal_category_id", nullable = false)
	private MealCategoryEntity mealCategoryEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity roomEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "meal_days_id", nullable = false)
	private MealDaysEntity mealDaysEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "meal_type_id", nullable = false)
	private MealTypeEntity mealTypeEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "mpcmp_id", nullable = false)
	private MealPlanCatVsMealPlanEntity mealPlanCatVsMealPlanEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "mmpc_id", nullable = false)
	private MealPriceCategoryEntity mealPriceCategoryEntity;
	
	@Override
	public String toString() {
		return Long.toString(roomVsMealId);
	}
}
