package com.example.demo.oracle.blog;

public class BlogTest {
	
	public static void main(String[] args) {
		BlogDao bDao = new BlogDao();
		
		Blog b = bDao.getBlog(1);
		System.out.println(b);
	}

}
