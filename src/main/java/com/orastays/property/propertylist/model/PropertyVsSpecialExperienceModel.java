package com.orastays.property.propertylist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class PropertyVsSpecialExperienceModel extends CommonModel {

	@JsonProperty("propertyExpId")
	private String propertyExpId;
	
	@JsonProperty("specialExperience")
	private SpecialExperienceModel specialExperienceModel;
	
	@JsonProperty("property")
	private PropertyModel propertyModel;
}
