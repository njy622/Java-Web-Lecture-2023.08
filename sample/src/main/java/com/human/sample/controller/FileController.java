package com.human.sample.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;	// Value가 위와 같을때 application.properties에 있는 c:/Temp/ 에 uploadDir라는 이름으로 넣어라!

	@GetMapping("/formUpload")
	public String formUpload() {
		return "file/form";
	}
	
	@PostMapping("/formUpload") 
	public String formProc(String title, MultipartFile file, Model model){
		model.addAttribute("title", title);
		String filename = file.getOriginalFilename();
		model.addAttribute("filename", filename);
		String path = uploadDir + "sample/" + filename;
		try {
			file.transferTo(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "file/formRes";
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> fileDownload(@PathVariable String filename) {
		String pathStr = uploadDir + "sample/" + filename;		// 업로드한 파일의 위치
		Path path = Paths.get(pathStr);
		try {
			String contentType = Files.probeContentType(path);
			HttpHeaders header = new HttpHeaders();
			header.setContentDisposition(
					ContentDisposition.builder("attachment")
							.filename(filename, StandardCharsets.UTF_8).build()
							);
			header.add(HttpHeaders.CONTENT_TYPE, contentType);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/formUploadMultiple")
	public String formUploadMultiple() {
		return "file/formMultiple";
	}
	
	@PostMapping("/formUploadMultiple") 
	public String formUploadMultipleProc(MultipartHttpServletRequest req, Model model) {
		String title = req.getParameter("title");		// req : session, param을 포함한 모든것을 다 받아올수 있다
		List<MultipartFile> multipartFileList = req.getFiles("files");
		List<String> fileList = new ArrayList<>();
		for (MultipartFile part: multipartFileList) {
			String filename = part.getOriginalFilename();
			System.out.println("filename: " + filename);
			fileList.add(filename);
			String path = uploadDir + "sample/" + filename;
			try {
				part.transferTo(new File(path));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("title", title);
		model.addAttribute("fileList", fileList);
		return "file/formMultipleRes";
	}
	
	
}
