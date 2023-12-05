package com.human.template.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.human.template.entity.Message;
import com.human.template.entity.Notification;
import com.human.template.service.MessageService;
import com.human.template.service.NotificationService;

@Controller
public class HomeController {
	@Autowired NotificationService notiService;
	@Autowired MessageService msgService;
	
	@GetMapping(value= {"/", "/index"})
	public String index(HttpSession session) {
		String sessMid, sessMname, sessProfile;
		if (session.getAttribute("sessMid") == null) {
			sessMid = "james";
			sessMname = "James Dean";
			sessProfile = "james.jpg";
			session.setAttribute("sessMid", sessMid);
			session.setAttribute("sessMname", sessMname);
			session.setAttribute("sessProfile", sessProfile);
		}
		sessMid = (String) session.getAttribute("sessMid");
		List<Notification> notiList = notiService.getNotificationList(sessMid, 0);
		List<Message> msgList = msgService.getMessageList(sessMid, 0);
		int notiNum = notiList.size();
		int msgNum = msgList.size();
		session.setAttribute("notiNum", notiNum);
		session.setAttribute("notiList", notiList);
		session.setAttribute("msgNum", msgNum);
		session.setAttribute("msgList", msgList);
		return "index";
	}

}
