import com.yannart.entity.User
import com.yannart.validation.json.JSR303ToJSONConstraintDescriptor

//Client side validation
def clientValidationRules = JSR303ToJSONConstraintDescriptor.getCachedInstance().render(User.class, "notValidatedByClient")
//Use JSR303ToJSONConstraintDescriptor.getInstance().render(User.class) to get an instance that do not cache the rendered JSON.
//Or call JSR303ToJSONConstraintDescriptor.getCachedInstance().render(User.class) to reset the cache for that bean

log.info "Validation rules: ${clientValidationRules}"

request.setAttribute 'client-validation-rules', clientValidationRules

forward '/validation-demo/form.gtpl'
