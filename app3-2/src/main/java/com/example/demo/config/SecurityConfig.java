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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
					.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
					.requestMatchers("/**").permitAll()
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











