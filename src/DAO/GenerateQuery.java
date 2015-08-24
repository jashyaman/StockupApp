package DAO;

import java.util.HashMap;

import com.proj.register.RegisterServlet;

public class GenerateQuery {

	public static String getUsernameFromJSessionId(String jsessionid)
	{
		return "SELECT USERNAME FROM SESSION WHERE SESSIONID ='"+jsessionid+"'";
	}
	
	public static String getUserIDFromUsername(String username)
	{
		return "select userid from user where upper(username) = upper('"+username+"')";
	}
	
	public static String getAccountdetailsfromUserId(String userId)
	{
	
		return "SELECT ACCOUNT_NO,BALANCE FROM ACCOUNT WHERE HOLDER_ID='"+userId+"'";
	}
	
	public static String InsertBuyTransaction(String accountno,String holder_id, double amount)
	{
		
		return "INSERT INTO TRANSACTION (ACCOUNT_NO, TXN_ID, TXN_DATE, HOLDER_ID, AMOUNT, buyORsell)"+
		 "VALUES('"+accountno+"',gentxnId(),DATE_FORMAT(now(), '%y-%m-%d %h:%m:%s'),'"+holder_id+"','"+amount+"','b')";
		
		 		
	}
	
	public static String InsertTransactionDetails(int txn_id,String stocksymbol,double price, int qty)
	{
		
		 
		return "INSERT INTO TRANSACTION_DETAILS (txn_id,txnd_stocksymbol,price,qty) values"
				+ "("+txn_id+",'"+stocksymbol+"',"+price+","+qty+")";
	}

	public static String getTxnId(String accountno, String userid, double amount) {
		
		
		
		return  "select txn_id from transaction where txn_date = (select max(txn_date) from transaction "+
				"where account_no = '"+accountno+"' and holder_id = '"+userid+"' and amount = '"+Math.round(amount * 100.0) / 100.0+"')";
	}
	
	
	public static String getStock_portfolio_data(String jsession)
	{
		String sql = "select stock_portfolio.stocktickername,replace(company.companyname,'_',' '),stock_portfolio.qty,stock_portfolio.price,stock_data.price from "+ 
				"session,user,account,stock_portfolio,stock_data,company "+
				"where time =  (select max(time) from session where sessionid='"+jsession+"') "+ 
				"and user.username = session.username "+ 
				"and account.holder_id = user.userid "+
				"and stock_portfolio.account_no = account.account_no "+
				"and stock_portfolio.stocktickername = stock_data.stocktickername "+
				"and stock_data.companyid = company.companyid order by stocktickername,stock_portfolio.price";
		
		
		
		
		return sql;
		
	}

	public static String getPortfolioQty(String jsession, String symbol) {
		String ssql ="select sum(qty) from session,user,account, stock_portfolio "+
				"where time =  (select max(time) from session where sessionid='"+jsession+"') "+
				"and user.username = session.username "+
				"and user.userid = account.holder_id "+
				"and account.account_no = stock_portfolio.account_no "+
				"and stocktickername = '"+symbol+"' ";
		return ssql;
	}

	public static String InsertSellTransaction(int accountno,int holder_id,double amount) {
		
		
		
		return "INSERT INTO TRANSACTION (ACCOUNT_NO, TXN_ID, TXN_DATE, HOLDER_ID, AMOUNT, buyORsell)"+
		 "VALUES('"+accountno+"',gentxnId(),DATE_FORMAT(now(), '%y-%m-%d %h:%m:%s'),'"+holder_id+"','"+amount+"','s')";
		
	}

	public static String getAccountNo(String jsession) {
		String sql = "select account_no,holder_id from account,user,session where time = (select max(time)"
				+ " from session where sessionid = '"+jsession+"') and "
						+ "session.username = user.username and user.userid = account.holder_id";
		System.out.println(sql);
		return sql;
	}

	public static String getTxnId2(String[] sellrequestDetails, String jsession) {
		String sql = "select txn_id,transaction.amount from transaction,account,user,session "
				+ " where time = (select max(time) from session where sessionid = '"+jsession+"') "
				+ "and session.username = user.username and user.userid = account.holder_id"
				+ " and account.account_no = transaction.account_no;";
		return sql;
	}

	public static String getStocksymbol(int txn_id) {
		return "SELECT txnd_stocksymbol,qty from transaction_details where txn_id = '"+txn_id+"'";
	}

	public static String getStockportfoliodata(String symbol) {
		return "select qty,price,txn_id from stock_portfolio where stocktickername ='"+symbol+"' order by price";
	}

	public static String getQ_sell(int txn_id) {
		return "select qty from transaction_details where txn_id = " + txn_id;
	}

	public static String deletePortfolioRecord(int trxn_id) {
		return "DELETE FROM stock_portfolio where txn_id = "+trxn_id;
	}

	public static String updatePortfolioRecord(int trxn_id, int i) {
		return "UPDATE stock_portfolio set qty = i where txn_id = "+trxn_id;
	}

	public static String updateAccount(int txn_id, double d, char c) {
		
		if(c == 'b')
		{
		return "update account set balance = balance - "+d+" where account_no = (select account_no from transaction where txn_id = "+txn_id+")";
		}
		else if (c == 's')
		{
			return "update account set balance = balance + "+d+" where account_no = (select account_no from transaction where txn_id = "+txn_id+")";
		}
		else
			return "ERROR";
	}

	public static String updatePortfolioOnBuy(int txn_id) {
		
		return "insert into stock_portfolio select account_no,txnd_stocksymbol,qty,price,td.txn_id from transaction_details "
				+ "td,transaction tx where td.txn_id = tx.txn_id and td.txn_id = '"+txn_id+"'";
	}

	public static String getStockList(String symbol) {
		return "select qty,price,txn_id from stock_portfolio where stocktickername = '"+symbol.toUpperCase()+"' order by price";
	}

	public static String removefromStockfolioOnSell(int txn_id) {
		return "delete from stock_portfolio where txn_id = "+txn_id;
	}

	public static String updateStockfolioOnSell(int txn_id, String qty) {
		
		return "update stock_portfolio set qty = "+qty+" where txn_id = "+txn_id;
	}

	public static String createAccountinformation(String userId, String balance) {
		// TODO Auto-generated method stub
		String sql = "insert into account (account_no, holder_id,balance) values (genAccountNo(),"+userId+",'"+balance+"')";
		System.out.println(sql);
		return sql;
	}

	public static String createUserDetailsRecord(String userId, HashMap<String, String> parameters) {
		
		String fullname = parameters.get(RegisterServlet.f_firstname) + " " + parameters.get(RegisterServlet.f_lastname);
		String address = parameters.get(RegisterServlet.f_address);
		String aptno = parameters.get(RegisterServlet.f_aptno);
		String city = parameters.get(RegisterServlet.f_city);
		String state = parameters.get(RegisterServlet.f_state);
		
		System.out.println(fullname + " " + address+ " " +aptno + " " + city+ " " + state);
		
		String sql ="insert into user_details (userid,full_name,address,apt_no,city,state) values ("+userId+",'"+fullname+"',"+
				"'"+address+
				"','"+aptno+
				"','"+city+
				"','"+state+
				"')";
		System.out.println(sql);
				
		return 	sql;
		}

	

	
	
	
}
