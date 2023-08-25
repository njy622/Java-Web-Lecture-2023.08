package com.example.demo.Food;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FoodDaoOracle {

	@Select("select * from food where id=#{id}")
	Food getFood(int id);
	
	
	@Select("select * from blog where ${field} like #{query} and isDeleted=0"
			+ " order by modTime desc")
	List<Food> getFoodList(String field, String query);
	
	
	@Insert("insert into food(nickName, foodType, content, taste)"
			+ " values (#{nickName}, #{foodType}, #{content, jdbcType=VARCHAR}, #{taste}")
	void insertFood(Food food);
	
	@Update("update food set nickName=#{nickName}, foodType=#{foodType},"
			+ " content=#{content, jdbcType=VARCHAR}, taste=#{taste} "
			+ " modTime=currnt_timestamp where id=#{id}")
	void updateFood(Food food);
	
	@Update("update food set isDeleted=1 where id=#{id}")
	void deleteFood(int id);
	
	@Update("update food set viewCount = viewCount +1 where id=#{id}")
	void increaseViewCount(int id);
	
}
