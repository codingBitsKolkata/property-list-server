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
@Table(name = "master_meal_plan")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealPlanEntity extends CommonEntity{
	
	private static final long serialVersionUID = 3365288762964751801L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meal_plan_id")
	private Long mealPlanId;
	
	@Column(name = "meal_plan_name")
	private String mealPlanName;
	
	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealPlanEntity", cascade = { CascadeType.ALL })
	private List<MealPlanCatVsMealPlanEntity> mealPlanCatVsMealPlanEntities;
	
	@Override
	public String toString() {
		return Long.toString(mealPlanId);
	}
}