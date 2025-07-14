package com.example.demo.web.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
	private int status;
	private String message;
	private T data;
	
	
	// 성공 응답 - status:200, message:메시지, data:null
	public static <T> ApiResponse<Void> success(String message) {
		ApiResponse<Void> res = new ApiResponse<Void>();
		res.status = HttpServletResponse.SC_OK;
		res.message = message;
		
		return res;
	}
	// 성공 응답 - status:200, message:"성공", data:데이터
	public static <T> ApiResponse<T> success(T data) {
		ApiResponse<T> res = new ApiResponse<T>();
		res.status = HttpServletResponse.SC_OK;
		res.message = "성공";
		res.data = data;
		
		return res;
	}
	// 성공 응답 - status:200, message:메시지, data:데이터
	public static <T> ApiResponse<T> success(String message, T data) {
		ApiResponse<T> res = new ApiResponse<T>();
		res.status = HttpServletResponse.SC_OK;
		res.message = message;
		res.data = data;
		
		return res;
	}
	
	// 실패 응답 - status:응답코드, message, 메시지, data:null
	public static ApiResponse<Void> fail(int status, String message) {
		ApiResponse<Void> res = new ApiResponse<Void>();
		res.status = status;
		res.message = message;
		
		return res;
	}
	

}
