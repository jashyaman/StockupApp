package com.proj.register;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBConnection
 */
@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection() {
        super();
    }
    
    

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  out.println("<html>");
		  out.println("<head><title>Servlet JDBC</title></head>");
		  out.println("<body>");
		  out.println("<h1>Servlet JDBC</h1>");

		  // connecting to database
		  Connection con = null;  
		  Statement stmt = null;
		  ResultSet rs = null;
		  try {
			  try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("Where is your MySQL JDBC Driver?");
					e.printStackTrace();
					return;
				}; 
		
				 con =DriverManager.getConnection 
						  ("jdbc:mysql://localhost/stockmarket","root","root");
						  stmt = con.createStatement();
		  rs = stmt.executeQuery("SELECT * FROM User");
		  // displaying records
		  while(rs.next())
		  {
		  out.print(rs.getObject(1).toString());
		  out.print("\t\t\t");
		  out.print(rs.getObject(2).toString());
		  out.print("<br>");
		  }
		  
		  System.out.println("Over here");
		  
		
		  
		/*  stmt.executeUpdate("INSERT INTO User (first_name, last_name, is_admin, num_points) "
		        +"VALUES ('Fred', 'Flinstone', false, 10000)");
		  */
		  
		  out.println("</body></html>");
		  } catch (SQLException e) {
		 throw new ServletException("Servlet Could not display records.", e);
		  } finally {
		  try {
		  if(rs != null) {
		  rs.close();
		  rs = null;
		  }
		  if(stmt != null) {
		  stmt.close();
		  stmt = null;
		  }
		  if(con != null) {
		  con.close();
		  con = null;
		  }
		  } catch (SQLException e) {}
		  }
		  out.close();
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("from: get() Served at: ").append(request.getContextPath());
		
		Enumeration<String> en = request.getParameterNames();
		 int count =1;
		  while(en.hasMoreElements())
		  {
			  count++;
			  String paramName = (String) en.nextElement();
			  System.out.println("param " + paramName);
			
			System.out.println(paramName + " = " + request.getParameter(paramName) + "<br/>");
		  }
		  System.out.println(count);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
			  
		//doGet(request, response);
	}

}
