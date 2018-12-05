package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class SpecialExperienceModel extends CommonModel {

	private String experienceId;
	private String languageId;
	private String parentId;
	private String experienceName;
}
