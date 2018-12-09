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
@Table(name = "master_discount_category_host")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class DiscountCategoryHostEntity extends CommonEntity {
	
	private static final long serialVersionUID = -4056330502466224589L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dch_id")
	@JsonProperty("dchId")
	private Long dchId;
	
	@Column(name = "dis_cat_host_name")
	@JsonProperty("disCatHostname")
	private String disCatHostname;
	
	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "discountCategoryHostEntity", cascade = { CascadeType.ALL })
	@JsonProperty("roomVsHostDiscounts")
	private List<RoomVsHostDiscountEntity> roomVsHostDiscountEntities;
	
	@Override
	public String toString() {
		return Long.toString(dchId);
	}
}