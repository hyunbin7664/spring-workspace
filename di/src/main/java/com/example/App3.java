package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.OrderService;
import com.example.service.UserService;

public class App3 {

	public static void main(String[] args) {
		
		ApplicationContext context
		= new ClassPathXmlApplicationContext("ctx.xml");
		
		OrderService orderService 
		= context.getBean(OrderService.class);
		
		orderService.주문하기("홍길동", "아이패드 프로", 0);
	}
}
