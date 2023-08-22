package com.example.demo.oracle.book;

import java.util.List;
import com.example.demo.oracle.Customer;
import com.example.demo.oracle.CustomerDao;

public class BookMain {

	public static void main(String[] args) {
			BookDao bDao = new BookDao();
			
			Book b1 = bDao.getBook(1);
			System.out.println(b1);
			System.out.println();
			
			
			List<Book> list = bDao.getBookList();
			for (Book b2 : list)
				System.out.println(b2);
	}
}
