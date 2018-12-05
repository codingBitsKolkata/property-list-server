package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsSpaceRuleModel extends CommonModel {

	private String propertySpaceId;
	private String answer;
	private SpaceRuleModel spaceRuleModel;
	private PropertyModel propertyModel;
}
