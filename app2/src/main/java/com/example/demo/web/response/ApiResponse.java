package com.example.demo.web.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private boolean success;
	private int status;
	private String message;
	private T data;
	
	public ApiResponse(boolean success, int status, String message, T data) {
		this.success = success;
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	// 성공 응답- status:200, message:메세지, data:null
	public static ApiResponse<Void> success(String message) {
		return new ApiResponse<Void>(true, 200, message, null);
	}
	
	// 성공 응답 - status:200, message:"성공", data:데이터
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(true, 200, "성공", data);
	}
	
	// 성공 응답 - status:200, message:메세지, data:데이터
	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<T>(true, 200, message, data);
	}
	
	// 실패 응답 - status:응답코드, message:메세지, data:null
	public static ApiResponse<Void> fail(int status, String message) {
		return new ApiResponse<Void>(false, status, message, null);
	}
}
/*
 * 구분		인증오류									인가오류
 * ------------------------------------------------------------------------------------	
 * JSP		로그인페이지								접근거부 오류페이지
 * 			재요청응답									재요청응답
 * ------------------------------------------------------------------------------------
 * AJAX		{										{
 * 				"success": false, 						"success": false,
 * 				"status": 401, 							"status": 403, 
 * 				"message": "인증이 필요합니다.", 			"message": "접근권한이 부족합니다.", 
 * 				"data": null							"data": null
 * 			}
 * ------------------------------------------------------------------------------------
 * 인증오류 : AuthenticationException 발생 -> AuthenticationEntryPoint의 commence() 호출
 * 인가오류 : AccessDeniedException 발생 -> AccessDeniedHandler의 handler() 호출
 * 
 * 
 * 인증되지 않은 사용자					- AuthenticationEntryPoint 동작
 * 		JSP 요청
 * 			/admin/home				: 로그인페이지 재요청 URL 응답
 * 		ajax 요청
 * 			/api/admin/users		: {status:401, message:인증이 필요합니다.}
 * 
 * 인증된 사용자(ROLE_USER 보유)			- AccessDeniedHandler 동작
 * 		JSP 요청	
 * 			/admin/home				: 접근 거부 오류페이지 재요청 URL 응답
 * 		ajax 요청
 * 			/api/admin/users		: {status:403, message:접근 권한이 부족합니다.}
 * 
 * 인증된 사용자(ROLE_ADMIN 보유)
 * 		JSP 요청
 * 			/admin/home				: 관리자 홈화면
 * 		ajax 요청
 * 			/api/admin/users		: {status:200, message:성공, data[{}, {}]}
 */










