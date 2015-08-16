<%
    Cookie[] cookies = request.getCookies();	
	String label,Home_label,button_equivalent ="error";
	if(cookies == null)
	{
		label = "<a href=\"Login.jsp\">Login</a>";
		button_equivalent = "<button class=\"pure-button pure-button-primary\" onclick=\"login()\">Login</button>";
		Home_label = "<a href=\"/Stock/index.jsp\">Home</a>";
		
	}
	else
	{
		Boolean check = false,Admin = false;
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("user_type"))
			{
				check = true;
				if(cookie.getValue().split("_")[1].equals("true"))
				{
					Admin = true;
				}
				else
				{
					Admin = false;
				}
				
			}
		}
		if(!check)
		{
			label = "<a href=\"Login.jsp\">Login</a>";
			button_equivalent = "";
			Home_label = "<a href=\"/Stock/index.jsp\">Home</a>";
		}
		else
		{
			
			label =  "<a href=\"LogoutServlet\">Logout</a>";
			button_equivalent = "<button class=\"pure-button pure-button-primary\" onclick=\"logout()\">Logout</button>";
			if(Admin)
				Home_label = "<a href=\"/Stock/AdminDashboard\">Admin Home</a>";
			else
				Home_label = "<a href=\"/Stock/BrokerDashboard\">Broker Home</a>";
		}
	}
  %>
  
<script type="text/javascript">
function home(){
   window.location = "http://localhost:8080/Stock/index.jsp";
}
function logout(){
	window.location = "http://localhost:8080/Stock/LogoutServlet";
}
function login(){
	window.location = "http://localhost:8080/Stock/LoginServlet";   //// <<<<<<<< 
}
</script>

<div>
<button class="pure-button pure-button-primary" onclick="home()">Home</button>

<%=button_equivalent%>
</div>
