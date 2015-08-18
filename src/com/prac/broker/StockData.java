package com.prac.broker;

public class StockData {

	private int Index;
	private String Symbol;
	private String StockName;
	private double Price;
	private String MarketCap;
	private int year;
	private String Sector;
	private String Industry;
	private String Summary_Quote;
	
	
	public StockData(int index,String symbol, String stockName, double price2, String marketCap, int year, String sector,
			String industry, String summary_Quote) {
		
		super();
		try{
		Index = index;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Index " + index);
		}
		try
		{
		Symbol = symbol;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Symbol " + symbol);
		}
		try
		{
		StockName = stockName;
		}
		catch(NumberFormatException e)
		{
			System.out.println("StockName " + stockName);
		}
		try
		{
		Price = price2;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Price " + price2);
		}
		try
		{
		MarketCap = marketCap;
		}
		catch(NumberFormatException e)
		{
			System.out.println("MarketCap " + marketCap);
		}
		try 
		{
		this.year = year;
		}
		catch(NumberFormatException e)
		{
			System.out.println("year " + year);
		}
		try 
		{
		Sector = sector;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Sector " + sector);
		}
		try 
		{
			
		
		Industry = industry;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Industry " + industry);
		}
		try
		{
		Summary_Quote = summary_Quote;
		}
		catch(NumberFormatException e)
		{
			System.out.println("Summary_Quote " + summary_Quote);
		}
	}
	
	
	public StockData() {
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
	public double getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		try{
		Price = Double.parseDouble(price);
		}
		catch(NumberFormatException e)
		{
			Price = 0.0;
		}
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


	public int getIndex() {
		return Index;
	}


	public void setIndex(int index) {
		Index = index;
	}
	
	
}
