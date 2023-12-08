package com.example.demo.oracle.blog;

import java.util.List;
//BlogDao 대신 이클래스로..
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BlogDaoOracle {
	
	@Select("select * from blog where bid=#{bid}")   
	// #{bid}하면 Blog getBlog(int bid)에서 불러온 bid값이 "...where bid=여기"여기 ?쏙! 들어감	
	Blog getBlog(int bid);		//인터페이스라서 public을 안붙여줘도 됨

	
	
	//"select * from blog where " + field + " like ? and isDeleted=0 "
	//+" order by modTime desc"
	//${field} => 스프링의 값으로 가져오기 위함 (SQL의 파라미터가 아닌 SQL의 스트링으로 가져와야하기때문에 
	
	@Select("select * from blog where ${field} like #{query} and isDeleted=0"
			+ " order by modTime desc")
	List<Blog> getBlogList(String field, String query);
	
	
	
	@Insert("insert into blog(penName, title, content)"
			+ " values (#{penName}, #{title}, #{content, jdbcType=VARCHAR})")
	void insertBlog(Blog blog);
	
	
	@Update("update blog set penName=#{penName}, title=#{title}, content=#{content, jdbcType=VARCHAR},"
			+ " modTime=current_timestamp where bid=#{bid}")
	void updateBlog(Blog blog);
	
	
	@Update("update blog set isDeleted=1 where bid=#{bid}")
	void deleteBlog(int bid);
	
	@Update("update blog set viewCount = viewCount +1 where bid=#{bid}")
	void increaseViewCount(int bid);
}
