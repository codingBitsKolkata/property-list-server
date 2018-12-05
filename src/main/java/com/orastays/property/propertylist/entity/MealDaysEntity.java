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
@Table(name = "master_meal_days")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealDaysEntity extends CommonEntity {

	private static final long serialVersionUID = -901384733771185132L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meal_days_id")
	private Long mealDaysId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealDaysEntity", cascade = { CascadeType.ALL })
	private List<RoomVsMealEntity> roomVsMealEntities;
	
	@Override
	public String toString() {
		return Long.toString(mealDaysId);
	}
}
