package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * 
 */
public class App5 {

	public static void main(String[] args) {
		ApplicationContext context
		= new AnnotationConfigApplicationContext(App5Config.class);
	}
}
 