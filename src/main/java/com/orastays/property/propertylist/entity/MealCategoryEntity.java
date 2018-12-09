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
@Table(name = "master_meal_category")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealCategoryEntity extends CommonEntity{
	
	private static final long serialVersionUID = 1428625624687027747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meal_category_id")
	@JsonProperty("mealCategoryId")
	private Long mealCategoryId;
	
	@Column(name = "meal_category_name")
	@JsonProperty("mealCatName")
	private String mealCatName;
	
	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealCategoryEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsMeals")
	private List<RoomVsMealEntity> roomVsMealEntities;
	
	@Override
	public String toString() {
		return Long.toString(mealCategoryId);
	}

}
