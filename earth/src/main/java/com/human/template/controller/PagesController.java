package com.human.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {
	private String category = "pages";
	
	@GetMapping("/faq")
	public String faq(Model model) {
		model.addAttribute("menu", "faq");
		model.addAttribute("category", category);
		return "pages/faq";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("menu", "contact");
		model.addAttribute("category", category);
		return "pages/contact";
	}

	@GetMapping("/blank")
	public String blank(Model model) {
		model.addAttribute("menu", "blank");
		model.addAttribute("category", category);
		return "pages/blank";
	}

}
