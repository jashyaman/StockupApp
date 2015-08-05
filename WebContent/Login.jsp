<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
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
<body>
<form name="Login" action="/Stock/LoginServlet" method="POST" onsubmit="return validateLoginForm()">
Username:<br>
<input type="text" name="username">
<br>
Password:<br>
<input type="password" name="password">
<br>
<input type="submit" value="Login">
</form>
</body>
</html>