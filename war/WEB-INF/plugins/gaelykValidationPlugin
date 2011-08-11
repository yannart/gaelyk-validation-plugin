import com.yannart.validation.ServerSideValidator
import com.yannart.validation.json.JSR303ToJSONConstraintDescriptor
 
binding {

	// Server side Validator
	validator=new ServerSideValidator()
	
	// JSON validation descriptor that generates JQuery validation descriptor
	validationDescriptor = JSR303ToJSONConstraintDescriptor.getCachedInstance()
	
	// JSON validation descriptor that generates JQuery validation descriptor
	validationDescriptorNoCache = JSR303ToJSONConstraintDescriptor.getInstance()
}

