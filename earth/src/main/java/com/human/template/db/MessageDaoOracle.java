package com.human.template.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.human.template.entity.Message;

@Mapper
public interface MessageDaoOracle {

	@Select("select msg.*, mb.mname fromName from message msg"
			+ " join members mb on msg.mfrom = mb.mid"
			+ " where msg.mid=#{mid}")
	Message getMessage(int mid);
	
	@Select("select * from message order by mid desc")
	List<Message> getMessageListAll();
	
	@Select("select msg.*, mb.mname fromName, mb.imgPath fromProfile from message msg"
			+ " join members mb on msg.mfrom = mb.mid"
			+ " where msg.mto=#{mto} and msg.status=#{status} order by msg.mid desc")
	List<Message> getMessageList(String mto, int status);
	
	@Insert("insert into message values(default, #{mfrom}, #{mto}, #{content}, default, default)")
	void insertMessage(Message message);
	
	@Update("update message set content=#{content}, genTime=systimestamp where mid=#{mid}")
	void updateMessage(Message message);
	
	@Delete("delete from message where mid=#{mid}")
	void deleteMessage(int mid);
	
}
