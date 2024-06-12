# StockupApp
JSP Servlets based Stock Market Web application

The application uses real time data loaded on to the database by means of sql dump files.
All standard actions associated with stock trading are available to perform.

Currently working on adding more features of loading real time data through API as opposed to running an SQL job.
Working on adding more front end features with javascript.

steps to run on local.
install mysql server in local to run a known port and note down username password
install tomcat server
setup project in ide.
run mvn install to create a deployable war.
add the war to deployment list of tomcat and proceed to start tomcat. 
