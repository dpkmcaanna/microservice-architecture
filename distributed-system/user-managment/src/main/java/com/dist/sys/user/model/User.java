package com.dist.sys.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder(toBuilder = true)
public class User {

	Long id;
	String userAlia;
	String firstName;
	String lastName;
	String email;
	String phone;
}
