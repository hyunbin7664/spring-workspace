package com.example.demo.web.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AppException;
import com.example.demo.web.response.ApiResponse;

@RestControllerAdvice(basePackages = "com.example.demo.web.rest")
public class RestControllerExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ApiResponse<Void>> handlerAppException(AppException ex) {
		ApiResponse<Void> apiResponse 
			= ApiResponse.fail(500, ex.getMessage());
		
		return ResponseEntity
				.status(500)
				.body(apiResponse);
	}
}
