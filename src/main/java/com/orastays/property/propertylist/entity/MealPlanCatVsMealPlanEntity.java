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
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "meal_plan_cat_vs_meal_plan")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealPlanCatVsMealPlanEntity extends CommonEntity{

	private static final long serialVersionUID = -2218223338060085395L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mpcmp_id")
	private Long mpcmpId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "mpc_id", nullable = false)
	private MealPlanCategoryEntity mealPlanCategoryEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "meal_plan_id", nullable = false)
	private MealPlanEntity mealPlanEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealPlanCatVsMealPlanEntity", cascade = { CascadeType.ALL })
	private List<RoomVsMealEntity> roomVsMealEntities;
	
	@Override
	public String toString() {
		return Long.toString(mpcmpId);
	}
}
