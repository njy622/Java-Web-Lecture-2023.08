package com.example.demo.oracle;

public class Ex11_CustomerMain {

	public static void main(String[] args) {
		CustomerDao cDao = new CustomerDao();
		// custId 값으로 검색해서 Customer 객체를 구하고 출력 / cDao.getCustomer(int custId) 
		Customer c = cDao.getCustomer(6);
		System.out.println(c);
		
		// cDao.getCustomerList() 메소드 생성  : List<Customer>
		

		// cDao.insertCustomerList(Customer c) Customer 객체를 주고 입력
		
		// cDao.updateCustomerList(Customer c)
		
		// cDao.deleteCustomerList(int custId)
	}

}
