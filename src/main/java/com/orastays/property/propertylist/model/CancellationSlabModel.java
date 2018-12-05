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
public class CancellationSlabModel extends CommonModel {

	private String cancellationSlabId;
	private String startTime;
	private String endTime;
	private List<RoomVsCancellationModel> roomVsCancellationModels;
}
