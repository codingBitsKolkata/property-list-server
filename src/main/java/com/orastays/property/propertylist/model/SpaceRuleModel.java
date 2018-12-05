package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class SpaceRuleModel extends CommonModel {

	private String spruleId;
	private String languageId;
	private String parentId;
	private String ruleName;
}
