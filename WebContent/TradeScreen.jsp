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
      <link rel="stylesheet" type="text/css" href="css/style.css">
  
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

var row

$(document).ready(function () {  
	
	 $( "#tabs" ).tabs();

	 $('#stockdataTable tr').click(function (event) {
	    	$('#resultTable').append($(this).clone());
	    	//alert($(this).clone().text().replace("\n","").replace("\r","").replace("\v","").replace("\f",""));
	    	
	    	var check = $(this).clone().text().replace(/\s\s+/g, ' ');
	    	alert(check);
	    	$("#tradeDetail").val(check);
		
		 });
	 
});

function reg (text){
  	
  	 return true
  }
  
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
    <li class="active"><a href="#tabs-2">Sell</a></li>
  </ul>
<div id="tabs-1">
 <div class="col-md-6" style="height:200px;width: 500px;overflow-y: scroll;" >
<table style="border: 1px solid black;padding:2px; table-layout:initial;" id="stockdataTable">
      <tr style="border: 1px solid black;padding:5px" id="header">
        <th style="border: 1px solid black;padding:5px">Symbol</th>
        <th style="border: 1px solid black;padding:5px">Name</th>
        <th style="border: 1px solid black;padding:5px">Price</th>
        <th style="border: 1px solid black;padding:5px">MarketCap</th>
      </tr>
      <c:forEach var="stockRow" items="${requestScope.StockData}">
      
      <tr style="border: 1px solid black;padding:5px" id="${stockRow.getIndex() }">
      	<td id="1" style="border: 1px solid black;padding:5px;">${stockRow.getSymbol() }</td>
      	<td id="2" style="border: 1px solid black;padding:5px;word-wrap: break-word">${stockRow.getStockName() }</td>
      	<td id="3" style="border: 1px solid black;padding:5px">${stockRow.getPrice() }</td>
      	<td id="4" style="border: 1px solid black;padding:5px">${stockRow.getMarketCap() }</td>
      </tr>
      </c:forEach>
</table>
</div>
    <div class="col-md-6" >
	<div  style="height:200px;width: 500px;overflow-y: scroll;">
	
<table style="border: 2px solid black;padding:5px;table-layout: fixed" id="resultTable">
      <tr style="border: 1px solid black;padding:5px" id="1">
        <th style="border: 1px solid black;padding:5px">Symbol</th>
        <th style="border: 1px solid black;padding:5px">Name</th>
        <th style="border: 1px solid black;padding:5px">Price</th>
        <th style="border: 1px solid black;padding:5px">MarketCap</th>
      </tr>
      <tr style="border: 1px solid black;padding:5px" id="1">
      </tr>
 </table>
 </div>
 <br>
  
 <form name="buyTrade" method="GET" action="/Stock/BuyServlet">
 <input type="hidden" name="tradedetails" value="" id="tradeDetail"></input>
 Qty: <input type="text" name="qty">
 <input type="submit" value="Buy">
 </form>
	</div>
    </div>
<div id="tabs-2">
<div class="col-md-6" style="height:200px;width: 500px;overflow-y: scroll;" >
<table style="border: 1px solid black;padding:2px; table-layout:initial;" id="stockdataTable">
      <tr style="border: 1px solid black;padding:5px" id="header">
        <th style="border: 1px solid black;padding:5px">Symbol</th>
        <th style="border: 1px solid black;padding:5px">Name</th>
        <th style="border: 1px solid black;padding:5px">qty</th>
        <th style="border: 1px solid black;padding:5px">bought for</th>
        <th style="border: 1px solid black;padding:5px">current price</th>
      </tr>
      <c:forEach var="stockfolio" items="${requestScope.Stock_portfolio}">
      
      <tr style="border: 1px solid black;padding:5px">
      	<td id="1" style="border: 1px solid black;padding:5px;">${stockfolio.getSymbol() }</td>
      	<td id="2" style="border: 1px solid black;padding:5px;word-wrap: break-word">${stockfolio.getStockname() }</td>
      	<td id="3" style="border: 1px solid black;padding:5px">${stockfolio.getQty_in_hand() }</td>
      	<td id="4" style="border: 1px solid black;padding:5px">${stockfolio.getPrice_bought() }</td>
      	<td id="5" style="border: 1px solid black;padding:5px">${stockfolio.getCurrent_price() }</td>
      </tr>
      </c:forEach>
</table>
</div>
</div>
</div>
</body>
</html>
