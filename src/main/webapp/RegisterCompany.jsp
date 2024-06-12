<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Registration</title>
<script	type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<script>
$(document).ready(function() {
	$(".selection").change();
});

$(".selection").change(
		function() {
			idComp = $(this).attr('id');
			var selText = $(this).find(":selected").text();
			if(idComp == "usernames"){
				alert("id "+ idComp);
				$("#dd_sel_hidden").val(selText);
				alert("username " + selText )
			}
		});

  function validateRCF() {
  var companyname = document.forms["CompanyReg"]["CompanyName"].value;
  var website = document.forms["CompanyReg"]["website"].value;
  
  if (companyname == null || website == "" || companyname == null || website == "") {
      alert("All Fields should be filled");
      return false;
  }else
  	{
	  var selText = $(".selection").find(":selected").text();
	  	if(selText == "None"){
	  		alert("Kindly make a user selection, in case no user exists in the list, kindly register a personnel before registering a company.");
	  		
	  		return false;
	  	}else
	  		{
	  		alert("username " + selText);
	  		$("#dd_sel_hidden").val(selText);
  			return true;
  			}
  }
  }
  
  
  </script>
<form name="CompanyReg" action="/stockapp/RegisterCompanyServlet" method="post" onsubmit="return validateRCF()">
Company Name:<br>
<input type ="text" name="CompanyName"><br><br>
Company Website:<br>
<input type ="text" name="website"><br><br>
Associated UserName: 
<select class="selection" id="usernames">
<option value="None">None</option>
<c:forEach var="username" items="${requestScope.usernames}">
 <option value="${username }">${username }</option>
</c:forEach>

</select>
<br><br>

<input type="hidden" name="dd_sel_hidden" value="" id="dd_sel_hidden"></input>

<input type="submit" value="Submit">

</form>
</body>
</html>
