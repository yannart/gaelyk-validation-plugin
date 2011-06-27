import com.yannart.entity.User
import com.yannart.validation.*

//Client side validation
def clientValidationRules = JSR303ToJson.getJsonValidationRules(User.class)

log.info "Validation rules: ${clientValidationRules}"

request.setAttribute 'client-validation-rules', clientValidationRules

forward '/validation-demo/form.gtpl'
