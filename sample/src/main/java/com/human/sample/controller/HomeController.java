package com.human.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/home")	// routing 정보, localhost:8090/sample/home
	public String home(Model model) {
		model.addAttribute("menu", "home");
		return "home";		// webapp/WEB-INF/view/home.jsp 를 렌더링해서 보여줄 것
	}

}
