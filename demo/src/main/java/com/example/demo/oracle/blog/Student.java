package com.example.demo.oracle.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 롬복하면 아래의 @를 입력하면, 밑에 코드 일일히 적지 않아도 됨
@ToString					// toString 
@Getter						//	getter
@Setter						// setter
@NoArgsConstructor			// 기본 생성자
@AllArgsConstructor			// 전체 생성자 
public class Student {
	private int sid;
	private String name;
	private String gender;
}
