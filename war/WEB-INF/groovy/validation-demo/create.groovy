import com.yannart.entity.User
import javax.validation.*

//Setup the result message handling
def errorMessages = [ ]
request.setAttribute 'errorMessages', errorMessages
def successMessages = [ ]
request.setAttribute 'successMessages', successMessages

def user = new User()
user.firstname = params['firstname']
user.middlename = params['middlename']
user.lastname = params['lastname']
user.age = Integer.parseInt(params['age'])
user.enabled = params['enabled'] != null ? true : false

log.info "Creating user: ${user}"

//validating user
def validator = Validation.buildDefaultValidatorFactory().getValidator();

// collect the constraint violations
def violations = validator.validate(user);

if(violations.size()>0) {
	log.warning "There are validation errors"

	//Validation KO
	violations.each {
		def errorMessage = "${it.getPropertyPath()} ${it.getMessage()}"
		errorMessages.add(errorMessage)
		log.warning "Validation error: ${errorMessage}"
	}

	request.setAttribute 'user', user

	forward '/validation-demo/form.gtpl'

} else {
	//validation OK
	log.info "Validation OK"

	request.setAttribute 'user', user

	forward '/validation-demo/view.gtpl'
}
