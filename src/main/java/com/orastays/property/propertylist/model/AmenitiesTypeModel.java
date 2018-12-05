package com.orastays.property.propertylist.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class AmenitiesTypeModel extends CommonModel {

	private String amiTypeId;
	private List<AmenitiesModel> amenitiesModels;
	private String amiTypeName;
}
