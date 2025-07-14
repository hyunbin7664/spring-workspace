package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.UserService;
import com.example.service.UserServiceImp1;

public class App1 {

	public static void main(String[] args) {
		ApplicationContext context
		= new AnnotationConfigApplicationContext(App1Config.class);
		
		UserService service 
		= context.getBean(UserService.class);
		
		service.회원가입("홍길동", "zxcv1234");
		System.out.println();
		
		service.회원탈퇴("김유신");
		System.out.println();
		
		service.회원조회("kim");
	}
}
