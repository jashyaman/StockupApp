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
		
		if(em == null)
		{
			System.out.println("all is lost");
		}
		while (em.hasMoreElements()) {
			String params = (String) em.nextElement();
			if(params.equalsIgnoreCase(txn_detail) || params.equalsIgnoreCase("qty"))
			{
				String value = request.getParameter(params);
				
				if(params.equalsIgnoreCase(txn_detail))
				{
					params = params.replaceAll("[\\r\\n\\t]", " ");
					params = params.replaceAll("  ", " ");
				}
				map.put(params, value);
			}
		}
		
		
		/*
		 * fetch trade details ^
		 */
		
		
		try {
			if(CreateBuyTransaction(map,request.getCookies()).equalsIgnoreCase("SUCCESSFUL")){
				
				response.sendRedirect("/Stock/TradeServlet");
			}
			else
			{
				System.out.println("ERROR IN PROCESSING");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}


	/*
	 * CreateBuyTransaction:
	 * Input: map of input request parameters
	 * 		  session cookies.
	 * step1:
	 * fetch the jsession cookie.
	 * step2:
	 * fetch teh username from jsessionid
	 * step3:
	 * get userid from username
	 * step4:
	 * get account details with userid : accountno and balance
	 * step5:
	 * calculate price.
	 * step6:
	 * insert a buy transaction in transaction table
	 * step7:
	 * Insert transaction details
	 */

	private String CreateBuyTransaction(HashMap<String, String> map, Cookie[] cookies) throws SQLException {
		
		String[] txn_details = map.get(txn_detail).split(" ");
		
		String jsession = new String(),username = new String(),userid = new String(),accountno = new String(),balance = new String();
		int txn_id  =0;
			
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
			double price = 0.0;
			
			try
			{
				price= Double.parseDouble(txn_details[3]);
			}
			catch(NumberFormatException e)
			{
				price = 0.0;
			}
			
			int qty = Integer.parseInt(map.get("qty"));
			
			
			if(Double.parseDouble(balance) < price*qty)
			{
				return "INSUFFICIENT FUNDS";
			}
			else
			{
				DatabaseConn.ExecuteQuery(GenerateQuery.InsertBuyTransaction(accountno, userid, price*qty));
				
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getTxnId(accountno, userid, price*qty));
				
				if(rs != null)
				{
					while(rs.next())
					{
						txn_id = (int)rs.getObject(1);
					}
				}
				else
				{
					System.out.println("error in resultset information");
				}
				
				
				DatabaseConn.ExecuteQuery(GenerateQuery.InsertTransactionDetails(txn_id,txn_details[1],price,qty));
				DatabaseConn.ExecuteQuery(GenerateQuery.updateAccount(txn_id,price*qty,'b'));
				DatabaseConn.ExecuteQuery(GenerateQuery.updatePortfolioOnBuy(txn_id));
			
				System.out.println("THE QUERIES HAVE BEEN EXECUTED");
				
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
