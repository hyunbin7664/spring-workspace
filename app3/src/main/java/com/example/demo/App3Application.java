package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App3Application {

	public static void main(String[] args) {
		SpringApplication.run(App3Application.class, args);
	}

}

/*
 * @SpringBootApplication
 * 	: 아래 세 가지 기능을 한 번에 함
 * 	  @Configuration → 설정 클래스라는 뜻
 * 	  @EnableAutoConfiguration → 스프링이 자동으로 설정을 해줌 (예: DB, 웹 등)
 * 	  @ComponentScan → com.example.demo 패키지 안의 컴포넌트(Controller, Service 등)를 자동으로 찾아 등록해줌
 * 
 * public class App3Application 
 * 	: 프로그램의 시작점(main 클래스) 역할
 * 
 * public static void main(String[] args) 
 * 	: 자바 프로그램이 실행될 때 가장 먼저 호출되는 메서드	
 * 	  여기서는 SpringApplication.run(...)을 호출해 Spring Boot 애플리케이션이 실행됨
 */
