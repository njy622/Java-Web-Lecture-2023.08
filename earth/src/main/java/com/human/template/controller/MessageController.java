package com.human.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.template.entity.Message;
import com.human.template.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	private String category = "message";
	@Autowired private MessageService messageService;
	
	@GetMapping(value = {"/list/{mid}", "/list"})
	public String list(@PathVariable(required=false) String mid, Model model) {
		model.addAttribute("menu", "list");
		model.addAttribute("category", category);
		List<Message> list = null;
		if (mid == null)
			list = messageService.getMessageListAll();
		else
			list = messageService.getMessageList(mid, 0);
		model.addAttribute("messageList", list);
		return "message/list";
	}
	
}
