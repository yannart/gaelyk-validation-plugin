Gaelyk Validation Plugin
====

Example quickstart
------------------

*	Checkout the example branch.
*	In a console "grandlew classes" then "grandlew gaeRun"
*	Open a browser with the URL http://localhost:8080/validation-demo/form.groovy
*	Test the validation while completing the form. The field "Field not validated at client side" is not validated on the client side ;-)

Introduction
------------

This plugin allows the validation of forms in the server side and in the client side based on the Model.

It uses JSR303 validation annotations to annotate a Bean and Hibernate Validator to perform the server side validation. It uses JQuery Validation to do the client side validation.
The validation rules are defined in the client side with a generated JSON.

The library JSR303ClientValidationLibrary is used to perform the genaration of JSON contraint rules from the annotated bean.
The project source is available at https://github.com/yannart/JSR303ClientValidationLibrary

First, validation is performed on client side, if all is OK, the validation is done on the server side.
If there are any validation problems in the server side that haven't beed detected on the client side, a list of error messages can be shown.

For now the annotations supported are: @Size, @Min, @Max, @NotNull

The annotated fields of the POGO are used to generate the rules used by JQuery Validation plugin for client side validation.

It is possible to cache the generated JSON to avoid its multiple generation. It is also possible to indicate that some fields even if validated on the server side must be ignored on client side.

The current implementation of JSR303 is Hibernate Validator.

The client side validation is performed with JQuery Validation plugin: http://bassistance.de/jquery-plugins/jquery-plugin-validation/

Example description
-------------------

Example of how to perform a form validation from a POGO annotated with JSR303 validation annotations.


POGO:
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

JQuery Validation plugin rules:

rules: {
	firstname: {
		required: true,
		minlength: 1,
		maxlength: 35
	},
	middlename: {
		minlength: 1,
		maxlength: 35
	},
	lastname: {
		required: true,
		minlength: 1,
		maxlength: 35
	},
	age: {
		required: true,
		min: 13,
		max: 110
	}
}

A POGO example is located in com.yannart.entity.User in the "src" folder

Two example controller that perform server side validation and send to the presentation the client validation rules can be found in:
"war/WEB-INF/groovy/validation-demo/form.groovy"
"war/WEB-INF/groovy/validation-demo/create.groovy"
