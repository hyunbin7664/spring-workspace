package com.example;

import com.example.repository.OracleUserRepository;
import com.example.service.UserService;

/*
 * 스프링의 IoC, DI를 사용하지 않는 경우
 */
public class App {

	public static void main(String[] args) {
		
		// 애플리케이션 실행에 필요한 모든 객체 생성
		OracleUserRepository r = new OracleUserRepository();
		UserService userService = new UserService();
		
		// UserService객체가 의존하는 객체(UserRepository 구현객체)를 주입
		userService.setUserRepository(r);
		
		// UserService객체의 비즈니스 기능 실행
		userService.신규회원가입("홍길동");
	}
}
