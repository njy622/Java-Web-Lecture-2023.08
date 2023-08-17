package com.example.demo;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


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
	//화면에 출력하는 메소드
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
	//시뮬레이션 돌리는 메소드(라우팅을 위한) /에러메세지를 콘솔창에 뜨게함
	@PostMapping("/register2")
	public String register2Proc(MultipartHttpServletRequest req) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		String pwd2 = req.getParameter("pwd2");
		String uname = req.getParameter("uname");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		MultipartFile filePart = req.getFile("profile");
		if(filePart.getContentType().contains("image")) {  //사진을 보냈음 (MIME type : imge.png, imge.jpg)
			String filename = filePart.getOriginalFilename();
			String profilePath = uploadDir + filename;
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//uid 동일한 데이터가 존재하면
		if (!checkUid(uid)) {
			// alert message 내보내고,
			System.out.println("uid: " + uid + "이/가 이미 존재합니다.");
			// 회원가입 화면으로 되돌려 보내야 함
			return "redirect:/bs/register";
			
		} else { //pwd 값이 없거나, pwd2의 값과 동일하면
			if (pwd !=null && pwd.equals(pwd2)) {
				// 올바른 회원가입
				System.out.println(uname + "님 환영합니다.");
				// 로그인 화면으로 보내줌
				return "redirect:/bs/login";
			} else {
				// 패스워드 에러에 대한 경고 메세지
				System.out.println("패스워드를 올바르게 입력하세요.");
				// 회원가입 화면으로 되돌려 보내야 함
				return "redirect:/bs/register";
			}
		}
	}
	//에러메세지를 팝업창에 뜨도록
	@PostMapping("/register3")
	public String register3Proc(MultipartHttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		String pwd2 = req.getParameter("pwd2");
		String uname = req.getParameter("uname");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		MultipartFile filePart = req.getFile("profile");
		if(filePart.getContentType().contains("image")) {  //사진을 보냈음 (MIME type : imge.png, imge.jpg)
			String filename = filePart.getOriginalFilename();
			String profilePath = uploadDir + filename;
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//uid 동일한 데이터가 존재하면
		if (!checkUid(uid)) {
			// alert message 내보내고,
			//System.out.println("uid: " + uid + "이/가 이미 존재합니다.");
			model.addAttribute("msg", "uid: " + uid + "이/가 이미 존재합니다.");
			model.addAttribute("url", "/demo/bs/register");
			// 회원가입 화면으로 되돌려 보내야 함
			return "bs/alertMsg";
			
		} else { //pwd 값이 없거나, pwd2의 값과 동일하면
			if (pwd !=null && pwd.equals(pwd2)) {
				// 올바른 회원가입
				//System.out.println(uname + "님 환영합니다.");
				model.addAttribute("msg", uname +"님 환영합니다.");
				model.addAttribute("url", "/demo/bs/login");
				// 로그인 화면으로 보내줌
				return "bs/alertMsg";
			} else {
				// 패스워드 에러에 대한 경고 메세지
				//System.out.println("패스워드를 올바르게 입력하세요.");
				model.addAttribute("msg", "패스워드를 올바르게 입력하세요");
				model.addAttribute("url", "/demo/bs/register");
				// 회원가입 화면으로 되돌려 보내야 함
				return "bs/alertMsg";
			}
		}
	}
	// uid가 DB에 존재하면 false, 없으면 true 
	boolean checkUid(String uid) {
		//if (Math.random() > 0.5) 
		if (Math.random() < 0.9)  //중복될 가능성 90%로 넓힘
			return true;
		return false;
	}
	
}
