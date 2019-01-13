/**
 * @author Abhideep
 */
package com.orastays.property.propertylist.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.property.propertylist.model.CommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class HostVsDomainModel extends CommonModel {

	@JsonProperty("hostDomId")
	private String hostDomId;
	
	@JsonProperty("user")
	private UserModel userModel;
	
	@JsonProperty("domain")
	private DomainModel domain;

}