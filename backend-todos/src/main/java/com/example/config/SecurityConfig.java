package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.web.jwt.JwtAuthenticationFilter;
import com.example.web.payload.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
   private final JwtAuthenticationFilter jwtAuthenticationFilter;

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Bean
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http
	    	 // csrf 취약점 보호 기능을 비활성화한다.
    		 // POST, PUT, DELETE 요청시 csrf 토큰을 사용할 필요가 없다.
	         .csrf(csrf -> csrf.disable())
	         // 일반적인 폼 로그인 인증 기능을 비활성화한다.
	         .formLogin(formLogin -> formLogin.disable())
	         // 로그아웃 처리를 비활성화한다.
	         .logout(logout -> logout.disable())
	         // HTTP의 기본 인증방식을 비활성화 한다.
	         .httpBasic(httpBasic -> httpBasic.disable())
	         // 같은 오리진인 경우 iframe 기능을 사용할 수 있게 한다.
	         // h2-console이 iframe 기능을
	         .headers(headers -> headers
	            .frameOptions(config -> config.sameOrigin()))
	         // cors(Cross-Origin Resource Sharing : 교차 출처 자원 공유)
	         .cors(cors -> cors
	            .configurationSource(corsConfigurationSource()))
	         // HTTP 세션 생성 전략을 절대 사용하지 않음으로 설정한다.
	         .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.NEVER))
	         // URL 기반 접근 제어
	         // h2 콘솔홤면 접근은 허용한다.
	         // /api/auth/* 접근은 허용한다.
	         // 나머지는 전부 인증이 필요하다.
	         .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/api/auth/*").permitAll()
	            .anyRequest().authenticated())
	         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	         .exceptionHandling(exceptionHandling -> exceptionHandling
	            .authenticationEntryPoint((req, res, authException) -> {
	               res.setContentType("application/json; charset=utf-8");
	               res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	
	               Response<Void> response = Response.fail(HttpServletResponse.SC_UNAUTHORIZED, "유효한 Access Token이 아닙니다.");
	               ObjectMapper objectMapper = new ObjectMapper();
	               res.getWriter().write(objectMapper.writeValueAsString(response));
            })
            .accessDeniedHandler((req,  res, accessDeniedException) -> {
               res.setContentType("application/json; charset=utf-8");
               res.setStatus(HttpServletResponse.SC_FORBIDDEN);

               Response<Void> response = Response.fail(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
               ObjectMapper objectMapper = new ObjectMapper();
               res.getWriter().write(objectMapper.writeValueAsString(response));
            }))
         .build();
   }
   
   // CORS를 전부 허용하도록 설정된 CorsConfigurationSource 객체를 
   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(List.of("*"));
      configuration.setAllowedMethods(List.of("*"));
      configuration.setAllowedHeaders(List.of("*"));
      configuration.setAllowCredentials(false);
      configuration.applyPermitDefaultValues();
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }
}
