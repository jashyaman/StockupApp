package DAO;

public class GenerateQuery {

	public static String getUsernameFromJSessionId(String jsessionid)
	{
		return "SELECT USERNAME FROM SESSION WHERE SESSIONID ='"+jsessionid+"'";
	}
	
	public static String getUserIDFromUsername(String username)
	{
		return "SELECT USERID FROM USER WHERE USERNAME ='"+username+"'";
	}
	
	public static String getAccountdetailsfromUserId(String userId)
	{
		System.out.println("SELECT ACCOUNT_NO,BALANCE FROM ACCOUNT WHERE HOLDER_ID='"+userId+"'");
		return "SELECT ACCOUNT_NO,BALANCE FROM ACCOUNT WHERE HOLDER_ID='"+userId+"'";
	}
	
	public static String InsertBuyTransaction(String accountno,String holder_id, double amount)
	{
		
		return "INSERT INTO TRANSACTION (ACCOUNT_NO, TXN_ID, TXN_DATE, HOLDER_ID, AMOUNT)"+
		 "VALUES('"+accountno+"',gentxnId(),DATE_FORMAT(now(), '%y-%m-%d %h:%m:%s'),'"+holder_id+"','"+amount+"')";
		
		 		
	}
	
	public static String InsertTransactionDetails(int txn_id,String stocksymbol,double price, int qty)
	{
		
		System.out.println("INSERT INTO TRANSACTION_DETAILS (txn_id,txnd_stocksymbol,price,qty) values"
				+ "("+txn_id+",'"+stocksymbol+"',"+price+","+qty+")");
		 
		return "INSERT INTO TRANSACTION_DETAILS (txn_id,txnd_stocksymbol,price,qty) values"
				+ "("+txn_id+",'"+stocksymbol+"',"+price+","+qty+")";
	}

	public static String getTxnId(String accountno, String userid, double amount) {
		
		System.out.println("select txn_id from transaction where txn_date = (select max(txn_date) from transaction "+
				"where account_no = '"+accountno+"' and holder_id = '"+userid+"' and amount = '"+Math.round(amount * 100.0) / 100.0+"')");
		
		
		return  "select txn_id from transaction where txn_date = (select max(txn_date) from transaction "+
				"where account_no = '"+accountno+"' and holder_id = '"+userid+"' and amount = '"+Math.round(amount * 100.0) / 100.0+"')";
	}
	
	
	public static String getStock_portfolio_data(String jsession)
	{
		String sql = "select stock_portfolio.stocktickername,replace(company.companyname,'_',' '),stock_portfolio.qty,stock_portfolio.price,stock_data.price from "+ 
				"session,user,account,stock_portfolio,stock_data,company "+
				"where time =  (select max(time) from session where sessionid='65A9E4ED5B9A904B9EA2E070E7701246') "+ 
				"and user.username = session.username "+ 
				"and account.holder_id = user.userid "+
				"and stock_portfolio.account_no = account.account_no "+
				"and stock_portfolio.stocktickername = stock_data.stocktickername "+
				"and stock_data.companyid = company.companyid";
		
		return sql;
		
	}
	
	
}
