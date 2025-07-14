package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.UserService;

public class App2 {
	
	public static void main(String[] args) {
		
		ApplicationContext context 
		= new ClassPathXmlApplicationContext("ctx.xml");
		
		UserService service = context.getBean(UserService.class);
		
		service.createUser("김유신");
	}

}
