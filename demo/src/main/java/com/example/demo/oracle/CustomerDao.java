package com.example.demo.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CustomerDao {
	private String host;
	private String user;
	private String password;
	private String database;
	private int port;
	
	public CustomerDao() {
		// 아래의 코드는 임시용 (Spring 에서는 이런식으로..) 
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
		
		return null;
	}
}
