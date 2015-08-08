<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <!-- <script src="A:\\JavaWorkSpaceJuly\\Stock\\WebContent\\javascript.js"></script> -->
  <script type="text/javascript">
  function validateLoginForm() {
  var username = document.forms["Login"]["username"].value;
  var password = document.forms["Login"]["password"].value;
  
  if (username == null || password == "" || username == null || password == "") {
      alert("Enter your credentials");
      return false;
  }else
  	{
  		return true;
  	}
  }
  </script>
</head>
<body >
<div class="container-fluid" style="background: lightblue;border:1px solid black">
 <center><h1 ><b id="opw" >StockUp</b></h1>
  <h4 ><b id="w">An Online Trading portal</b></h4></center> 
</div>
<div style="font-family: Lucida Console,Helvectica, sans-serif;position:absolute;width:170px;border:3px solid black;
padding: 10px;top:50%;left:55%;margin:-100px 0 0 -150px;-webkit-animation-name: example;-webkit-animation-duration: 4s;">
<form id="#formlogin" name="Login" action="/Stock/LoginServlet" method="POST" onsubmit="return validateLoginForm()">
Username:<br>
<input type="text" name="username" style="font-family: Lucida Console,Helvectica, sans-serif">
<br>
<br>
Password:<br>
<input type="password" name="password" style="font-family: Lucida Console,Helvectica, sans-serif">
<br>
<br>
<center><input class="btn btn-default btn-sm" type="submit" value="Login" style="font-family: Lucida Console,Helvectica, sans-serif" ></center>
</form>
</div>
</body>
</html>