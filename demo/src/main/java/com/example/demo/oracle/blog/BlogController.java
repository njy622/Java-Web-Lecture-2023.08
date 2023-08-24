package com.example.demo.oracle.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
//   private BlogDao bDao = new BlogDao();
   @Autowired private BlogDao bDao; // Spring에서 BlogDao() 객체를  생성해서 inject(꽂아준다.)
   
   @GetMapping("/list")
   public String list(Model model) {
      List<Blog> list = bDao.getBlogList("title","");
      model.addAttribute("blogList", list);
      model.addAttribute("menu", "blog");
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
	   bDao.insertBlog(blog);
	   return "redirect:/blog/list";
   }
   
   @GetMapping("/detail/{bid}")
   public String detail(@PathVariable int bid, Model model) {  
	   bDao.increaseViewCount(bid);				// 조회수 증감
	   Blog blog = bDao.getBlog(bid);
	   model.addAttribute("blog", blog);
	   model.addAttribute("menu", "blog");
	   return "blog/detail";
   }
   
   @GetMapping("/update/{bid}")
   public String updateForm(@PathVariable int bid, Model model) {
	   Blog blog = bDao.getBlog(bid);
	   model.addAttribute("blog", blog);
	   model.addAttribute("menu", "blog");  
	   // update를 하려면 기존에 가지고 있던 자료를 데이터베이스에서 가져와서 업데이트로 넘겨줘야함
	   return "blog/update";
   }
   
   @PostMapping("/update")
   public String updateProc(Blog  blog) {
// public String updateProc	(int bid, String penName, String title, content) {
//	   Blog blog = new Blog(bid, penName, title, content);  //이렇게 쓸수도 있음
	   bDao.updateBlog(blog);
	   return "redirect:/blog/detail/" + blog.getBid();
   }
   
}