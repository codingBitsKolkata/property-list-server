package com.orastays.property.propertylist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PGCategorySexModel extends CommonModel {

	@JsonProperty("pgcsId")
	private String pgcsId;
	
	@JsonProperty("languageId")
	private String languageId;
	
	@JsonProperty("parentId")
	private String parentId;
	
	@JsonProperty("categoryName")
	private String categoryName;
	
	@JsonProperty("propertys")
	private List<PropertyModel> propertyModels;
}
