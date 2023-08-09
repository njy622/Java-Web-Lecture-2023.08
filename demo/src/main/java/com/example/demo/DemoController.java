package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
	
	// localhost:8080/demo/index	
	@GetMapping("/index")
	@ResponseBody
	public String index() {
		return "<h1>Hello World!!!</h1>";
	}

	@GetMapping("/hello")
	public String hello() {
		return "01.hello";		
	}
	
	@GetMapping("/login")  //FROM 화면 보여주기   //입력한 값을 처리해주는게 필요
	public String login() {
		return "02.login";
	}
	@PostMapping("/login")
//	@ResponseBody
	public String loginProc(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid"); //"uid"가 input tag의 name 값
		String pwd = req.getParameter("pwd");
//		return "<h1>uid="+ uid+", pwd="+pwd+"</h1>";
		
		model.addAttribute("uid",uid);
		model.addAttribute("pwd",pwd);
		
		return "03.loginResult";
	}
	@GetMapping("/getParam")
	@ResponseBody
	// GET 방식을 이용해서 주소에 정보를 입력하여 화면에 값을 나타내는 방식
	public String getParam(HttpServletRequest req) {
		//파라미터를 하려면 @ResponseBody와 HttpServletRequest req를 붙여줌
		String a_ = req.getParameter("a"); //임시변수 표시는 언더바표시해줌(변수명_)
		String b_ = req.getParameter("b");
		String op = req.getParameter("op");
		int a = Integer.parseInt(a_);   
		//문자열을 숫자로 타입변환(Integer.parseInt를 사용) (int)는 숫자는 가능하지만 문자열은 안됨
		int b = Integer.parseInt(b_);
		int result=0;
		String oper = "";
		
		switch(op) {
		case "add" : result = a + b; oper= "+"; 			break;
		case "sub" : result = a - b; oper= "-";				break;
		case "mul" : result = a * b; oper= "*";				break;
		case "div" : result = (int) (a / b); oper= "/"; 	break;
		default : result = 0;
		}
				
		return "<h1>"+ a +" "+ oper +" " + b +" " + "=" + result + "</h1>";
	}
	//calc 폼이 화면에 보이도록함
	@GetMapping("/calc")
	public String calcForm(int a, int b, String op) {  //파라미터 자리에 이렇게 입력해주면 String값을 int로 바꿔줌(스프링에서만 가능한 기능)
		return "04.calcForm";
	}
	
	@PostMapping("/calc")
	public String clacProc(int a, int b, String op, Model model) {
		int result=0;
		String oper = "";
		switch(op) {
		case "add" : result = a + b; oper= "+"; 			break;
		case "sub" : result = a - b; oper= "-";				break;
		case "mul" : result = a * b; oper= "*";				break;
		case "div" : result = (int) (a / b); oper= "/"; 	break;
		default : result = 0;
		}
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("oper", oper);
		model.addAttribute("result", result);
		return "05.calcResult";
	}
}