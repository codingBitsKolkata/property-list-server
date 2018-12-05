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
public class SpecialtiesModel extends CommonModel {

	private String specialtiesId;
	private String specialitiesName;
	private String languageId;
	private String parentId;
	private List<RoomVsSpecialitiesModel> roomVsSpecialitiesModels;
}
