package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.NotificationService;

public class App8 {
	public static void main(String[] args) {
		ApplicationContext context
		= new AnnotationConfigApplicationContext(App8Config.class);
		
		NotificationService s
		= context.getBean(NotificationService.class);
		s.sendMessage("sms", "6월 여름 세일을 시작합니다.");
	}
}
