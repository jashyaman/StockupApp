package com.miscellaneous;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

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

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("here");
		String email = new String();
Enumeration<String> em = request.getParameterNames();
	
		
		
		
		while(em.hasMoreElements())
		{
			String string = (String) em.nextElement();
			if(string.equalsIgnoreCase("fp_email"))
			{
				email = request.getParameter(string);
				sendPasswordResetEmail(email);
			//TODO	response.getWriter().append("<html<head><title>forgot password</head><body><script language='JavaScript'> alert(\"An email has been sent to "+email +" with your new password.</script></body></html>").append(request.getContextPath());
			
				
				response.sendRedirect("/stockapp/index.jsp");
				
			}
			
		}
		System.out.println("email is " + email);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
private void sendPasswordResetEmail(String email) {
		

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
			Random rnd = new Random();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("stockmarketappli@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("[assword reset Request");
			String pass = "n@wpass"+ rnd.nextInt(10) + rnd.nextInt(20) + "5G%" + rnd.nextInt(2);
			 String body = "new password is " + pass;
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");
			   System.out.println("message sent successfully...."); 
			   
			   Statement stmt = miscellaneous.DatabaseConn.createDBConn();
			   
			   
			   String sql = "UPDATE USER SET password = '"+pass+"' WHERE EMAIL='"+email+"'";
			   
			   
			   int i = stmt.executeUpdate(sql);
			   
			   System.out.println(i);

			   

			   
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		  
		   

		   
	}


}
