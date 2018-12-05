package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsSpecialExperienceModel extends CommonModel {

	private String propertyExpId;
	private SpecialExperienceModel specialExperienceModel;
	private PropertyModel propertyModel;
}
