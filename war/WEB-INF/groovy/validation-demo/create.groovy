import com.yannart.entity.User

def user = new User()
user.firstname = params['firstname']
user.middlename = params['middlename']
user.lastname = params['lastname']
user.age = Integer.parseInt(params['age'])
user.notValidatedByClient = params['notValidatedByClient']
user.enabled = params['enabled'] != null ? true : false

log.info "Creating user: ${user}"

//validating user
def hasErrors = validator.validate(user, request)

if(hasErrors) { //validation NOK
	
	log.warning "There are validation errors"
	request.setAttribute 'user', user
	forward '/validation-demo/form.gtpl'

} else { //validation OK
	log.info "Validation OK"
	request.setAttribute 'user', user
	forward '/validation-demo/view.gtpl'
}
