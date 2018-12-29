package com.orastays.property.propertylist.model;

import java.util.List;

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
public class DocumentModel {

	@JsonProperty("documentId")
	private String documentId;

	@JsonProperty("documentName")
	private String documentName;

	@JsonProperty("propertyVsDocuments")
	private List<PropertyVsDocumentModel> propertyVsDocumentModels;
}
