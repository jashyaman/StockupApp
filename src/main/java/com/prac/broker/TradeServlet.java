package com.prac.broker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GenerateQuery;
import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class TradeServlet
 */
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<StockData> StockArray = new ArrayList<StockData>();
	ArrayList<Stock_Portfolio> stock_portfolio = new ArrayList<Stock_Portfolio>();
	String MarqueeStock = new String();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String jsession = new String();
		if(cookies == null)
		{
			response.sendRedirect("/stockapp/index.jsp");
			return;
		}
		else
		{
			Boolean check = false;
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName());
				if(cookie.getName().equalsIgnoreCase("user_type"))
				{
					check = true;
				}
				 if(cookie.getName().equalsIgnoreCase("JSESSIONID"))
				 {
					 
					 jsession = cookie.getValue();
					 
				 }
			}
			
			if(!check)
			{
				response.sendRedirect("/stockapp/index.jsp");
				return;
			}
		}

		/*
		 * with the jsessionid, the logged in user's portfolio is fetched from the database and stored.
		 */
		stock_portfolio = genPortfolio(jsession);
		
		
		Statement stmt = DatabaseConn.createDBConn();
		
		String sql = "SELECT SYMBOL, cNAME, LastSale, MarketCap, startYear, sector, industry, Summary_Quote from company_origin";
		
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			StockArray = null;
			StockArray = new ArrayList();
			int i =0;
			while(rs.next())
			{
				double price = 0.0;
				try{
					price = Double.parseDouble(rs.getString(3));
				}
				catch(NumberFormatException e)
				{
					price = 0.0;
				}
				StockData Stockinfo = new StockData(); 
				try{
			 Stockinfo = new StockData(i,rs.getString(1), rs.getString(2),price,rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),rs.getString(8));
				}
				catch(NumberFormatException e)
				{
				}
			StockArray.add(Stockinfo);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.service(request, response);
	}

	private ArrayList<Stock_Portfolio> genPortfolio(String jsession) {
		ArrayList<Stock_Portfolio> stock_portfolio = new ArrayList<Stock_Portfolio>();
		
		
		ResultSet rs = null;
		try {
			 rs = DatabaseConn.ExecuteSelectQuery(GenerateQuery.getStock_portfolio_data(jsession));
			 
			 while(rs.next())
			 {
				 //stock_portfolio.price,stock_data.price 
				 Stock_Portfolio s_p = new Stock_Portfolio();
				 s_p.setSymbol(rs.getString(1));
				 s_p.setStockname(rs.getString(2));
				 s_p.setQty_in_hand(rs.getInt(3));
				 s_p.setPrice_bought(rs.getDouble(4));
				 s_p.setCurrent_price(rs.getDouble(5));
				 
				 stock_portfolio.add(s_p);
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock_portfolio;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		StringBuilder sb = null;
				sb = new StringBuilder();
		
		for (int i = 0; i < StockArray.size(); i++) {
			sb.append(StockArray.get(i).getSymbol());
			sb.append(":");
			sb.append(StockArray.get(i).getPrice());
			sb.append("::  ");
		}
		MarqueeStock = null;
		MarqueeStock = new String();
		
		MarqueeStock = sb.toString();
		request.removeAttribute("StockArray");
		request.removeAttribute("StockData");
		request.setAttribute("StockArray", MarqueeStock);
		request.setAttribute("StockData", StockArray);
		
		request.setAttribute("Stock_portfolio", stock_portfolio);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TradeScreen.jsp");
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
