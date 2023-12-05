package com.example.demo.oracle.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {
//   private BlogDao bDao = new BlogDao();
//   @Autowired private blog bDao; // Spring에서 BlogDao() 객체를  생성해서 inject(꽂아준다.)
   @Autowired private BlogService blogService;   
   // BlogService는 인터페이스 blogService : 구현객체를 스프링이 넣어줌
   //  BlogService를  BlogServiceOracleImpl에 자동으로 넣어줌
   
   @GetMapping("/list")
   public String list(@RequestParam(name="f", defaultValue="title") String field,
//		   				f라는 네임을 볼건데 없으면 타이틀로
		   			  @RequestParam(name="q", defaultValue="")String query, //query는 사용자가 입력해야하는 부분, 아무것도 안치면 
//		   			  	q라는 네임을 볼건데 없으면 ""
		   			  Model model) {
      List<Blog> list = blogService.getBlogList(field, query);
//    List<Blog> list = blogService.getBlogList("title","");
      model.addAttribute("blogList", list);
      model.addAttribute("menu", "blog");
      model.addAttribute("field", field); //list.jsp에 와서  id= query 에 value=${query} 입력해줌
      model.addAttribute("query", query);
      return "blog/list";    //blog에 있는 list를 렌더링하는 파일로 사용하겠다라는 의미
   }
   
   @GetMapping("/write")
   public String writeForm(Model model) {
      model.addAttribute("menu", "blog");
      return "blog/write";
   }
   
   @PostMapping("/write")
   public String writeProc(Blog blog) {
//   public String writeProc(String penName, String title, content) {
//	   Blog blog = new Blog(penName, title, content); //이렇게 쓸수도 있음
	   blogService.insertBlog(blog);
	   return "redirect:/blog/list";
   }
   
   @GetMapping("/detail/{bid}")
   public String detail(@PathVariable int bid, String option, Model model) {  
	   if (option == null || option.equals(""))
		   blogService.increaseViewCount(bid);	 //DNI option이 설정되어 있으면 조회수를 증가시키지 않음
	   Blog blog = blogService.getBlog(bid);
	   model.addAttribute("blog", blog);
	   model.addAttribute("menu", "blog");
	   return "blog/detail";
   }
   
   @GetMapping("/update/{bid}")
   public String updateForm(@PathVariable int bid, Model model) {
	   Blog blog = blogService.getBlog(bid);
	   model.addAttribute("blog", blog);
	   model.addAttribute("menu", "blog");  
	   // update를 하려면 기존에 가지고 있던 자료를 데이터베이스에서 가져와서 업데이트로 넘겨줘야함
	   return "blog/update";
   }
   
   @PostMapping("/update")
   public String updateProc(Blog  blog) {
// public String updateProc	(int bid, String penName, String title, content) {
//	   Blog blog = new Blog(bid, penName, title, content);  //이렇게 쓸수도 있음
	   blogService.updateBlog(blog);
	   return "redirect:/blog/detail/" + blog.getBid() + "?option=DNI";  //?option=DNI있으면 조회수 XX, 없으면 조회수 올림
   }
   
   @GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, Model model) {
		model.addAttribute("bid", bid);
		model.addAttribute("menu", "blog");
		return "blog/delete";
	}
	
	@GetMapping("/deleteConfirm/{bid}")
	public String deleteConfirm(@PathVariable int bid) {
		blogService.deleteBlog(bid);
		return "redirect:/blog/list";
	}
   
}