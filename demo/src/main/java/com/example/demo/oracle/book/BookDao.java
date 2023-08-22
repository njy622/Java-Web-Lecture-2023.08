package com.example.demo.oracle.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private String host;
	private String user;
	private String password;
	private String database;
	private int port;
	

	public BookDao() {
		this.host = "localhost";
		this.user = "hmuser";
		this.password = "hmpass";
		this.database = "xe";
		this.port = 1521;
	}
	
	Connection myConnection() {
		Connection conn = null;
		try {
			String connStr = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
			conn = DriverManager.getConnection(connStr, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Book getBook(int bookId) {  //Book 테이블의 타입응ㄹ 쓸것!
		Connection conn = myConnection();  // 접속정보를 하나 가지고옴
		String sql = "select * from book where bookid=?";
		Book book = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // 정보 끄집어 내기
				bookId = rs.getInt(1);
				String bookname = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				book = new Book(bookId, bookname, publisher, price);
			}
			rs.close(); pstmt.cancel(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public List<Book> getBookList(){
		Connection conn = myConnection();
		String sql = "select * from book";
		List<Book> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String bookname = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				//객체선언
				Book b = new Book(bookId, bookname, publisher, price);
				list.add(b);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	
	}
}
