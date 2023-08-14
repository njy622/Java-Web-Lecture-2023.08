package com.example.demo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bs")  // 주소 설정
public class BootstrapController {
	
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	
	@GetMapping("/login")  //주소 설정
	public String login() {
		return "bs/login";  // ResponseBody가 붙지 않으면 jsp 파일이 그대로 붙음(?)
	}
	//http://localhost:8080/demo/bs/login
	@PostMapping("/login1")
	@ResponseBody // 이 내용은 리턴으로 화면에 그대로 출력됨
	public String login1(HttpServletRequest req) {
		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		String remember = req.getParameter("remember");   //check면 "on", 아니면 "null"
		
		return "<h2>email: " + email + ", pswd: " + pswd + ", remember: " + remember + "</h2>";
	}
	
	@PostMapping("/login2")
	@ResponseBody // 이 내용은 리턴으로 화면에 그대로 출력됨
	public String login2(String email, String pswd, String remember) {
		return "<h2>email: " + email + ", pswd: " + pswd + ", remember: " + remember + "</h2>";
	}
	
	//http://localhost:8080/demo/bs/register 
	@GetMapping("/register")  //register 면 아래와같이..
	public String registerForm() {
		return "bs/register";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public String registerProc(MultipartHttpServletRequest req) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		String pwd2 = req.getParameter("pwd2");
		String uname = req.getParameter("uname");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String html = String.format("<h2>%s, %s, %s, %s, %s, %s</h2>", uid, pwd, pwd2, uname, email, gender);
		
		// 파일 읽기 
		MultipartFile filePart = req.getFile("profile");
		if(filePart.getContentType().contains("image")) {  //사진을 보냈음 (MIME type : imge.png, imge.jpg)
			String filename = filePart.getOriginalFilename();
			html += "<h2>" + filename + "</h2>";
			String profilePath = uploadDir + filename;
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return html;
	}
	
}
