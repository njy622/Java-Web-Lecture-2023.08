package com.example.demo.oracle.Customer;

import java.util.List;

public class Ex11_CustomerTest {

	public static void main(String[] args) {
		CustomerDao cDao = new CustomerDao();
		// custId 값으로 검색해서 Customer 객체를 구하고 출력  
//		Customer c1 = cDao.getCustomer(6);
//		System.out.println(c1);
		
		// 모든 Customer List를 가져옴
//		List<Customer> list = cDao.getCustomerList();
//		for (Customer c2 : list)
//		System.out.println(c2);

		// cDao.insertCustomerList(Customer c) Customer 객체를 주고 입력
//		Customer c3 = new Customer(8, "김민재", "독일 뮌헨", "010-3333-7777");
//		cDao.insertCustomer(c3);
		
		// cDao.updateCustomerList(Customer c)
//		Customer c4 = cDao.getCustomer(8);
//		c4.setPhone("010-3456-9876"); c4.setName("고영복"); c4.setAddr("대한민국 서울");
//		cDao.updateCustomer(c4);
//
//		// 모든 Customer List를 가져옴	
//		List<Customer> list = cDao.getCustomerList();
//		for (Customer c2 : list)
//			System.out.println(c2);
		// cDao.deleteCustomerList(int custId)
//		cDao.deleteCustomer(8);
//
//		// 모든 Customer List를 가져옴	
//		List<Customer> list = cDao.getCustomerList();
//		for (Customer c2 : list)
//			System.out.println(c2);
		
//		// 나라별 거주하는 고객리스트
//		List<Customer> list = cDao.getCustomerListByAddr("대한민국");
//		for (Customer c2 : list)
//			System.out.println(c2);
		
		// 이름에 박이 들어가는 사람
//		List<Customer> list = cDao.getCustomerListByName("박");
//		for (Customer c2 : list)
//			System.out.println(c2);

		// 제너럴한 것으로 만들어놓으면 편하게 사용가능
//		List<Customer> list = cDao.getCustomerListByFileAndQuery("address","대한민국");
//		for (Customer c2 : list)
//			System.out.println(c2);
		
		List<Customer> list = cDao.getCustomerListByFileAndQuery("name","박");
		for (Customer c2 : list)
			System.out.println(c2);

	
	}

}
