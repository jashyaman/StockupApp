<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bootstrap Example</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script type="text/javascript">
   
  </script>
    <%
    
    Cookie[] cookies = request.getCookies();
	String label,Home_label;
	if(cookies == null)
	{
		label = "<a href=\"Login.jsp\">Login</a>";
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
			Home_label = "<a href=\"/Stock/index.jsp\">Home</a>";
		}
		else
		{
			
			label =  "<a href=\"LogoutServlet\">Logout</a>";
			if(Admin)
				Home_label = "<a href=\"/Stock/AdminDashboard\">Admin Home</a>";
			else
				Home_label = "<a href=\"/Stock/BrokerDashboard\">Broker Home</a>";
		}
	}
  %>
  
</head>
<body>		
<div class="container-fluid" id="ex" style="background-image: url('http://investorplace.com/wp-content/uploads/2014/12/Nasdaq.jpg')">
<div class="container-fluid" style="background: lightblue" id="tr">
 <center><h1 ><b id="opw" >StockUp</b></h1>
  <h4 ><b id="w">An Online Trading portal</b></h4></center> 
</div>
<div class="container-fluid" style=" opacity: 0.5;background: azure">      
    <div class="col-sm-2" ><b><%=Home_label%></b></div>
    <div class="col-sm-2" ><b><%=label%></b></div>
    <div class="col-sm-2" ><b><a href="Register.jsp">Register</a></b></div>
    <div class="col-sm-2" ><b><a href="/Stock/RegisterCompanyServlet">Register Company</a></b></div>
    <div class="col-sm-2" ><b>Contact Us</b></div>
    <div class="col-sm-2" ><b>Developer page</b></div> 
    
</div>
<div class="container-fluid">


</div>
<div class="container-fluid" style="background-color:green;">
<div class="col-sm-2" >CNN Money</div>
<div class="col-sm-2" >Bloomberg</div>
<div class="col-sm-2" >WSJ</div>
<div class="col-sm-2" >Business Insider</div>
<div class="col-sm-2" >BusinessLine</div>
<div class="col-sm-2" >Fortune</div>
</div>
</div>
<div>

</div>
	</body>	
</html>