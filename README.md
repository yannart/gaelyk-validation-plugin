Gaelyk Validation Plugin
====

Usage
-----

####Installation
*	Add the plugin files to your project.
*	Edit the war/WEB-INF/plugins.groovy file to add the line:
	install gaelykValidationPlugin

####Variables bound

Variables bound available in pages and controllers:

#####validator:
Server side validator that wraps and instance of javax.validation.Validator to perform the validation of a POGO (or POJO).

The method to call in the controller is "validator.validate(entity, request)" where entity is the object to "validate" and "request" the Servlet request object. The method returns false if no validation error is found or true if errors exist.
The error messages list is added to the request property "errorMessages".

#####validationDescriptor:
Validation constraint descriptor generator that creates a JSON constraint descriptor from an annotated POGO / POJO entity and a list of properties to ignore.
The generated JSON is cached for each combination of entities and properties. 

The method that generates the JSON is:

	validationDescriptor.render(entityClass, optionalPropertyToIgnore1, optionalPropertyToIgnore2...)

* entityClass is the .class of the entity to validate
* optionalPropertyToIgnore1, optionalPropertyToIgnore2, ... are the properties that will not be validated on the client side.

#####validationDescriptorNoCache:
Works in the same manner than validationDescriptor but do not cache the generated JSON, it is produced for every call.

####Form and scripts to add in the page

#####JavaScript dependencies
JQuery and JQuery Validation are required and provided in the plugin package. But they must be added to the page where will be content the validated form:

	<script src="/js/jquery-1.6.1.min.js" type="text/javascript"></script>
	<script src="/js/jquery.validate.min.js" type="text/javascript"></script>

#####Validation configuration

A script must be used to attach a validation logic to a form:

    <script type="text/javascript"> 
    \$().ready(function() {
	    jQuery.validator.setDefaults({errorClass: "form-validation-error"});

	    // validate category form on keyup and submit
	    \$("#validatedForm").validate({<%= validationDescriptor.render(com.yannart.entity.User.class, 'notValidatedByClient') %>});
	});
	</script>

* By default the property errorClass: "form-validation-error" defines that the style used for errors is "form-validation-error".
* "validatedForm" is the name of the form to be validated.

####Server side error messages

The server side error messages are provided in the "errorMessages" request property and can be displayed in the form page.
A section to include already displays the messages with custom CSS style:

    <% include '/WEB-INF/includes/messages.gtpl' %>

####Validated Form

The form to validate finally must contain the fields to be validated. If fields are defined in the form but not in the entity model or if some properties have been declared to be ignored, those fields will not be validated on the client side.

    <form class="cmxform" id="validatedForm" action="/validation-demo/create.groovy" method="post"> 
 
          <fieldset> 
            <legend>User</legend> 
            <p> 
              <label for="firstname">Firstname</label><br> 
              <input type="text" name="firstname" id="firstname" value="<%= user?.firstname %>"> 
            </p>
            <p> 
              <input class="submit" type="submit" value="Submit"/> 
              <input type="reset" value="Reset"> 
            </p>
          </fieldset> 
    </form>

####Example

An example can be found in the branch "example" of this project:
https://github.com/yannart/gaelyk-validation-plugin/tree/example


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
