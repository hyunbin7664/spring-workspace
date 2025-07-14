package com.example.demo.exception;

/*
 * 회원가입 예외를 표현하는 클래스다.
 * 	field는 유효성 검증을 통과하지 못한 입력필드 이름이다.
 */
public class UserRegisterException extends AppException {
	
	private static final long serialVersionUID = -4860672033504039964L;
	private String field;
	
	public UserRegisterException(String field, String message) {
		super(message);
		this.field = field;
	}
	
	public String getField() {
		return field;
	}

}
