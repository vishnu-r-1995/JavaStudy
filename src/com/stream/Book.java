package com.stream;

import java.math.BigDecimal;

public class Book {
	private String name;
	private String author;
	private BigDecimal price;
	private String genre;
	
	public Book(String name, String author, BigDecimal price, String genre) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() 
	{
		return "Book: name =" + name + ", author = " + author + ", price = " + price.doubleValue() + ", genre = " + genre;
	}
	
}
