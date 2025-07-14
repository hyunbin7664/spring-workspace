package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration
 * - 스프링 컨테이너에 의해서 자동으로 스캔되고,
 * 	스프링 컨테이너가 라이프사이클을 관리할 객체임을 나타낸다.
 */
@Configuration
@ComponentScan(basePackages = "com.example.service")
public class AppConfig {
	
	public void createUser(String name) {
		System.out.println("[" + name + "] 사용자 생성");

	}

}
