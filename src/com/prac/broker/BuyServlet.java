package com.prac.broker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GenerateQuery;
import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final String txn_detail = "tradedetails";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> em = request.getParameterNames();
		HashMap<String, String> map = new HashMap<String,String>();
		while (em.hasMoreElements()) {
			String string = (String) em.nextElement();
			if(string.equalsIgnoreCase(txn_detail) || string.equalsIgnoreCase("qty"))
			{
				String value = request.getParameter(string);
				map.put(string, value);
				
			}
		}
		String tradeDetails = map.get(txn_detail);
		tradeDetails = tradeDetails.replaceAll("[\\r\\n\\t]", " ");
		tradeDetails = tradeDetails.replaceAll("  ", " ");
		
		map.remove(txn_detail);
		
		map.put(txn_detail,tradeDetails);
		
		
		
		
		
		
		try {
			if(CreateBuyTransaction(map,request.getCookies()).equalsIgnoreCase("SUCCESSFUL")){
				response.sendRedirect("/Stock/TradeServlet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

	private String js_alertBox(String msg,int type) {
		StringBuilder sb = new StringBuilder();
		if(type == 1)
		{
			sb.append("<html>");
			  sb.append("<head><title>Servlet JDBC</title></head>");
			  sb.append("<body>");
			  sb.append("<script language='JavaScript'>alert('"+msg+"');</script>");
			  sb.append("</body></html>");
		}
		else if(type == 2)
		{
			sb.append("<html>");
			  sb.append("<head><title>Servlet JDBC</title></head>");
			  sb.append("<body>");
			  sb.append("<script language='JavaScript'>window.location=\""+msg+"\";</script>");
			  sb.append("</body></html>");
		}
		
		return sb.toString();
	}

	private String CreateBuyTransaction(HashMap<String, String> map, Cookie[] cookies) throws SQLException {
		
		String[] txn_details = map.get(txn_detail).split(" ");
		String jsession = new String(),username = new String(),userid = new String(),accountno = new String(),balance = new String();
			
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("JSESSIONID"))
			{
				jsession = cookie.getValue();
			}
		}
			
		if(jsession != null)
		{
			
			ResultSet rs =DatabaseConn.ExecuteSelectQuery(GenerateQuery.getUsernameFromJSessionId(jsession));
			
			if(rs != null)
			{
				while(rs.next())
				{
					username = rs.getObject(1).toString();
				}
			}else return "error occurred during transaction";
			
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getUserIDFromUsername(username));
			
			if(rs != null)
			{
				while(rs.next())
				{
					userid = rs.getObject(1).toString();
				}
			}else return "error occurred during transaction";
			
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getAccountdetailsfromUserId(userid));
			
		
			if(rs!= null)
			{
				while(rs.next())
				{
					accountno = rs.getObject(1).toString();
					balance = rs.getObject(2).toString();
				}
			}else return "error occurred during transaction";
			
			
			System.out.println("INSIDE CREATE BUY TRANSACTION - AFTER ALL THE SELECT QUERIES HAVE BEEN EXECUTED.");
			
			double price = Double.parseDouble(txn_details[3]);
			
			double qty = Double.parseDouble(map.get("qty"));
			
			System.out.println(price + " x " + qty + " = "+  price*qty);
			
			if(Double.parseDouble(balance) < price*qty)
			{
				return "INSUFFICIENT FUNDS";
			}
			else
			{
				DatabaseConn.ExecuteQuery(GenerateQuery.InsertBuyTransaction(accountno, userid, price*qty));
				return "SUCCESSFUL";
				
			}
		
		
		}else return "error occurred during transaction";
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
