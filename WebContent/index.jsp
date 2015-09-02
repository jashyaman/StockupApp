<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StockUp App</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link href="https://metroui.org.ua/css/metro.css" rel="stylesheet">
	<script type="text/javascript" src="WebContent/js/canvasjs.min.js"></script>
<script type="text/javascript">
	window.onload = function () {

		var dps = []; // dataPoints

		var chart = new CanvasJS.Chart("chartContainer",{
			title :{
				text: "Live Random Data"
			},			
			data: [{
				type: "line",
				dataPoints: dps 
			}]
		});

		var xVal = 0;
		var yVal = 100;	
		var updateInterval = 100;
		var dataLength = 500; // number of dataPoints visible at any point

		var updateChart = function (count) {
			count = count || 1;
			// count is number of times loop runs to generate random dataPoints.
			
			for (var j = 0; j < count; j++) {	
 				yVal = yVal +  Math.round(5 + Math.random() *(-5-5));
 				dps.push({
 					x: xVal,
 					y: yVal
 				});
 				xVal++;
 			};
 			if (dps.length > dataLength)
			{
				dps.shift();				
			}
			
			chart.render();		

		};

		// generates first set of dataPoints
		updateChart(dataLength); 

		// update chart after specified time. 
		setInterval(function(){updateChart()}, updateInterval); 

	}
	</script>
	
    <%
    
    Cookie[] cookies = request.getCookies();
	String label,Home_label;
	if(cookies == null)
	{
		label = "<a href=\"/Stock/Login.jsp\">Login</a>";
		Home_label = "<a href=\"/Stock/index.jsp\">Home</a>";
		
	}
	else
	{
		Boolean checkIfbrowserhasAusercookie = false,isAdmin = false;
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("user_type"))
			{
				checkIfbrowserhasAusercookie = true;
				if(cookie.getValue().split("_")[1].equals("true"))
				{
					isAdmin = true;
				}
				else
				{
					isAdmin = false;
				}
				
			}
		}
		if(!checkIfbrowserhasAusercookie)
		{
			label = "<a href=\"/Stock/Login.jsp\">Login</a>";
			Home_label = "<a href=\"/Stock/index.jsp\">Home</a>";
		}
		else
		{
			
			label =  "<a href=\"LogoutServlet\">Logout</a>";
			if(isAdmin)
				Home_label = "<a href=\"/Stock/AdminDashboard\">Admin Home</a>";
			else
				Home_label = "<a href=\"/Stock/BrokerDashboard\">Broker Home</a>";
		}
	}
  %>
  
</head>
<body  background="http://investorplace.com/wp-content/uploads/2014/12/Nasdaq.jpg" style="background-attachment: fixed">		
<div class="container-fluid" id="ex" >
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

<br>
<br>
<br>
</div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;text-align: center;vertical-align:middle;"><a href="http://money.cnn.com/"><img width="1500" height="100%" src="http://s2.postimg.org/9n8d3ay7d/CNN_Icon.png"></a></div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;text-align: center;vertical-align:middle; "><a href="http://www.bloomberg.com/"><img src="http://s1.postimg.org/o73fxojtb/rsz_12.jpg"></a></div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;text-align: center;vertical-align:middle; "><a href="http://www.wsj.com/"><img src="http://s8.postimg.org/wrscr26id/WSJ_logo.jpg"></a></div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;text-align: center;vertical-align:middle; "><a href="http://www.businessinsider.com/"><img src="http://s13.postimg.org/3tzgkrzs7/07_business_insider_logo.png"></a></div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;text-align: center;vertical-align:middle; "><a href="http://www.cbsnews.com/moneywatch/"><img src="http://s2.postimg.org/830qg1d49/Money_Watch_Logo.jpg"></a></div>
<div class="tile-large" style="background-color:transparent;width: 400px;display: inline-block;vertical-align:baseline; "><a href="http://www.reuters.com/finance/markets"><img src="http://s8.postimg.org/7o1ojqpo5/Reuters_logo.png"></a></div>
</div>
<div>
<div>
</div>
<div id="chartContainer" style="height: 300px; width:100%;">
	</div>
</div>
<footer>
<div style="background: lightblue">
  <p>All Rights Reserved</p>
  <p>Contact information: <a href="mailto:shyam_@live.in">
  SHYAM_@LIVE.IN</a>.</p>
  </div>
</footer>
	</body>	
</html>