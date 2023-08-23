package com.example.demo.oracle.book;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.example.demo.oracle.Customer.Customer;

public class BookDao {

	private String host;
	private String user;
	private String password;
	private String database;
	private String port;
	

	public BookDao() {
		try {
			Properties props = new Properties();
			String filename = "D:/JavaWeb/demo/src/main/java/com/example/demo/oracle/book/oracle.properties";
			InputStream is = new FileInputStream(filename);
			props.load(is);
			is.close();
			
			this.host = props.getProperty("host");
			this.user = props.getProperty("user");
			this.password = props.getProperty("password");
			this.database = props.getProperty("database");
			this.port = props.getProperty("port");
			// this 안붙여도됨
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
			pstmt.setInt(1, bookId);  // 57행 ?자리에 파라미터 셋팅
			ResultSet rs = pstmt.executeQuery(); // SQL을 JDBC를 거쳐서 오라클에서 데이터를 가지고 옴
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
		public List<Book> getBookListByFileAndQuery(String field, String query){
			Connection conn = myConnection();  
			String sql = "select * from book where " +field + " like ?";
			List<Book> list = new ArrayList<>();  
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, "%" + query + "%");
			      ResultSet rs = pstmt.executeQuery();
			      while (rs.next()) {
			         Book b = new Book(rs.getInt(1),rs.getString(2),
			        		 					rs.getString(3),rs.getInt(4));
			         list.add(b);
			      }
				rs.close(); pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		
	}
		
		public void insertBook(Book b) {  //메소드의 시그니처 
			// insert라서 반환할 필요가 없으니 void 
			Connection conn = myConnection();
			String sql = "insert into book values (?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, b.getBookId());		   
				// 파라메타 자리에 Customer c이기 때문에 custId를 바로 불러올수 없어
				//c.getCustId() = custId를 가져오기 위함   
				pstmt.setString(2, b.getBookname());
				pstmt.setString(3, b.getPublisher());
				pstmt.setInt(4, b.getPrice());
				pstmt.executeUpdate();
				
				pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public void updateBook(Book b) {  //메소드의 시그니처 
			// insert라서 반환할 필요가 없으니 void 
			Connection conn = myConnection();
			String sql = "update book set bookname=?, publisher=?, price=? where bookid=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b.getBookname());
				pstmt.setString(2, b.getPublisher());
				pstmt.setInt(3, b.getPrice());
				pstmt.setInt(4, b.getBookId());		   
				pstmt.executeUpdate();
				
				pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public void deleteBook(int bookId) {  //메소드의 시그니처 
			// insert라서 반환할 필요가 없으니 void 
			Connection conn = myConnection();
			String sql = "delete from book where bookid=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookId);
				pstmt.executeUpdate();
				
				pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
