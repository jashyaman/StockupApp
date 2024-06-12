package miscellaneous;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConn {
	
	public static Statement createDBConn(){
		Connection con = null;
		Statement stmt = null;
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
				return null;
			}
		
			try{
				con =DriverManager.getConnection 
						  ("jdbc:mysql://localhost/stockmarket","root","root");
				stmt = con.createStatement();
				return stmt;
			}catch(SQLException e)
			{
				System.out.println("issues with connection");
			}
			return stmt;
	
	}
	
	public static void ExecuteQuery(String sql) throws SQLException
	{
		Statement stmt = createDBConn();
		stmt.execute(sql);
		stmt.close();
	}
	
	public static ResultSet ExecuteSelectQuery(String sql) throws SQLException
	{
		System.out.println(sql);
		Statement stmt = createDBConn();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

}
