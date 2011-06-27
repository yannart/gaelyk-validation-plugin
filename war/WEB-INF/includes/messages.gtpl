<%
	def messageTypes = ['error', 'notice', 'info', 'success'] 

	messageTypes.each { type ->
		def messages = request.getAttribute(type + 'Messages')

		if(messages?.size() > 0) {
%>
			<div class="<%= type %>">
				<ul>
				<% messages.each { %>
					<li><%= it %></li>
				<% } %>
				</ul>
			</div>	
<% 		}	
	}
%>
