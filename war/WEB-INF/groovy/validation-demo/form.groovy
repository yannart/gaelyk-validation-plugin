import com.yannart.entity.User
import com.yannart.validation.json.JSR303ToJSONConstraintDescriptor

//Client side validation
def clientValidationRules = JSR303ToJSONConstraintDescriptor.getInstance().render(User.class)

log.info "Validation rules: ${clientValidationRules}"

request.setAttribute 'client-validation-rules', clientValidationRules

forward '/validation-demo/form.gtpl'
