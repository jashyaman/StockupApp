package com.proj.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miscellaneous.DatabaseConn;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String f_firstname = "firstname";
	public static final String f_lastname = "lastname";
	public static final String f_username = "username";
	public static final String f_password = "password";
	public static final String f_usertype = "usertype";
	public static final String f_email = "email";
	
	public static HashMap<String, String> params = new HashMap<String,String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		System.out.println("Control comes here.");
		//connect to db at the init method
		// collect all form data into an object array
		// insert into the table.
		Enumeration<String> en = request.getParameterNames();
		 int count =1;
		  while(en.hasMoreElements())
		  {
			  count++;
			  String paramName = (String) en.nextElement();
			  System.out.println("param " + paramName);
			
			System.out.println(paramName + " = " + request.getParameter(paramName) );
			if(paramName != "cpassword")
			params.put(paramName, request.getParameter(paramName));
		  }
		  System.out.println(count);
		  
		 
		Statement stmt = DatabaseConn.createDBConn();
			
			
			StringBuilder insertstmt = new StringBuilder();
			insertstmt.append("INSERT INTO " + "User" +
					"(Userid,FirstName,LastName,username,password,email,createdate,user_type) VALUES (getUserID(),");
			
				insertstmt.append("'"+params.get(f_firstname)+"',");
				insertstmt.append("'"+params.get(f_lastname)+"',");
				insertstmt.append("'"+params.get(f_username)+"',");
				insertstmt.append("'"+params.get(f_password)+"',");
				insertstmt.append("'"+params.get(f_email)+"',");
				
				
				System.out.println(params.get(f_usertype) );
				
				int usertype = 0;
				if(params.get(f_usertype).equals("normal"))
					usertype = 0;
				else{
					System.out.println(usertype);
					sendAdminRequestEmail();
					   response.setContentType("text/html");
						  PrintWriter out = response.getWriter();
						  out.println("<html>");
						  out.println("<head><title>Servlet JDBC</title></head>");
						  out.println("<body>");
						out.print("<script language='JavaScript'>alert('Request Email has been sent to Admin'); window.location=\"AdminReg.html\";</script>");
						out.println("</body></html>");
						
					return;
					
				}
				
				insertstmt.append("now(),"+usertype+")");
			
			try {
				System.out.println(insertstmt.toString());
				stmt.executeUpdate(insertstmt.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/Stock/Login.jsp");
			}
			
				  
			

				
		
		
		
	

	private void sendAdminRequestEmail() {
		

		final String username = "stockmarketappli@gmail.com";
		final String password = "G8sumIT#";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("stockmarketappli@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("shyam_@live.in"));
			message.setSubject("New Account Creation Request");
			 String body = "User has requested for an Admin account. "
				   		+ "Details are as below:"
				   		+ "\nFirstName: " + params.get(f_firstname) + 
				   		"\nLastName: " + params.get(f_lastname) + 
				   		"\nusername: " + params.get(f_username) +
				   		"\npassword: " + params.get(f_password) +
				   		"\nusertype: " + params.get(f_usertype) +
				   		"\nemail: " + params.get(f_email) + 
				   		"\n---------------------------------------";
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");
			   System.out.println("message sent successfully....");  
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		  
		   

		   
	}

}
