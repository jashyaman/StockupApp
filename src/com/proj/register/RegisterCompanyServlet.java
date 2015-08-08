package com.proj.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterCompanyServlet
 */		  
@WebServlet("/RegisterCompanyServlet")
public class RegisterCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCompanyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: huhuh ").append(request.getContextPath());
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> userList = new ArrayList<String>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try{
			Connection con = DriverManager.getConnection 
					  ("jdbc:mysql://localhost/stockmarket","root","root");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("Select username from user where username not in (select username from company where authorized = 1)");

			while(rs.next())
			{
				userList.add(rs.getString(1).toString());
				
				System.out.println("1: " +rs.getString(1).toString());
			}
			System.out.println(userList.size());
			
			request.setAttribute("usernames", userList);
			
			System.out.println(request.getAttribute("usernames"));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RegisterCompany.jsp");
            if (dispatcher != null){
             dispatcher.forward(request, response);
             }  
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		Enumeration<String> en = request.getParameterNames();
		HashMap<String, String> params =  new HashMap<String,String>();
		while(en.hasMoreElements())
		{
			String paramName = (String) en.nextElement();
			String paramValue = (String) request.getParameter(paramName);
			
			System.out.println(paramName + " " + paramValue);
			params.put(paramName, paramValue);
		}
		
		
		
		
	}

}
