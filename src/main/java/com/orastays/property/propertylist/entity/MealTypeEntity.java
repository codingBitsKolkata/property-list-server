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
@Table(name = "master_meal_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MealTypeEntity extends CommonEntity{
	
	private static final long serialVersionUID = -8486375113883948188L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meal_type_id")
	@JsonProperty("mealTypeId")
	private Long mealTypeId;
	
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "img_url")
	@JsonProperty("imgUrl")
	private String imgUrl;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mealTypeEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsMeals")
	private List<RoomVsMealEntity> roomVsMealEntities;
	
	@Override
	public String toString() {
		return Long.toString(mealTypeId);
	}
}
