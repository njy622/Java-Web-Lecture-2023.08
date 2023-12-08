package com.human.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Value("${spring.servlet.multipart.location}")   // 이 값을 uploadDir 주입시켜줌
	private String uploadDir; 

	
	@GetMapping("/formUpload")
	public String formUpload() {
		return "file/form";
	}
}