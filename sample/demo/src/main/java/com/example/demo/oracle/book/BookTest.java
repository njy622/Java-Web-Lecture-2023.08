package com.example.demo.oracle.book;

import java.util.List;

import com.example.demo.oracle.Customer.Customer;
import com.example.demo.oracle.Customer.CustomerDao;

public class BookTest {

	public static void main(String[] args) {
			BookDao bDao = new BookDao();
			
			// id 5번 조회
//			Book b1 = bDao.getBook(5);
//			System.out.println(b1);
//			System.out.println();
//			
//			// 모두 조회
//			List<Book> list = bDao.getBookList();
//			for (Book b2 : list)
//				System.out.println(b2);
			
			//bDao.insertBookList(Book b) Book 객체를 주고 입력
//			Book b3 = new Book(14, "앞잡이 세훈이", "조세훈", 15000);
//			bDao.insertBook(b3);
//			List<Book> list = bDao.getBookList();
//			for (Book b2 : list)
//				System.out.println(b2);
			
			
			// 업데이트
//			Book b4 = bDao.getBook(14);
//			b4.setPrice(16000); b4.setBookname("앞잡이는 누구인가"); b4.setPublisher("조스민");
//			bDao.updateBook(b4);
//			
			List<Book> list = bDao.getBookList();
			for (Book b2 : list)
				System.out.println(b2);
//			
			//삭제
//			bDao.deleteBook(14);
			
			
			//제너럴한것
//			List<Book> list = bDao.getBookListByFileAndQuery("bookname","축구");
//			for (Book b2 : list)
//				System.out.println(b2);
			
	}
}
