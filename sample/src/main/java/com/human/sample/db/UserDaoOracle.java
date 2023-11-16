package com.human.sample.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.human.sample.entity.User;

@Mapper
public interface UserDaoOracle {
	
	@Select("select * from users where \"uid\"=#{uid}")  // SQL구문으로 uid를 받아서
	public User getUser(String uid);			// User 객체에 넣어줌
	
	// #{uid}   --> user.getUid() 메소드를 부르도록 정의된 Mybatis에서의 기호
	@Insert("insert into users values (#{uid}, #{pwd}, #{uname},#{email}, default, default)")
	public void insertUser(User user);		// insert는 반환타입이 없음으로 void를 이용
	
	
}
