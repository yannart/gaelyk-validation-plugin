package com.yannart.entity

import javax.validation.constraints.*

class User {

	@NotNull
	@Size(min = 1, max = 35)
	String firstname

	@Size(min = 1, max = 35)
	String middlename

    @NotNull
	@Size(min = 1, max = 35)
	String lastname

	@NotNull
	@Min(13L)
	@Max(110L)
	Integer age
	
	//this field is used to demonstrate the ability of ignoring
	//client side validation for particular fields
	@Size(min = 10, max = 15)
	String notValidatedByClient

	boolean enabled
}

