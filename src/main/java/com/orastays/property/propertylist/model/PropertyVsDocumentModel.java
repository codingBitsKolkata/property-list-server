package com.orastays.property.propertylist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyVsDocumentModel {

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
