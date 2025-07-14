package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

/*
 * 스프링 시큐리티(Spring Security) 설정 파일
 * : 로그인, 로그아웃, 보안, 비밀번호 암호화 등 설정
 * 
 * 이 파일은 웹사이트의 로그인/로그아웃과 보안 정책을 설정하는 코드
 * 사용자가 로그인할 때 어떤 페이지로 이동하고, 로그아웃하면 어디로 가고, 비밀번호는 어떻게 암호화할지 등을 정해줘.
 */
@Configuration
@EnableWebSecurity	// 스프링 시큐리티를 “켜겠다”는 선언
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)	// 메서드 단위로도 보안 설정을 할 수 있게 함?
public class SecurityConfig {

	// filterChain : 보안 규칙 설정
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
						/*
						 * FORWARD랑 INCLUDE로 들어온 요청은 막지 말고 그냥 통과시켜줘 
						 * 
						 * DispatcherType.FORWARD 
						 * : 서버 안에서 다른 페이지로 내부 이동할 때 사용. 
						 * 		ex) 로그인 실패 시 → 다시 로그인 페이지로 forward할 때
						 * 
						 * DispatcherType.INCLUDE
						 * : JSP 같은 데서 다른 페이지를 일부분만 포함(include) 할 때 사용. 
						 * 		ex) header.jsp, footer.jsp 같은 공통 요소를 포함할 때
						 */
					.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
					
					.requestMatchers("/**").permitAll() // 모든 URL(페이지)에 누구나 접근 허용
			)
			.formLogin(formLogin -> formLogin
					.usernameParameter("username")	// 로그인폼의 아이디 필드명을 지정한다.
					.passwordParameter("password")	// 로그인폼의 비밀번호 필드명을 지정한다.
					.loginPage("/login")			// 로그인폼을 요청하는 URL을 지정한다.
					.loginProcessingUrl("/login")	// 로그인처리를 요청하는 URL을 지정한다.
					.defaultSuccessUrl("/")			// 로그인 성공시 이동할 URL을 지정한다.
					.failureUrl("/login?failed")	// 로그인 실패시 이동할 URL을 지정한다.
			)
			.logout(logout -> logout
					.logoutUrl("/logout")			// 로그아웃 요청 URL을 지정한다.
					.logoutSuccessUrl("/")			// 로그아웃 완료 후 이동할 URL을 지정한다.
			);
			
	
		return http.build();
	}
	
	/*
	 * BCryptPasswordEncoder
	 * - 비밀번호 암호화를 지원하는 클래스다.
	 * - 똑같은 비밀번호를 암호화하더라도 매번 다르게 인코딩된다.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}











