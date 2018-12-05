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
public class AccommodationModel extends CommonModel {

	private String accommodationId;
	private String languageId;
	private String parentId;
	private String accommodationName;
	private List<RoomModel> roomModels;

}
