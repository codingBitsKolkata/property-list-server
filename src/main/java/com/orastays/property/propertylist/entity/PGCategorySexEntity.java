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
@Table(name = "master_pg_category_sex")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PGCategorySexEntity extends CommonEntity {

	private static final long serialVersionUID = 4977098535376096639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pgcs_id")
	private Long pgcsId;

	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "category_name")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pgCategorySexEntity", cascade = { CascadeType.ALL })
	private List<PropertyVsPgcsEntity> propertyVsPgcsEntities;

	@Override
	public String toString() {
		return Long.toString(pgcsId);
	}
}