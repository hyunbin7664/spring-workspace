package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Java Config 기반의 구성 정보를 활용
 */
public class App4 {
	
	public static void main(String[] args) {
		
		ApplicationContext context
		= new AnnotationConfigApplicationContext(App4Config.class);
		
	}

}
