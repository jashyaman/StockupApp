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
		return "SELECT ACCOUNT_NO,BALANCE FROM ACCOUNT WHERE HOLDER_ID='"+userId+"'";
	}
	
	public static String InsertBuyTransaction(String accountno,String holder_id, double amount)
	{
		
		return "INSERT INTO TRANSACTION (ACCOUNT_NO, TXN_ID, TXN_DATE, HOLDER_ID, AMOUNT)"+
		 "VALUES('"+accountno+"',gentxnId(),DATE_FORMAT(now(), '%y-%m-%d'),'"+holder_id+"','"+amount+"')";
		
		 		
	}
	
}
