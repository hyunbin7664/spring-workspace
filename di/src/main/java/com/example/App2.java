package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.UserService;

/*
 * XML 설정 정보를 활용하는 IoC, DI
 */
public class App2 {

	public static void main(String[] args) {
		
		/*
		 * 1. 스프링 컨테이너 객체가 생성된다.
		 * 2. ctx.xml 설정정보를 로딩한다.
		 * 3. 설정정보를 분석해서 OracleUserRepository 객체, 
		 * 		- OracleUserRepository 객체
		 * 		- UserService 객체
		 * 4. 설정정보를 분석해서 의존성 주입을 수행한다.
		 * 		UserService 객체에 OracleUserRepository 객체를 주입한다.
		 * 5. 모든 객체가 완전히 사용할 준비가 완료되었다.
		 */
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("ctx.xml");
		
		// 완전히 준비된 UserService 객체를 스프링 컨테이너에서 가져온다.
		UserService service = context.getBean(UserService.class);
		
		// 업무로직을 실행한다.
		service.신규회원가입("홍길동");
	}
}
