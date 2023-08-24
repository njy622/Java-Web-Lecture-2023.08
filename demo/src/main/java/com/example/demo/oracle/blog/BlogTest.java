package com.example.demo.oracle.blog;

import java.util.List;

public class BlogTest {
	
	public static void main(String[] args) {
		BlogDao bDao = new BlogDao();
		
		//insert
//		Blog blog = new Blog("프로그래머", "자바", "자바는 객체지향 프로그램....");
//		bDao.insertBlog(blog);
		
		
		// 원하는 bid값 한줄 불러오기
//		Blog b = bDao.getBlog(3);
//		System.out.println(b);
		
		//모두 조회
//		List<Blog> list = bDao.getBlogList("title", "");
//				for (Blog b : list)
//					System.out.println(b);
				
		//수정
//		Blog blog = bDao.getBlog(4);
//		blog.setTitle("파이썬");
//		blog.setContent("파이썬은 배우기 쉬운 프로그래밍 언어입니다");
//		bDao.updateBlog(blog);
//		
//		List<Blog> list = bDao.getBlogList("title", "");
//		for (Blog b : list)
//			System.out.println(b);
		
		//삭제 (오라클에서 데이터는 남아있지만, isDelete에 1로 변경되어있음
//		bDao.deleteBlog(2);
//		List<Blog> list = bDao.getBlogList("title", "");
//		for (Blog b : list)
//			System.out.println(b);
		
		//조회수 증가
//		bDao.increaseViewCount(1);
//		bDao.increaseViewCount(1);
//		bDao.increaseViewCount(3);
//		bDao.increaseViewCount(4);
		
		List<Blog> list = bDao.getBlogList("title", "");
		for (Blog b : list)
			System.out.println(b);
	}

}
