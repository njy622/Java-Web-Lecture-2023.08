package com.human.onnana.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.onnana.entity.Schedule;

@Mapper
public interface ScheduleDaoOracle {

	@Select("SELECT * FROM schedule"
			+ "  WHERE \"uid\"=#{uid} and sdate >= #{startDate} and sdate <= #{endDate}"
			+ "  ORDER BY startTime")
	List<Schedule> getSchedList(String uid, String startDate, String endDate);
	
	@Insert("INSERT INTO schedule VALUES"
			+ " (DEFAULT, #{uid}, #{sdate}, #{title}, #{place, jdbcType=VARCHAR},"
			+ " #{place2, jdbcType=VARCHAR}, #{elecetricty}, #{gas}, #{smoke}, #{smoke2}, #{emoge})")
	void insert(Schedule schedule);
	
	@Select("select * from schedule where sid=#{sid}")
	Schedule getSchedule(int sid);
	
	@Update("update schedule set \"uid\"=#{uid}, sdate=#{sdate}, title=#{title},"
			+ " place=#{place, jdbcType=VARCHAR}, place2=#{place2, jdbcType=VARCHAR}, elecetricty=#{elecetricty}, "
			+ " gas=#{gas}, smoke=#{smoke, jdbcType=VARCHAR}, smoke2=#{smoke2, jdbcType=VARCHAR}, "
			+ " emoge=#{emoge, jdbcType=VARCHAR} where sid=#{sid}")
	void update(Schedule schedule);
	
	@Delete("delete from schedule where sid=#{sid}")
	void delete(int sid);
	
	
	// 모든 유저 게시글 카운트
	@Select("select count(*) from schedule")
	public int count();
	
	
	// 유저별 게시글 카운트
	@Select("select count(*) from schedule where \"uid\"=#{uid}")
	public int userCount(String uid);
}
