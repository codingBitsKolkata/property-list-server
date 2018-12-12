/**
 * @author Krishanu 
 */
package com.orastays.property.propertylist.model;

import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserVsAccountModel extends CommonModel {

	@JsonProperty("userVsAccountId")
	private Long userVsAccountId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("accountHolderName")
	private String accountHolderName;

	@JsonProperty("accountType")
	private String accountType;

	@JsonProperty("bankName")
	private String bankName;

	@Column(name = "branch_name")
	@JsonProperty("branchName")
	private String branchName;

	@JsonProperty("ifscCode")
	private String ifscCode;

	@JsonProperty("propertys")
	private List<PropertyModel> propertyModels;
}
