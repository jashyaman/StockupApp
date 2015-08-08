package com.prac.dashboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();

		if(cookies == null)
		{
			response.sendRedirect("/Stock/index.jsp");
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
				response.sendRedirect("/Stock/index.jsp");
				return;
			}
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminDashboard.jsp");
        if (dispatcher != null){
         dispatcher.forward(request, response);
         }  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
