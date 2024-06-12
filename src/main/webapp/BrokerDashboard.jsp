<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Broker Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script	type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

     
<div class="container">
<h1>Broker Dashboard</h1>
</div>
<div class="container">
<c:set var="count" value="${requestScope.count}"/>
<c:choose>
<c:when test="${count == 0}">
<%-- no values retrieved <c:out value="${count}"/> --%>
</c:when>
 <c:otherwise>
 <c:forEach var="companynames" items="${requestScope.auth_list}">
<p>${companynames } Require your acknowledgement to be the authorized personnel for the company account. Kindly <a href="/stockapp/AckServlet?companyname=${companynames}&username=${requestScope.username}">acknowledge</a></p>
</c:forEach>
 </c:otherwise>
</c:choose>
</div>
<div class="container">        
  <div class="row">
    <div class="col-sm-2" style="background-color:lavender;"><a href="/stockapp/TradeServlet">Trade</a></div>
    <div class="col-sm-3" style="background-color:lavenderblush;"><a href="">Latest News</a></div>
    <div class="col-sm-3" style="background-color:lavender;">Market watch</div>
    <div class="col-sm-2" style="background-color:lavenderblush;">References</div>
    <div class="col-sm-2" style="background-color:lavender;"><a href="/stockapp/LogoutServlet">Logout</a></div>
  </div>
</div>



</body>
</html>
