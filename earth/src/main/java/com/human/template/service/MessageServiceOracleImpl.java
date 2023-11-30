package com.human.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.template.db.MessageDaoOracle;
import com.human.template.entity.Message;

@Service
public class MessageServiceOracleImpl implements MessageService {
	@Autowired private MessageDaoOracle messageDao;
	
	@Override
	public Message getMessage(int mid) {
		Message message = messageDao.getMessage(mid);
		return message;
	}

	@Override
	public List<Message> getMessageListAll() {
		List<Message> list = messageDao.getMessageListAll();
		return list;
	}

	@Override
	public List<Message> getMessageList(String mto, int status) {
		List<Message> list = messageDao.getMessageList(mto, status);
		return list;
	}

	@Override
	public void insertMessage(Message message) {
		messageDao.insertMessage(message);
	}

	@Override
	public void updateMessage(Message message) {
		messageDao.updateMessage(message);
	}

	@Override
	public void deleteMessage(int mid) {
		messageDao.deleteMessage(mid);
	}

}
