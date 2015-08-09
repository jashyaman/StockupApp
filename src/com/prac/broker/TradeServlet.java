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

import miscellaneous.DatabaseConn;

/**
 * Servlet implementation class TradeServlet
 */
@WebServlet("/TradeServlet")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<StockData> StockArray = new ArrayList<StockData>();
	String MarqueeStock = new String();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		Statement stmt = DatabaseConn.createDBConn();
		
		String sql = "SELECT SYMBOL, NAME, LastSale, MarketCap, startYear, sector, industry, Summary_Quote from company_origin";
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			int i =0;
			while(rs.next())
			{
			StockData Stockinfo = new StockData(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),rs.getString(8));
			StockArray.add(Stockinfo);
				
			}
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.service(arg0, arg1);
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
		
		System.out.println(StockArray.size());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < StockArray.size(); i++) {
			sb.append(StockArray.get(i).getSymbol());
			sb.append(":");
			sb.append(StockArray.get(i).getPrice());
			sb.append("::  ");
		}
		
		MarqueeStock = sb.toString();
		request.setAttribute("StockArray", MarqueeStock);
		
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/TradeScreen.jsp");
			
	        if (dispatcher != null){
	        //	System.out.println("request dispatcher in process");
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
