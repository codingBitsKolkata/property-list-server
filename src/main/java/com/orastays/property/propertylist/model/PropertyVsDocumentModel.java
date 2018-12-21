package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PropertyVsDocumentModel extends CommonModel{

	@JsonProperty("userVsDocumentId")
	private Long userVsDocumentId;

	@JsonProperty("documentNumber")
	private String documentNumber;

	@JsonProperty("fileUrl")
	private String fileUrl;

	@JsonProperty("property")
	private PropertyModel propertyModel;
	
	@JsonProperty("document")
	private DocumentModel documentModel;
}
