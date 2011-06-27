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

	boolean enabled
}

