Gaelyk Validation Plugin
====

This plugin allows the validation of forms in the server side and in the client side based on the Model.

It uses JSR303 validation annotations to annotate a Bean and Hibernate Validator to perform the server side validation. It uses JQuery Validation to do the client side validation.
The validation rules are defined in the client side with a generated JSON.

First, validation is performed on client side, if all is OK, the validation is done on the server side.
If there are any validation problems in the server side that haven't beed detected on the client side, a list of error messages can be shown.

For now the annotations supported are: @Size, @Min, @Max, @NotNull (check the com.yannart.validation.JSR303ToJson class in the "src" folder)
The annotated fields of the POGO are used to generate the rules used by JQuery Validation plugin for client side validation.

The current implementation of JSR303 is Hibernate Validator.

The client side validation is performed with JQuery Validation plugin: http://bassistance.de/jquery-plugins/jquery-plugin-validation/


Example
-------

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
