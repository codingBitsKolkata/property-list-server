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
public class AmenitiesModel extends CommonModel {

	private String aminitiesId;
	private String amiName;
	private String languageId;
	private String parentId;
	private List<RoomVsAmenitiesModel> roomVsAmenitiesModels;
	private AmenitiesTypeModel amenitiesTypeModel;
}
