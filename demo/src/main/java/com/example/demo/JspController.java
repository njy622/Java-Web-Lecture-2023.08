package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller  // 콘트롤러 사용하려면 가장 먼저 추가해야함 
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
	
	@GetMapping("/jstlCore")
	public String jstlCore(Model model) {
		Address addr1 = new Address(12345, "LA", "미국");
		Address addr2 = new Address(67890, "뉴욕", "미국");
		Member m1 = new Member(101, "제임스", addr1);
		Member m2 = new Member(102, "마리아", addr2);
		Member m3 = new Member(103, "홍길동", new Address(23456, "서울", "한국"));
		Member m4 = new Member(104, "김자바", new Address(23456, "고양", "한국"));
		//jsp를 보내려면.. model.
		model.addAttribute("m1", m1);
		model.addAttribute("m2", m2);
		
		Member[] member = {m1, m2};
		model.addAttribute("memberArray",member);
		
		List<Member> list = new ArrayList<>();
		list.add(m1); list.add(m2); list.add(m3); list.add(m4);
		model.addAttribute("memberList", list);
		return "jsp/jstl1_core";
	}
	
	@GetMapping("/jstlFmt")
	public String jstlFmt(Model model) {
		int price = 12345000;
		Date now = new Date();
		model.addAttribute("price", price);
		model.addAttribute("now", now);
		
		LocalDate ldNow = LocalDate.now();
		LocalTime ltNow = LocalTime.now();
		LocalDateTime ldtNow = LocalDateTime.now();
		model.addAttribute("ldNow", ldNow);
		model.addAttribute("ltNow", ltNow);
		model.addAttribute("ltNow2", ltNow.toString().substring(0,8));
		model.addAttribute("ldtNow", ldtNow);
		model.addAttribute("ldtNow2", ldtNow.toString().substring(0, 19).replace("T", " "));
		
		return "jsp/jstl2_fmt";
	}
	
	
	@GetMapping("/jstlFn")
	public String jstlFn(Model model) {
		model.addAttribute("str1", "Hello world");
		model.addAttribute("str2", "쇼핑몰의 중심 JSP Mall");
		model.addAttribute("str3", "중심");
		return "jsp/jstl3_fn";
	}
	
	//내가 만든것
	@GetMapping("/homeWork") //demo/jsp/homeWork
	public String homeWork(Model model) {
		model.addAttribute("menu", "user");
		Address addr1 = new Address(12345, "LA", "미국");
		Address addr2 = new Address(67890, "뉴욕", "미국");
		Member m1 = new Member(104, "신영화", new Address(45678, "서울", "한국"));
		Member m2 = new Member(103, "주세희", new Address(12345, "인천", "한국"));
		Member m3 = new Member(104, "남지영", new Address(23456, "고양", "한국"));
		Member m4 = new Member(103, "조세훈", new Address(34567, "서울", "한국"));
		Member m5 = new Member(101, "제임스", addr1);
		Member m6 = new Member(102, "마리아", addr2);
		Member m7 = new Member(103, "홍길동", new Address(23456, "서울", "한국"));
		Member m8 = new Member(104, "김자바", new Address(23456, "고양", "한국"));
		Member m9 = new Member(103, "정종문", new Address(56789, "파주", "한국"));
		Member m10 = new Member(104, "박하민", new Address(67890, "경기", "한국"));
		//jsp를 보내려면.. model.
		model.addAttribute("m1", m1);
		model.addAttribute("m2", m2);
		
		Member[] member = {m1, m2};
		model.addAttribute("memberArray",member);
		
		List<Member> list = new ArrayList<>();
		list.add(m1); list.add(m2); list.add(m3); list.add(m4);list.add(m5);list.add(m6);list.add(m7);list.add(m8);list.add(m9);list.add(m10);
		model.addAttribute("memberList", list);
		return "jsp/homework";
	}
	
	//선생님이 만들어주신 것
	@GetMapping("/homew")
	public String homework(Model model) {
		Address addr1 = new Address(12345, "LA", "미국");
		Address addr2 = new Address(67890, "뉴욕", "미국");
		Member m1 = new Member(101, "제임스", addr1);
		Member m2 = new Member(102, "마리아", addr2);
		Member m3 = new Member(103, "홍길동", new Address(23456, "서울", "한국"));
		Member m4 = new Member(104, "김자바", new Address(23456, "고양", "한국"));
		
		List<Member> list = new ArrayList<>();
		list.add(m1); list.add(m2); list.add(m3); list.add(m4);
		list.add(m1); list.add(m2); list.add(m3); list.add(m4);
		model.addAttribute("memberList", list);
		return "jsp/homew";
	}
}
