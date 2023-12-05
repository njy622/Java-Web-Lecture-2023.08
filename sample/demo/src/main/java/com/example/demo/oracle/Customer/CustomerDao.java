package com.example.demo.oracle.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
	private String host;
	private String user;
	private String password;
	private String database;
	private int port;
	
	public CustomerDao() {
		// 아래의 코드는 임시용 (Spring 에서는 앞으로는 안쓸거임) 
		this.host = "localhost";
		this.user = "hmuser";
		this.password = "hmpass";
		this.database = "xe";
		this.port = 1521;
		// 접속과 관련한 정보를 파일에 저장을 해서 보관하고 , 이곳에서 읽어서 셋팅함.
		// → JavaEE + Maven 일때
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
	
	public Customer getCustomer(int custId) {  // Customer 타입으로 받을 것이다.
		Connection conn = myConnection();  //접속정보를 하나 가지고옴
		String sql = "select * from customer where custid=?";
		Customer customer = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custId);        //파라메타 셋팅
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {    // 정보 끄집어내기
				custId = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String phone = rs.getString(4);
				customer = new Customer(custId, name, addr, phone);   //Customer 클래스에서 메소드를 이렇게 생성했기 때문
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public List<Customer> getCustomerList(){
		Connection conn = myConnection();  
		String sql = "select * from customer";
		List<Customer> list = new ArrayList<>();  
		try {
			Statement stmt = conn.createStatement();  
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int custId = rs.getInt(1);  
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String phone = rs.getString(4);
				//객체선언
				Customer c = new Customer(custId, name, addr, phone);
				list.add(c);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Customer> getCustomerListByFileAndQuery(String field, String query){
		Connection conn = myConnection();  
		String sql = "select * from customer where " +field + " like ?";
		List<Customer> list = new ArrayList<>();  
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, "%" + query + "%");
		      ResultSet rs = pstmt.executeQuery();
		      while (rs.next()) {
		         Customer c = new Customer(rs.getInt(1),rs.getString(2),
		        		 					rs.getString(3),rs.getString(4));
		         list.add(c);
		      }
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertCustomer(Customer c) {  //메소드의 시그니처 
		// insert라서 반환할 필요가 없으니 void 
		Connection conn = myConnection();
		String sql = "insert into customer values (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCustId());		   
			// 파라메타 자리에 Customer c이기 때문에 custId를 바로 불러올수 없어
			//c.getCustId() = custId를 가져오기 위함   
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getAddr());
			pstmt.setString(4, c.getPhone());
			pstmt.executeUpdate();
			
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateCustomer(Customer c) {  //메소드의 시그니처 
		// insert라서 반환할 필요가 없으니 void 
		Connection conn = myConnection();
		String sql = "update customer set name=?, address=?, phone=? where custid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getAddr());
			pstmt.setString(3, c.getPhone());
			pstmt.setInt(4, c.getCustId());		
			// 파라메타 자리에 Customer c이기 때문에 custId를 바로 불러올수 없어
			//c.getCustId() = custId를 가져오기 위함   
			// ★ 오류나면 대부분 위의 인덱스 순서가 잘못 되었기때문 카피하고 늘 확인하자
			pstmt.executeUpdate();
			
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteCustomer(int custId) {  //메소드의 시그니처 
		// insert라서 반환할 필요가 없으니 void 
		Connection conn = myConnection();
		String sql = "delete from customer where custid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custId);
			pstmt.executeUpdate();
			
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomerListByAddr(String addr) {  // Customer 타입으로 받을 것이다.
		Connection conn = myConnection();  //접속정보를 하나 가지고옴
		String sql = "select * from customer where address like ?";
		List<Customer> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + addr + "%");        //파라메타 셋팅
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {    // 정보 끄집어내기
				Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(c);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Customer> getCustomerListByName(String name) {  // Customer 타입으로 받을 것이다.
		Connection conn = myConnection();  //접속정보를 하나 가지고옴
		String sql = "select * from customer where name like ?";
		List<Customer> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" +name + "%");        //파라메타 셋팅
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {    // 정보 끄집어내기
				Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(c);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
