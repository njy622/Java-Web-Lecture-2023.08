package com.example.demo.Food;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Component;

	@Component
	public class FoodDao {
		@Value("${spring.datasource.url}") private String url;
		@Value("${spring.datasource.username}") private String username;
		@Value("${spring.datasource.password}") private String password;
//		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		private String username = "hmuser";
//		private String password = "hmpass";
		
		public Food getFood(int id) {
			String sql = "select * from food where id=?";   //?쓸때는 #을쓰고
			// System.out.println(url); connection 은 문제없음 확인
			Food food = null;		
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
//				System.out.println(conn); 커넥션도 문제없음 열려있음
				PreparedStatement pstmt = conn.prepareStatement(sql);
//				System.out.println(pstmt);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
//				System.out.println(rs); 연결되어있음
				while (rs.next()) {
					id = rs.getInt(1);
					String nickName = rs.getString(2);
					String foodtype = rs.getString(3);
					String content = rs.getString(4);
					String taste = rs.getString(5);
					String modTime =rs.getString(6);
					int viewCount = rs.getInt(7);
					int isDeleted = rs.getInt(8);
//					System.out.println(title); // title 데이터 못가져오고 있음
					food = new Food(id, nickName, foodtype, content, taste, 
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
			return food;
			// Oracle에서 commit을 해줘야 스프링부트에 불러올 수 있음
		}
		
		public void insertFood(Food food) {
			String sql = "insert into food(nickName, foodType, content, taste) values (?, ?, ?, ?)";
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, food.getNickName());
				pstmt.setString(2, food.getfoodType());
				pstmt.setString(3, food.getContent());
				pstmt.setString(4, food.getTaste());
				pstmt.executeUpdate();
				pstmt.close(); conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public List<Food> getFoodList(String field, String query){
			String sql = "select * from food where " + field + " like ? and isDeleted=0 "
						+" order by modTime desc";   //field는 파라미터가 아니라서 부르려면 $를 써야함
			List<Food> list = new ArrayList<>();
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ query + "%");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String nickName = rs.getString(2);
					String foodtype = rs.getString(3);
					String content = rs.getString(4);
					String taste = rs.getString(5);
					String modTime =rs.getString(6);
					int viewCount = rs.getInt(7);
					int isDeleted = rs.getInt(8);
					Food food = new Food(id, nickName, foodtype, content, taste, 
							LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")),
							viewCount, isDeleted);		
					list.add(food);
				}
				rs.close(); pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
			
		}
		public void updateFood(Food food) {
			String sql = "update blog set nickName=?, foodType=?, content=?, taste=?, modTime=current_timestamp"
					+ " where id=?";
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, food.getNickName());
				pstmt.setString(2, food.getfoodType());
				pstmt.setString(3, food.getContent());
				pstmt.setString(4, food.getTaste());
				pstmt.setInt(5, food.getId());
				pstmt.executeUpdate();
				pstmt.close(); conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		public void deleteFood(int id) {
			String sql = "update food set isDeleted=1 where id=?";
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				pstmt.close(); conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void increaseViewCount(int id) {
			String sql = "update food set viewCount = viewCount +1 where id=?";
			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				pstmt.close(); conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

