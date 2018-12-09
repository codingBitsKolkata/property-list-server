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
@Table(name = "master_pg_category_sex")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PGCategorySexEntity extends CommonEntity {

	private static final long serialVersionUID = 4977098535376096639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pgcs_id")
	@JsonProperty("pgcsId")
	private Long pgcsId;

	@Column(name = "language_id")
	@JsonProperty("languageId")
	private Long languageId;

	@Column(name = "parent_id")
	@JsonProperty("parentId")
	private Long parentId;

	@Column(name = "category_name")
	@JsonProperty("categoryName")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pgCategorySexEntity", cascade = { CascadeType.ALL })
	@JsonProperty("propertys")
	private List<PropertyEntity> propertyEntities;

	@Override
	public String toString() {
		return Long.toString(pgcsId);
	}
}