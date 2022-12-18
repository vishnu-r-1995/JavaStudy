package com.stream;

import java.math.BigDecimal;

public class CommonStock {
	private String symbol;
	private String name;
	private BigDecimal previousClosingPrice = BigDecimal.ZERO;
	private BigDecimal currentPrice = BigDecimal.ZERO;
	private double workingCapitalRatio = 0d;
	
	public CommonStock(String symbol, String name, BigDecimal previousClosingPrice, BigDecimal currentPrice,
			double workingCapitalRatio) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.previousClosingPrice = previousClosingPrice;
		this.currentPrice = currentPrice;
		this.workingCapitalRatio = workingCapitalRatio;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPreviousClosingPrice() {
		return previousClosingPrice;
	}
	public void setPreviousClosingPrice(BigDecimal previousClosingPrice) {
		this.previousClosingPrice = previousClosingPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public double getWorkingCapitalRatio() {
		return workingCapitalRatio;
	}
	public void setWorkingCapitalRatio(double workingCapitalRatio) {
		this.workingCapitalRatio = workingCapitalRatio;
	}	
}
