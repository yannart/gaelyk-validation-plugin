package com.yannart.validation

import javax.validation.*

def validator = Validation.buildDefaultValidatorFactory().getValidator()

boolean validate(entity, request) {

	//Setup validation message handling
	
	def hasErrors = false
	def errorMessages = [ ]
	def successMessages = [ ]

	// collect the constraint violations
	def violations = validator.validate(entity);

	if(violations.size()>0) {
		log.warning "There are validation errors"

		//Validation KO
		violations.each {
			def errorMessage = "${it.getPropertyPath()} ${it.getMessage()}"
			errorMessages.add(errorMessage)
			log.warning "Validation error: ${errorMessage}"
		}
	} else {
		//validation OK
		log.info "Validation OK"
	}
	
	request.setAttribute 'hasErrors', hasErrors
	request.setAttribute 'errorMessages', errorMessages
	request.setAttribute 'successMessages', successMessages
	
	return hasErrors	
}