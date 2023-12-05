package com.human.onnana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.onnana.entity.User;
import com.human.onnana.service.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	
	
	@GetMapping("/analysis")
	public String analysisForm() {
		
		//return "project/analysis";
		return "테스트";
	}
	

	@GetMapping("/weather")
	public String weatherForm() {
		
		return "project/weather";
	}
	@GetMapping("/dust")
	public String dustForm() {
		
		return "project/dust";
	}
}