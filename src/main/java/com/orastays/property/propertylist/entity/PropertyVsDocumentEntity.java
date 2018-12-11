package com.orastays.property.propertylist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PropertyVsDocumentEntity extends CommonEntity {

	private static final long serialVersionUID = 252461737582048082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_vs_document_id")
	@JsonProperty("userVsDocumentId")
	private Long userVsDocumentId;

	@Column(name = "document_number")
	@JsonProperty("documentNumber")
	private String documentNumber;

	@Column(name = "file_url")
	@JsonProperty("fileUrl")
	private String fileUrl;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "property_id", nullable = false)
	@JsonProperty("property")
	private PropertyEntity propertyEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "document_id", nullable = false)
	@JsonProperty("document")
	private DocumentEntity documentEntity;
	
	


	@Override
	public String toString() {
		return Long.toString(userVsDocumentId);
	}
}
