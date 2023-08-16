package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller  // 콘트롤러 사용하려면 가장 먼저 추가
@RequestMapping("/jsp")  //주소로 불러오고 싶을때 => /demo/jsp
public class JspController {
	
	@GetMapping("/sample") //demo/jsp/sample
	public String sample(Model model) {
		model.addAttribute("menu", "ai");
		return "jsp/sample";
	}
	
	@GetMapping("/schedule") //demo/jsp/schedule
	public String schedule(Model model) {
		model.addAttribute("menu", "schedule");
		return "jsp/schedule";
	}
	
	@GetMapping("/elOperator")
	public String elOperator() {
		return "jsp/el1_operator";
	}

	@GetMapping("/elScope")
	public String elScope(HttpSession session, Model model) {
		session.setAttribute("sName", "sName");
		model.addAttribute("mName", "mName");
		session.setAttribute("name", "sName");
		model.addAttribute("name", "mName");
		return "jsp/el2_scope";
	}
}