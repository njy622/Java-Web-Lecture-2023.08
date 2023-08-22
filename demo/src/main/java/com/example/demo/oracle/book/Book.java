package com.example.demo.oracle.book;

public class Book {			//객체를 만들때 기본적으로 쓰는게 private / getter,setter만 public 
	private int bookId;
	private String bookname;
	private String publisher;
	private int price;
	
	public Book() {	}

	public Book(int bookId, String bookname, String publisher, int price) {
		super();
		this.bookId = bookId;
		this.bookname = bookname;
		this.publisher = publisher;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookname=" + bookname + ", publisher=" + publisher + ", price=" + price
				+ "]";
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
}
