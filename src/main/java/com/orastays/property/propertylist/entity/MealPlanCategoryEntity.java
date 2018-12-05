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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_meal_plan_category")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealPlanCategoryEntity extends CommonEntity {

	private static final long serialVersionUID = -9073085176444953780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mpc_id")
	private Long mpcId;
	
	@Column(name = "mean_plan_category_name")
	private String mpcName;
	
	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealPlanCategoryEntity", cascade = { CascadeType.ALL })
	private List<MealPlanCatVsMealPlanEntity> mealPlanCatVsMealPlanEntities;
	
	@Override
	public String toString() {
		return Long.toString(mpcId);
	}
}