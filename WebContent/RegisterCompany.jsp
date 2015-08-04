<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Registration</title>
</head>
<body>
<form action="/RegisterCompanyServlet" method="post" onsubmit="return validateRCF()">
Company Name:<tab>
<input type ="text" name="CompanyName">
<br><br>
Company Website:<tab>
<input type ="text" name="website">
<br><br>
<%
import java.sql.DriverManager;
try
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection 
			  ("jdbc:mysql://localhost/stockmarket","root","root");
}
catch(Exception e)
{
	
}

%>
</form>
</body>
</html>