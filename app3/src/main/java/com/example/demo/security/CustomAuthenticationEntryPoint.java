package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.web.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint 
	implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String requestURI = request.getRequestURI();
		if (requestURI.startsWith("/api/")) {
			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			ApiResponse<Void> apiResponse
			= ApiResponse.fail(HttpServletResponse.SC_UNAUTHORIZED
							  , "인증이 필요합니다.");
			
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonText = objectMapper.writeValueAsString(apiResponse);
			response.getWriter().write(jsonText);
			
		} else {								// 일반 JSP 요청
			response.sendRedirect("/login");
		}
		
	}
}






