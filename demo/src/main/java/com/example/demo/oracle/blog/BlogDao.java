package com.example.demo.oracle.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class BlogDao {
//	@Value("${spring.datasource.url}") private String url;
//	@Value("${spring.datasource.username}") private String username;
//	@Value("${spring.datasource.password}") private String password;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "hmuser";
	private String password = "hmpass";
	
	public Blog getBlog(int bid) {
		String sql = "select * from blog where bid=?"; 
		// System.out.println(url); connection 은 문제없음 확인
		Blog blog = null;		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
//			System.out.println(conn); 커넥션도 문제없음 열려있음
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			System.out.println(pstmt);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
//			System.out.println(rs); 연결되어있음
			while (rs.next()) {
				bid = rs.getInt(1);
				String penName = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				String modTime =rs.getString(5);
				int viewCount = rs.getInt(6);
				int isDeleted = rs.getInt(7);
//				System.out.println(title); // title 데이터 못가져오고 있음
				blog = new Blog(bid, penName, title, content, 
								LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")),
								viewCount, isDeleted);	
				/* modTime의 데이터 Oracle과 LocalDateTime에서 형식이 다름
				   Oracle 데이터 : 2023-08-23 14:18:41.904000000
				   LocalDateTime : 2023-08-23T14:18:41
				   modTime.substring(0,19).replace(" ", "T")	이렇게 써서 변환 */
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return blog;
		// Oracle에서 commit을 해줘야 스프링부트에 불러올 수 있음
	}
}
