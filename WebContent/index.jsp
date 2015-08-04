<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bootstrap Example</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>		
		<form action="HelloServlet">			
			 Please enter a color <br>
			<input type="text" name="color"size="20px">
			<input type="submit" value="submit">						
		</form>	
		<div class="container-fluid">
  <h1>StockUp</h1>
  <p>Online Trading portal</p>
    <!--Home - Login - Register - About - Contact-->
    </div>
<div class="container">        
  <div class="row">
    <div class="col-sm-2" style="background-color:lavender;">Home</div>
    <div class="col-sm-3" style="background-color:lavenderblush;"><a href="Login.jsp">Login</a></div>
    <div class="col-sm-3" style="background-color:lavender;"><a href="Register.jsp">Register</a></div>
    <div class="col-sm-2" style="background-color:lavenderblush;"><a href="RegisterCompany.jsp">Register Company</a></div>
    <div class="col-sm-2" style="background-color:lavender;">Contact</div>
  </div>
</div>
<div class="container">
<h3>Trade Stocks for free <small>No conditions apply</small></h3>
<br>
<h4>Play with real time data</h4>
<h5>Stock "Quotes"</h5>
<h6>
<!-- <blockquote>
    <p>In the 20th Century, the United States endured two world wars and other traumatic and expensive military conflicts; the Depressionl a dozen or so recessions and financial panics; oil shocks; a flu epidemic; and the resignation of a disgraced president. Yet the Dow rose from 66 to a 11,497.</p>
    <footer>Warren Buffet</footer>
 </blockquote> -->
 </h6>
</div>
<div class="container">
<div class="col-sm-10" style="background-color:green;">CNN Money</div>
<div class="col-sm-10" style="background-color:green;">Bloomberg</div>
<div class="col-sm-10" style="background-color:green;">WSJ</div>
<div class="col-sm-10" style="background-color:green;">Business Insider</div>
</div>
	</body>	
</html>