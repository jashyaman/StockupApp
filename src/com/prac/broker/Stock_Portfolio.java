package com.prac.broker;

public class Stock_Portfolio {


	String symbol;
	String stockname;
	int qty_in_hand;
	double price_bought;
	double current_price;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public int getQty_in_hand() {
		return qty_in_hand;
	}
	public void setQty_in_hand(int qty_in_hand) {
		this.qty_in_hand = qty_in_hand;
	}
	public double getPrice_bought() {
		return price_bought;
	}
	public void setPrice_bought(double price_bought) {
		this.price_bought = price_bought;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}
	
	
}
