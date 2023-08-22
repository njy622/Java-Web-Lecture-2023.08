package com.example.demo.oracle;

import java.util.ArrayList;
import java.util.List;

public class Ex99_ListApp {

	public static void main(String[] args) {
		
		//기본생성자 구성으로 setter를 이용해서 객체를 하나 만든것
		Customer c1 = new Customer();
		c1.setCustId(101);
		c1.setName("박찬호");
		c1.setAddr("미국 엘에이");
		c1.setPhone("010-2345-6789");
		
		
		//파라메타 값을 넣어서 객체를 하나 만든것
		Customer c2 = new Customer(102, "박인비", "대한민국 서울", "010-3456-7890");
		System.out.println(c1); System.out.println(c2);
		System.out.println();
	
		// 리스트안에 Customer라는것들이.. 줄줄줄...리스트..
		List<Customer> list = new ArrayList<>();
		list.add(c1); list.add(c2);
		System.out.println(list.get(0)); System.out.println(list.get(1));
		System.out.println();
		
		for (Customer c : list)  		// list에서 데이터 하나를 뽑아 냈으면 타입은 Customer타입
			System.out.println(c);

		System.out.println();
		
		list.forEach(x -> System.out.println(x)); //람다식함수 표현
	}
}
