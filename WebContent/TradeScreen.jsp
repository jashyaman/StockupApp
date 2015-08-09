<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trading screen</title>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
      <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>
<script type="text/javascript">
function home(){
   window.location = "http://localhost:8080/Stock/index.jsp";
}
function logout(){
	window.location = "http://localhost:8080/Stock/LogoutServlet";
}
$(document).ready (function() {
    $( "#tabs" ).tabs();
  });

</script>
<div class="container-fluid" style=" opacity: 0.5;background: azure">
<div class="col-sm-8" ><h1> Trades Screen</h1></div>
<button class="pure-button pure-button-primary" onclick="home()">Home</button>
<button class="pure-button pure-button-primary" onclick="logout()">Logout</button>
</div>
<div style="background-color: #b0c4de;" >
<c:set var="stocklist" value="${requestScope.StockArray}"/>
<marquee behavior="scroll" direction="left">${stocklist}</marquee>
</div>
<div id="tabs">
<ul >
    <li class="active"><a href="#tabs-1">Buy</a></li>
    <li><a href="#tabs-2">Sell</a></li>
  </ul>
<div class="container-fluid">
<div id="tabs-1">
table with stock name and price,
table should allow selection of a row,
qty and input text for number of stock.
buy button
</div>
<div id="tabs-2">
table with stock name and price from user's portfolio
if portfolio is empty, display empty table
table should allow selection of a row,
qty and input text for number of stock, validate if qty entered is less than available stock
sell button
</div>
<br><br>
</div>
</div>
</body>
</html>