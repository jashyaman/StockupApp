package com.prac.broker;

public class StockData {

	private String Symbol;
	private String StockName;
	private String Price;
	private String MarketCap;
	private int year;
	private String Sector;
	private String Industry;
	private String Summary_Quote;
	
	
	public StockData(String symbol, String stockName, String price, String marketCap, int year, String sector,
			String industry, String summary_Quote) {
		super();
		Symbol = symbol;
		StockName = stockName;
		Price = price;
		MarketCap = marketCap;
		this.year = year;
		Sector = sector;
		Industry = industry;
		Summary_Quote = summary_Quote;
	}
	
	
	public StockData() {
		// TODO Auto-generated constructor stub
	}


	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getMarketCap() {
		return MarketCap;
	}
	public void setMarketCap(String marketCap) {
		MarketCap = marketCap;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSector() {
		return Sector;
	}
	public void setSector(String sector) {
		Sector = sector;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
	}
	public String getSummary_Quote() {
		return Summary_Quote;
	}
	public void setSummary_Quote(String summary_Quote) {
		Summary_Quote = summary_Quote;
	}
	
	
}
