package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller  // 콘트롤러 사용하려면 가장 먼저 추가해야함 
@RequestMapping("/jsp")  //주소로 불러오고 싶을때 => /demo/jsp
public class JspController {
	
	@GetMapping("/sample") //demo/jsp/sample
	public String sample(Model model) {
		//session.addAttribute("menu", "ai"); /모든 페이지에서 menu를 쓸수 있음
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
	
	//2023.08.17 
	@GetMapping("/elCollections")
	public String elCollections(Model model) {  // jsp 파일을 사용할거예용
		//String[] fruits = {"참외", "수박", "복숭아", "토마토"};
		String[] fruits = "참외, 수박,복숭아, 토마토".split(",");
		model.addAttribute("fruitArray", fruits);
		//fruitArray라는 이름을 el_collection.jsp 파일에서 써야 데이터를 받을 수 있음
		
		List<String> sports = new ArrayList<>(); 
		//웹페이지를 만들면서 가장 많이 사용하는 List(DB와 함께) <>안에 내용은 리스트에서 쓸 DB타입
		sports.add("축구");sports.add("야구");sports.add("배구");
		model.addAttribute("sportsList", sports);
		
		
		Map<String, String> map = new HashMap<>();
		map.put("key", "el"); map.put("value", "jstl");
		model.addAttribute("map", map);
		return "jsp/el3_collections";  //해당 jsp 파일로 쓰겠습니다(보내겠습니다)
	}
	
	
	@GetMapping("/elPojo") //play old java object
	public String elPojo(Model model) {
		Address addr1 = new Address(12345, "LA", "미국");
		Address addr2 = new Address(67890, "뉴욕", "미국");
		Member m1 = new Member(101, "제임스", addr1);
		Member m2 = new Member(102, "마리아", addr2);
		//jsp를 보내려면.. model.
		model.addAttribute("m1", m1);
		model.addAttribute("m2", m2);
		
		Member[] member = {m1, m2};
		model.addAttribute("memberArray",member);
		
		List<Member> list = new ArrayList<>();
		list.add(m1); list.add(m2);
		model.addAttribute("memberList", list);
		return "jsp/el4_pojo";
	}
	
}
