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
  </script>
  </head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">Basic Details</a></li>
    <li><a href="#">Location Info</a></li>
    <li><a href="#">Registration form</a></li>
  </ul>
<div class="container-fluid">
<form name="UserRegistration" action="${pageContext.request.contextPath}/RegisterServlet" method="POST" onsubmit="return validateForm()">
First name:<br>
<input type="text" name="firstname">
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
<input type="email" name="email">
<br><br>
<input type="submit" value="Register">
</form>
</div>
</body>
</html>