package miscellaneous;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Load_company_data {

	public static void main(String[] args) {

		String sql = "select companyid,symbol,lastsale from company_origin,company where cname = companyName and symbol is not null";
	try {
		ResultSet rs  = DatabaseConn.ExecuteSelectQuery(sql);
		
		Statement stmt = DatabaseConn.createDBConn();
		
		while(rs.next())
		{
			Stock_data stdt = new Stock_data();
			stdt.setId((int)rs.getObject(1));
			stdt.setSymbol(rs.getObject(2).toString());
			try
			{
				stdt.setPrice(Double.parseDouble(rs.getObject(3).toString()));
			}
			catch(NumberFormatException e)
			{
				stdt.setPrice(0.0);
			}
			//System.out.println(stdt.toString());
			
			System.out.println(stdt.getPrice());
			
		
			
		if(stmt.execute("insert into stock_data (companyid,stockTickerName,price) values("+stdt.getId()+",'"+
			stdt.getSymbol()+"',round("+stdt.getPrice()+",2))"))
		{
			System.out.print("YES!");
		}
		else
		{
			System.out.println("...");
		}
			
		
	
		}
		
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public static class Stock_data{
		int id;
		String symbol;
		double price;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		@Override
		public String toString() {
			return "Stock_data [id=" + id + ", symbol=" + symbol + ", price=" + price + "]";
		}
		
	}

}
