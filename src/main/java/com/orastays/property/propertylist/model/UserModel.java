/**
 * @author Abhideep
 */
package com.orastays.property.propertylist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserModel extends CommonModel {

	private String userId;
	private String name;
	private String mobileNumber;
	private String emailId;
}