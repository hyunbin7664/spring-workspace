package com.example.demo.web.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.AppException;


@ControllerAdvice(basePackages = "com.example.demo.web.controller")
public class ControllerExceptionHandler {

	@ExceptionHandler(AppException.class)
	public String handlerAppException(AppException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		
		return "error/app";
	}
	
	@ExceptionHandler(DataAccessException.class)
	public String handlerDataException(DataAccessException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		
		return "error/db";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException(RuntimeException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		
		return "error/server";
	}
}
/*
 * AppException
 * 	- 업무로직에 위배되는 상황이 있을 때 개발자가 의도를 가지고 발생시킨다.
 * 	- 오류 화면
 * 		업무 오류가 발생하였습니다.
 * 		오류 코드 : ER-00001
 * 		오류 내용 : 재고 부족
 * 	- error/app.jsp
 * DataAccessException
 * 	- 데이터베이스 엑세스 작업 중 오류가 있으면 발생된다.
 * 	- 오류 화면
 * 		데이터베이스 엑세스 작업 중 오류가 발생하였습니다.
 * 		잠시 후 다시 시도하여 주시기 바랍니다.
 * 		오류가 지속되면 고객센터로 연락 바랍니다.
 * 	- error/db.jsp
 * RuntimeException, Exception
 * 	- 예상하지 못한 예외가 발생되었다.
 * 	- 오류 화면
 * 		요청처리 중 알 수 없는 오류가 발생하였습니다.
 * 		잠시 후 다시 시도하여 주시기 바랍니다.
 * 		오류가 지속되면 고객센터로 연락 바랍니다.
 * 	- error/server.jsp
 */
