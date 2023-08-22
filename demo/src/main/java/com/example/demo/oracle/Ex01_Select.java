package com.example.demo.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 파라메터가 없는 SQL을 사용하여, Madang/customer 테이블 조회 
 * (모든 데이터베이스에서 코드동일..MySQL.. 등
 * / (jdbc:oracle:thin:@localhost:1521:xe)주소만 바뀔뿐...)
 */
public class Ex01_Select {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(
					 "jdbc:oracle:thin:@localhost:1521:xe",
					 "hmuser", "hmpass");
			stmt = conn.createStatement();  
			String sql = "select * from customer";
			
			// sql 실행(select)  => 결과는 ResultSet으로 받음(rs에 들어옴)
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {		// 리턴값이 남아있는 동안에  while문 안의 내용을 실행해라(한줄단위로 들어옴)
				int custId = rs.getInt(1);   // 1자리에 컬럼명을 써줘도됨(custid)
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String phone = rs.getString(4);
				System.out.printf("%d, %s, %s, %s%n", custId, name, addr, phone);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
