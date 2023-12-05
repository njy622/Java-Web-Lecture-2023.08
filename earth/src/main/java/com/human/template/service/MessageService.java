package com.human.template.service;

import java.util.List;

import com.human.template.entity.Message;

public interface MessageService {

	Message getMessage(int mid);
	
	List<Message> getMessageListAll();
	
	List<Message> getMessageList(String mto, int status);
	
	void insertMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(int mid);
	
}
