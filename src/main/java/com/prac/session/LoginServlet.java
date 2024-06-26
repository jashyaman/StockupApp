package com.prac.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.register.RegisterServlet;

import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		super.init();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Loading login screen...").append(request.getContextPath());

		String filename = "/stockapp/WEB-INF/jsp/Login.jsp" ;
		ServletContext context = this.getServletContext(); 
		String pathname =context.getRealPath(filename); 
		
		System.out.println(pathname);
		
		response.sendRedirect(pathname);
		
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String pass = new String();
		boolean userType = true;
		int count = 0;
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return;
			}; 
		
			try{
				con =DriverManager.getConnection 
						  ("jdbc:mysql://localhost/stockmarket","root","root");
				stmt = con.createStatement();
			}catch(SQLException e)
			{
				System.out.println("issues with connection");
			}
			Enumeration<String> en = request.getParameterNames();
			HashMap<String, String> params = new HashMap<String,String>();
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				params.put(paramName,request.getParameter(paramName));
				System.out.println(paramName + " " + params.get(paramName));
			}
			try {
			//	System.out.println(params.get(RegisterServlet.f_username));
				rs = stmt.executeQuery("SELECT username,password,user_type FROM User where username ='"+params.get(RegisterServlet.f_username)+"'");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("issues with select query.");
			}
			String str_usertype = new String();
			try{
				
			while(rs.next()){
				count+=1;
				  System.out.println(rs.getObject(1).toString());
				  System.out.println(pass = rs.getObject(2).toString());
				  System.out.print(" user type : ");
				  str_usertype = rs.getObject(3).toString();
				  System.out.println(str_usertype);
				  }
			
			System.out.println(count);
			
			if(str_usertype.equals("false"))
			{
				userType = false;
			}else
			{
				userType = true;
			}
			
			
			if(params.get(RegisterServlet.f_password).equals(pass))
			{
				
				CreateSession(params,request.getCookies(), request, response);
				
				if(!userType)
				{
					Cookie loginCookie = new Cookie("user_type",params.get(RegisterServlet.f_username)+"_"+userType);
		            loginCookie.setMaxAge(30*60);
		            response.addCookie(loginCookie);
					response.sendRedirect("/stockapp/BrokerDashboard");
					return;
				}
				else
				{
					Cookie loginCookie = new Cookie("user_type",params.get(RegisterServlet.f_username)+"_"+userType);
		            loginCookie.setMaxAge(30*60);
		            response.addCookie(loginCookie);
					response.sendRedirect("/stockapp/AdminDashboard");
					return;
				}
				
			}
			else
			{
				response.setContentType("text/html");
				  PrintWriter out = response.getWriter();
				  out.println("<html>");
				  out.println("<head><title>Servlet JDBC</title></head>");
				  out.println("<body>");
				out.print("<script language='JavaScript'>alert('Invalid credentials'); window.location=\"Login.jsp\";</script>");
				out.println("</body></html>");
			}
			
			}
			catch(SQLException e)
			{
				System.out.println("issues with printing data.");
			}
			finally
			{
				try {
				if(con != null)
				{
					con.close();
					con = null;
				}
				if(stmt != null)
				{
					stmt.close();
					stmt = null;
				}
				if(rs != null)
				{
					
						rs.close();
					
					rs = null;
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
	}

	private void CreateSession(HashMap<String, String> params, Cookie[] cookies,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("From Create Session");
		String jsession = new String();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("JSESSIONID"))
			{
				jsession = cookie.getValue();
				System.out.println(jsession);
			}
		}
		
		
		String username = new String();
		
		for (String key : params.keySet()) {
			System.out.println(key + " " + params.get(key));
			if(key.equalsIgnoreCase("username"))
			{
				username = params.get(key);
			}
			
		}
		System.out.println(username);
		
		String sql = "INSERT INTO SESSION (USERNAME, SESSIONID,time) VALUES('"+username+"','"+jsession+"',DATE_FORMAT(now(), '%y-%m-%d %h:%m:%s'))";
		
			try {
				DatabaseConn.ExecuteQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				request.getSession().invalidate();
				try {
					response.sendRedirect("/stockapp/index.jsp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			}
		
		
		
		
	}

}
