<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <!-- <script src="A:\\JavaWorkSpaceJuly\\Stock\\WebContent\\javascript.js"></script> -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  function validateForm() {
	    var fname = document.forms["UserRegistration"]["firstname"].value;
	    var lname = document.forms["UserRegistration"]["lastname"].value;
	    var uname = document.forms["UserRegistration"]["username"].value; 
	    var pass = document.forms["UserRegistration"]["password"].value; 
	    var cpass = document.forms["UserRegistration"]["cpassword"].value;
	    var email = document.forms["UserRegistration"]["email"].value;
	    
	    
	    if(cpass == pass){
	    if (fname == null || fname == "" || lname == null || lname == "" || 
	    		uname == null || uname == "" || pass == null || pass == "" || email == null || email =="") {
	        alert("All fields must be filled out");
	        return false;
	    }else
	    	{
	    		return true;
	    	}
		}
	    else
	    	{
	    	alert("Passwords do not match")
	    	return false
	    	}
  }
  
  $(function tabsel() {
	    $( "#tabs" ).tabs();
	  });
  </script>
  </head>
<body>
<%@include file="header.jsp" %>
<div id="tabs">
<ul >
    <li class="active"><a href="#tabs-1">Basic Details</a></li>
    <li><a href="#tabs-2">Banking Details</a></li>
    <li><a href="#tabs-3">Location Info</a></li>
  </ul>
<div class="container-fluid">
<form name="UserRegistration" action="${pageContext.request.contextPath}/RegisterServlet" method="POST" onsubmit="return validateForm()">
<div id="tabs-1">
First name:<br>
<input type="text" name="firstname" id="firstname">
<br>
Last name:<br>
<input type="text" name="lastname">
<br>
Username:<br>
<input type="text" name="username">
<br>
Password:<br>
<input type="password" name="password">
<br>
Confirm Password:<br>
<input type="password" name="cpassword">
<br>
<input type="radio" name="usertype" value="admin"> Admin User<br>
<input type="radio" name="usertype" value="normal" checked> Normal User<br>
<br>
Email:<br>
<input type="text" name="email">
</div>
<div id="tabs-2">
    Bank account number:<br>
<input type="text" name="baccount"><br>
   Full Name Registered with bank:<br>
<input type="text" name="holder"><br>
   Balance Available:<br>
<input type="text" name="balance"><br>
<br>
  </div>
  <div id="tabs-3">
      Address:<br>
<input type="text" name="addr"><br>
  Apt No:<br>
<input type="text" name="apt"><br>
   City:<br>
<input type="text" name="city"><br>
 State:<br>
<input type="text" name="state"><br>
  </div>
<br><br>
<input type="submit" value="Register">
</form>
</div>
</div>
</body>
</html>