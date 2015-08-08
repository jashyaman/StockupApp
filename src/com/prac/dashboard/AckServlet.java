package com.prac.dashboard;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class AckServlet
 */
@WebServlet("/AckServlet")
public class AckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AckServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
Statement stmt = DatabaseConn.createDBConn();
		
		Enumeration<String> em = request.getParameterNames();
		HashMap<String, String> map = new HashMap<String,String>();
		while (em.hasMoreElements()) {
			String string = (String) em.nextElement();
			if(string.equalsIgnoreCase("companyname") || string.equalsIgnoreCase("username"))
			{
				String value = request.getParameter(string);
				map.put(string, value);
				
			}
		}
		
		
		
		System.out.println(map.get("username") + "' and companyname = '"+map.get("companyname"));
		try {
			String sql = "UPDATE COMPANY SET AUTHORIZED = true where USERNAME = '" + map.get("username") + "' and companyname = '"+map.get("companyname")+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		 
		response.sendRedirect("/Stock/BrokerDashboard");
		return;

				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
