package com.prac.broker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class SellServlet
 */
@WebServlet("/SellServlet")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellServlet() {
        super();
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		Enumeration<String> em = request.getParameterNames();
		String jsession = new String();
		String[] sellrequestDetails = null;
		
			
			while(em.hasMoreElements())
			{
				String paramName = em.nextElement();
				if(paramName.equalsIgnoreCase("selltradeDetails") || paramName.equalsIgnoreCase("quantity"))
				{
					map.put(paramName, request.getParameter(paramName).trim());
				}
			}
			
			sellrequestDetails = map.get("selltradeDetails").split(" ");
			System.out.println(sellrequestDetails[0]);
			
			System.out.println(sellrequestDetails[sellrequestDetails.length-3]);
			
			
			
			for (Cookie cookie : request.getCookies()) {
				if(cookie.getName().equalsIgnoreCase("JSESSIONID"))
				{
					jsession = cookie.getValue();
				}
			}
			int portfolio_qty = GetPortfolioQty(sellrequestDetails[0],jsession);
			
			System.out.println(portfolio_qty);
			int txn_id = 0;
			ResultSet rs;
			try {

			System.out.println("protfolio_qty and qty_of_sale " + portfolio_qty + Integer.parseInt(map.get("quantity")) );
			if(portfolio_qty < Integer.parseInt(map.get("quantity")))
			{
				System.out.println("insufficient qty");
			}
			else 
			{
				if(CreateSellTransaction(map,jsession).equalsIgnoreCase("SUCCESSFUL"))
				{
					rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getTxnId2(sellrequestDetails,jsession));
					double amount = 0.0;
					
					if(rs != null)
					{
						while(rs.next())
						{
							txn_id = (int)rs.getObject(1);
							amount = Double.parseDouble(rs.getString(2));
						}
					}
					else
					{
						System.out.println("error in resultset information");
					}
					DatabaseConn.ExecuteQuery(GenerateQuery.updateAccount(txn_id,amount,'s'));
					
					if(CreateSellTransactionDetail(txn_id,sellrequestDetails,jsession,Integer.parseInt(map.get("quantity"))).equalsIgnoreCase("SUCCESSFUL"))
					{
						if(updateStockPortfolioOnSell(txn_id)){
						response.sendRedirect("/Stock/TradeServlet");
						}
					}
						
				}
				else
				{
					System.out.println("ERROR IN PROCESSING");
				}
				
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		

	}



	private boolean updateStockPortfolioOnSell(int txn_id) {
		/*
		 * check if quantity of stock ordered is less than total available stock qty.
		 * get an arraylist of available stock in portfolio for the stocktickername
		 * arrange the list in ascending order of stockPrice.
		 * match the stock price as required by order qty.
		 * if the stock record is used as a whole, run a delete query.
		 * if the stock record is used partly to fulfill the request, apply an update.
		 * get stockTickername from txn_id
		 * get the quantity of stock available for the stock
		 * 
		 */
		String symbol = new String();
		int qty_of_sale = 0;
		ArrayList<Price_qty> price_qty = new ArrayList<Price_qty>();
		try {
			ResultSet rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getStocksymbol(txn_id));
			while(rs.next())
			{
				symbol = rs.getString(1);
				qty_of_sale = rs.getInt(2);
			}
			
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getStockList(symbol));
			
			while(rs.next())
			{
				 Price_qty pq = new Price_qty();
				 pq.setQty(rs.getString(1));
				 pq.setPrice(rs.getString(2));
				 pq.setTxn_id(rs.getInt(3));
				 price_qty.add(pq);
			}
			
			for (Price_qty pr_data : price_qty) 
			{
				
				if(qty_of_sale <=0)
				{
					break;
				}
				
				System.out.println(qty_of_sale);
				System.out.println(pr_data.getQty());
				if(qty_of_sale - Integer.parseInt(pr_data.getQty()) >= 0)
				{
					qty_of_sale-=Integer.parseInt(pr_data.getQty());
					DatabaseConn.ExecuteQuery(GenerateQuery.removefromStockfolioOnSell(pr_data.getTxn_id()));
				}
				else
				{
					pr_data.setQty(String.valueOf((Integer.parseInt(pr_data.getQty())-qty_of_sale)));
					DatabaseConn.ExecuteQuery(GenerateQuery.updateStockfolioOnSell(pr_data.getTxn_id(),pr_data.getQty()));
					
				}
				
			}
			return true;
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return false;
	}


	private String CreateSellTransactionDetail(int txn_id, String[] sellrequestDetails, String jsession, int qty) {
		
		try {
			DatabaseConn.ExecuteQuery(GenerateQuery.InsertTransactionDetails(txn_id, sellrequestDetails[0], Double.parseDouble(sellrequestDetails[sellrequestDetails.length-1]),qty));
			return "SUCCESSFUL";
		} catch (SQLException e) {
			return "ERROR IN CREATING SELL TRANSACTION DETAILS";
		}
		

	}


	private String CreateSellTransaction(HashMap<String, String> map, String jsession) {
		
		String qty = map.get("quantity");
		String price = map.get("selltradeDetails").split(" ")[map.get("selltradeDetails").split(" ").length-1];
		System.out.println("quantity " + qty + "\n" + " price" + price);
		ResultSet rs;
		int accountno = 0;
		int holder_id = 0;
		try {
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getAccountNo(jsession));
			while(rs.next())
			{
				accountno = rs.getInt(1);
				holder_id = rs.getInt(2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			DatabaseConn.ExecuteQuery(GenerateQuery.InsertSellTransaction(accountno,holder_id,Double.parseDouble(qty)* Double.parseDouble(price)));
			return "SUCCESSFUL";
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERROR IN INSERT SELL TRANSACTION";
		}
	}


	private int GetPortfolioQty(String symbol, String jsession) {
		ResultSet rs;
		System.out.println("symbol " + symbol);
		try {
			rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getPortfolioQty(jsession,symbol));
		
				while(rs.next())
				{
					
					return rs.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
				
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	
	public class Price_qty
	{
		String qty;
		String price;
		int txn_id;
		public int getTxn_id() {
			return txn_id;
		}
		public void setTxn_id(int txn_id) {
			this.txn_id = txn_id;
		}
		public String getQty() {
			return qty;
		}
		public void setQty(String qty) {
			this.qty = qty;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
	}
}
