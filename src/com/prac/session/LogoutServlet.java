package com.prac.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getMaxAge() != 0)
				{
				System.out.println("Cookies: "+cookies[i].getMaxAge());
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				}
				
			}
		}
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  out.println("<html>");
		  out.println("<head><title>Servlet JDBC</title></head>");
		  out.println("<body>");
		out.print("<script language='JavaScript'>alert('Logged out successfully'); window.location=\"index.jsp\";</script>");
		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
