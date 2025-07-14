package com.example.demo.controller.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.AppException;

/*
 * @ControllerAdvice
 * - Spring MVC에서 공통기능 처리를 담당하는 클래스임을 나타내는 어노테이션이다.
 * - 여러 컨트롤러 클래스에 적용되는 공통기능을 구현할 수 있다.
 * 	- 컨트롤러 클래스에서 발생되는 예외처리(@ExceptionHandler)
 * 	- 공통 모델 속성 정의(@ModelAttribute)
 * 	- 요청 파라미터 바인딩 초기화(@InitBinder)
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	// 예외처리
	@ExceptionHandler(AppException.class)
	public String handleDataAppException(AppException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		
		return "error/app";	// WEB-INF/views/error/app.jsp
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException() {
		return "error/unknown";
	}
}
