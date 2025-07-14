package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.UserService;

/*
 * ApplicationContext
 * 	- 스프링 컨테이너가 제공할 기능이 정의된 인터페이스다.
 * 	- 이 인터페이스를 구현한 다양한 구현 클래스(스프링 컨테이너)가 있다.
 * 
 * AnnotationConfigApplicationContext
 * 	- ApplicationContext의 구현 클래스 중 하나다.
 * 	- 자바기반 설정정보를 이용해서 객체를 생성/관리/조립을 수행한다.
 */
public class App {
	
	public static void main(String[] args) {
		// 스프링 컨테이너를 생성한다.
		// 객체 생성에 필요한 설정정보는 AppConfig 자바 클래스가 제공한다.
		// 1. 스프링 컨테이너 객체가 생성된다.
		// 2. AppConfig의 설정정보를 분석하는 객체가 생성된다.
		// 3. com.example.service 패키지를 스캔해서 UserService객체를 생성한다.
		// 4. 의존성 주입 정보를 분석하는 객체가 생성된다.
		// 5. UserService객체가 필요로 하는 객체가 있었다면 자동으로 조립된다.
		// 6. UserService객체가 사용할 준비가 완료된다.
		ApplicationContext context
		= new AnnotationConfigApplicationContext(AppConfig.class);
		
		// 스프링 컨테이너가 생성한 객체를 꺼내온다.
		UserService userService = context.getBean(UserService.class);
		System.out.println(userService);
		
		userService.createUser("홍길동");
	}

}
