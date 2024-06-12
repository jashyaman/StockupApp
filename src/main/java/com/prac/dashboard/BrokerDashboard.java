package com.prac.dashboard;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class BrokerDashboard
 */
public class BrokerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerDashboard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();

		if(cookies == null)
		{
			response.sendRedirect("/stockapp/index.jsp");
			return;
		}
		else
		{
			Boolean check = false;
			for (Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase("user_type"))
				{
					check = true;
				}
			}
			if(!check)
			{
				response.sendRedirect("/stockapp/index.jsp");
				return;
			}
		}
		getCompanyAuthList(request);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BrokerDashboard.jsp");
		
        if (dispatcher != null){
         dispatcher.forward(request, response);
        }  
		
	}

	private void getCompanyAuthList(HttpServletRequest request) {
		Statement stmt = DatabaseConn.createDBConn();
		StringBuilder sb = new StringBuilder();
		String username = new String();
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("user_type"))
				
				username = cookie.getValue().split("_")[0];
			
		}
		
		if(stmt == null)
		{
			System.out.println("stmt is null");
		}
		try {
			ResultSet rs = stmt.executeQuery("SELECT AUTHORIZED,COMPANYNAME FROM COMPANY WHERE USERNAME = '" + username + "'");
			if(rs == null){
				System.out.println("result set is null");
			}
			else
			{
			    while(rs.next())
			    {
					Boolean s = (Boolean) rs.getObject(1);
					if(s){
						
					}
					else
					{
						sb.append(rs.getObject(2).toString());
							sb.append(',');
					}
				}
				if(sb.toString().length() == 0)
				{
					request.setAttribute("count", 0);
				}
				else
				{
				request.setAttribute("count", 1);
				String strar[] = sb.toString().substring(0, sb.toString().length()-1).split(",");
				request.setAttribute("auth_list", strar);
				request.setAttribute("username", username);
				}
			}
		
		rs.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
