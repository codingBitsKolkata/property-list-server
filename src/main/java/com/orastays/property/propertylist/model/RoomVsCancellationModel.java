package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoomVsCancellationModel extends CommonModel {

	private String rcId;
	private String percentage;
	private CancellationSlabModel cancellationSlabModel;
	private RoomModel roomModel;
}
