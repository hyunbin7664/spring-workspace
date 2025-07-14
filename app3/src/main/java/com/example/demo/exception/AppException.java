package com.example.demo.exception;

/*
 *  프로젝트에서 자주 쓸 수 있는 ‘공통 예외 클래스’
 *  
 *  RuntimeException : 프로그램 실행 중에(=런타임 중에) 발생하는 에러
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -7550881696969175849L;

	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}
