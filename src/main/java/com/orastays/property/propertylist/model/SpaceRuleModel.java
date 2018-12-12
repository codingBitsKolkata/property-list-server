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
public class SpaceRuleModel extends CommonModel {

	@JsonProperty("spruleId")
	private String spruleId;

	@JsonProperty("languageId")
	private String languageId;

	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("ruleName")
	private String ruleName;

	@JsonProperty("propertyVsSpaceRules")
	private List<PropertyVsSpaceRuleModel> propertyVsSpaceRuleModels;
}
