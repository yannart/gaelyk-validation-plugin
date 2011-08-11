<% include '/WEB-INF/includes/header.gtpl' %>
<% def user = request.getAttribute('user') %>

<script src="/js/jquery-1.6.1.min.js" type="text/javascript"></script>
<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript"> 
\$().ready(function() {
	jQuery.validator.setDefaults({errorClass: "form-validation-error"});

	// validate category form on keyup and submit
	\$("#validatedForm").validate({<%= validationDescriptor.render(com.yannart.entity.User.class) %>});
});
</script>

<% include '/WEB-INF/includes/messages.gtpl' %>

<form class="cmxform" id="validatedForm" action="/validation-demo/create.groovy" method="post"> 
 
          <fieldset> 
            <legend>User</legend> 
            <p> 
              <label for="firstname">Firstname</label><br> 
              <input type="text" name="firstname" id="firstname" value="<%= user?.firstname %>"> 
            </p>
            <p> 
              <label for="middlename">Middlename</label><br> 
              <input type="text" name="middlename" id="middlename" value="<%= user?.middlename %>"> 
            </p>
            <p> 
              <label for="lastname">Lastname</label><br> 
              <input type="text" name="lastname" id="lastname" value="<%= user?.lastname %>"> 
            </p>
            <p> 
              <label for="age">Age</label><br> 
              <input type="text" name="age" id="age" value="<%= user?.age %>"> 
            </p>
            <p> 
              <label for="notValidatedByClient">Field not validated at client side</label><br> 
              <input type="text" name="notValidatedByClient" id="notValidatedByClient" value="<%= user?.notValidatedByClient %>"> 
            </p>
            <p>
		 		<input type="checkbox" name="enabled" <%= user?.enabled == true ? "checked" : "" %>>Enabled<br> 
			</p>
            <p> 
              <input class="submit" type="submit" value="Submit"/> 
              <input type="reset" value="Reset"> 
            </p>
          </fieldset> 
</form>
 
<% include '/WEB-INF/includes/footer.gtpl' %>
