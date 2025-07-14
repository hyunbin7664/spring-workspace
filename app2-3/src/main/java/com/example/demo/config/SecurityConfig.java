package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.CustomAuthenticationEntryPoint;

import jakarta.servlet.DispatcherType;

/*
 * @Configuration
 *  - 이 클래스가 구성정보를 제공하는 클래스임을 나타내는 어노테이션이다.
 *  - @Bean 어노테이션이 선언된 메소드가 반환하는 객체는
 *    스프링 컨테이너의 빈으로 등록된다. 
 * 
 * @EnableWebSecurity
 * - 이 클래스가 스프링 시큐리티 보안 설정 정보를 제공하는 클래스임을 나타낸다.
 * - 스프링 시큐리티의 보안을 활성화시킨다.
 * - 내부에서는 WebSecurityConfigurerAdapter(spring boot 2.7이전)나
 *   SecurityFilterChain을 기반으로 웹 애플리케이션 보안설정을
 *   커스터마이징한다.
 * 
 * @EnableMethodSecurity
 * - 메소드 기반 접근제어를 활성화시키는 어노테이션이다.
 * - @PreAuthorize, @PstAuthorize, @Secured 어노테이션을
 *   사용해서 메소드 기반 접근제어를 적용할 수 있다.
 * - 주로, 컨트롤러의 요청 핸들러 메소드 혹은 서비스의 업무로직 메소드에
 *   메소드 기반 접근제어를 적용한다.
 * - 메소드 기반 접근제어는 AOP를 통해서 이루어진다.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	/*
	 * filterChain(HttpSecurity http) 메소드
	 *  - HttpSecurity객체를 전달받아서 스프링 시큐리티 보안설정을 구성하는
	 *    메소드다.
	 *  - 이 메소드의 보안 설정을 기반으로 SecurityFilterChain 객체가
	 *    생성되고, 스프링 컨테이너의 빈으로 등록된다.
	 *    
	 * SecurityFilterChain
	 *  - 스프링 시큐리티에서 보안작업(인증/인가)을 처리하는 SecurityFilter들의
	 *    집합체인이다.
	 *  - SecurityFilterChain을 구성하는 SecurityFilter들은
	 *    filterChain(HttpSecurity http) 메소드를 이용해서 커스터마이징된다.
	 *   
	 * HttpSecurity
	 * 	- 스프링 시큐리티의 보안설정을 커스터마이징할 수 있는 기능을 제공하는 객체다.
	 *  - build() 메소드를 실행하면 커스터마이징이 적용된 
	 *    SecurityFilter로 구성된 
	 *    SecurityFilterChain 객체가 생성된다.
	 *  - 주요 기능
	 *  	- .authorizeHttpRequests()
	 *  		* 요청 URL 패턴에 따른 접근제한정책(인가 관련 설정)을 설정한다.
	 *  	- .formLogin()
	 *  		* 폼 기반 로그인 관련 정보를 설정한다.
	 *  		* 커스텀 로그인 페이지, 로그인 성공/실패시 핸들러 설정
	 *  	- .logout()
	 *  		* 로그아웃 처리 설정
	 *  		* 로그아웃 URL, 세션 무효화, 로그아웃 성공 후 이동URL
	 *  	- .exceptionHandling()
	 *  		* 인증실패, 인가실패 발생시 사용자 정의 핸들러 설정한다.
	 *  	- .csrf()
	 *  		* csrf 보호 설정, 폼 기반 POST 요청에 적용된다.
	 *  	
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
					.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
					.requestMatchers("/user/**").hasRole("USER")
					.requestMatchers("/order/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/cart/**").authenticated()
					.requestMatchers("/schedule/**").authenticated()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/api/admin/**").hasRole("ADMIN")
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
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			
			)
			.exceptionHandling(handling -> handling
					.authenticationEntryPoint(null)
					.accessDeniedHandler(customAccessDeniedHandler));
	
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





