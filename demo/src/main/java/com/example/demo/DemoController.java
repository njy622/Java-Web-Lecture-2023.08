package com.example.demo;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DemoController {
	String a_=null, b_ =null, op=null;
	int a= 0, b= 0, result=0;
	
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
	public String calcForm() {  //파라미터 자리에 이렇게 입력해주면 String값을 int로 바꿔줌(스프링에서만 가능한 기능)
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
	
	@GetMapping("/write")
	public String writeForm() {
		return "06.writeForm";
	}
	// write 받는 메소드 작성!
	@PostMapping("/write")
	public String writeProc(HttpServletRequest req, Model model) {
		String title = req.getParameter("title");
		String[] languages = req.getParameterValues("language");    //체크박스는 멀티로 선택가능함, 그래서 배열로표기
		String content = req.getParameter("content");		
		// 읽은 데이터를 가지고 DB에다가 저장하기 위해 코드를 만들자
		
		String joinLanguages = (languages ==null) ? "" :String.join(", ", languages);
		Board board = new Board(title, joinLanguages, content.replace("\n", "<br>"));
//		System.out.println(board);
//		return "06.writeForm";
		model.addAttribute("board", board);   //파라미터 자리에 Model model 추가
		return "07.writeResult";
		
	}
	
	@GetMapping("/calculator")
	public String calculator() {
		return "08.calculator";
	}
	// localhost:8080/demo/calculator
	@PostMapping("/calculator")
	public String calculatorProc(HttpServletRequest req, HttpSession session, Model model) {
		String num_ = req.getParameter("num");
		String op_ = req.getParameter("op");
		Object obj = session.getAttribute("stack");
		Stack<String> stack = (obj == null) ? new Stack<>() : (Stack) obj;
		
		if (num_ != null) {                  //num 숫자를 눌렀을때
			if (stack.isEmpty()) {			//스텍영역이 비었으면
				stack.push(num_);			//숫자를 넣어주고
			} else {
				String element = stack.pop();		//스텍영역에 데이터가 있으면 스텍에서 element로 빼줌
				
				if (element.equals("/") || element.equals("*")		//element가 연산자이면
					|| element.equals("-") || element.equals("+")) {
					stack.push(element);							// 스텍에 연산자를 넣고
					stack.push(num_);								// 숫자를 넣어줌
				} else {
					num_ = element + num_;				//연산자가 아니면 숫자를 계속 입력하여
					stack.push(num_);					//스텍에 넣어줌
				}
			}
			session.setAttribute("stack", stack);		// 스텍에 저장된 값을 "stack"에 저장
			model.addAttribute("eval", getEval(stack));		//스텍에 저장된 값을 "eval"에 표시
		} else if (op_ != null && !op_.equals("=")) {   //숫자가 아닌 연산자가 null이 아니거나 =이 아닐때,
			if (op_.equals("C")) {			// 연산자가 C일때
				if (stack.isEmpty())		// 스텍이 비어있으면
					;						// 아무것도 표시 안함
				else {							// 연산자가 비어있지 않다면
					String s = stack.pop();		// 스텍에 있는 값을 변수 s에 빼놓고
					if (s.length() > 1) {		// s의 길이가 1보다크면
						s = s.substring(0, s.length()-1);	// s는 첫글자부터 s의 끝자리까지 s에 담는다
						stack.push(s);		// s의 값을 스텍에 넣어둠
					}
				}
			} else 								// 연산자가 C가 아닐때
				stack.push(op_);				// 스텍에 연산자를 넣어둠
			session.setAttribute("stack", stack);		// 스텍에 저장된 값을 "stack"에 저장
			model.addAttribute("eval", getEval(stack)); //스텍에 저장된 값을 "eval"에 표시
		} else {										//숫자가 아닌 연산자가 null이고 =이면,
			int result = 0;
			int num2 = Integer.parseInt(stack.pop());	//스텍에 저장한 값을 꺼내서, 숫자로 변환하여 num2에 넣어둠
			String op = stack.pop();					//스텍에서 꺼낸 값을 op에 넣어둠
			int num1 = Integer.parseInt(stack.pop());	// 스텍에 저장한 값을 꺼내서, 숫자로 변환하여 num1에 넣어둠
			switch(op) {								// op 연산자의 경우에 따라 연산
			case "+": result = num1 + num2; break;
			case "-": result = num1 - num2; break;
			case "*": result = num1 * num2; break;
			case "/": result = (int) (num1 / num2); break;
			default: result = 0;
			}
			session.removeAttribute("stack");
			model.addAttribute("eval", result);
		}
		return "08.calculator";
	}
	
	String getEval(Stack<String> stack) {
		String eval = "";
		for (String s: stack)
			eval += s + " ";
		return eval;
	}
}

//	Object obj = session.getAttribute("stack");
//	Stack<Object> stack = (obj == null) ? new Stack<>() : (Stack) obj;

// 두자리수는 아직 안됨 찾아보자	

/*@PostMapping("/calculator")
	public String calculatorProc(HttpServletRequest req, HttpSession session, Model model) {
		String num_ = req.getParameter("num");
		String op_ = req.getParameter("op");
		String eval = req.getParameter("eval");
		try {
			
		if (eval == null)
			eval = "";
		
		if (num_ != null) {
				eval += num_;
				if (a_ == null) {
					a_ = num_;
					a = Integer.parseInt(a_);
				} else if (b_ == null) {
					b_ = num_;
					b = Integer.parseInt(b_);
				}
				model.addAttribute("eval", eval);
			} else if (op_ != null) {
				if (op_.equals("C")) {
					eval = "";
					a_ = null; b_ = null; op = null;
					a = 0; b = 0;
					model.addAttribute("eval", eval);
				} else if (op_.equals("=")) {
					switch(op) {
					case "+": result = a + b; break;
					case "-": result = a - b; break;
					case "*": result = a * b; break;
					case "/": result = (int) (a / b); break;
					default: result = 0;
					}
					a_ = null; b_ = null; op = null;
					a = 0; b = 0;
					model.addAttribute("eval", result);
				} else {
					eval += " " + op_ + " ";
					op = op_;
					model.addAttribute("eval", eval);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			return "08.calculator";
		
	}*/