package com.example.demo.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = -7550881696969175849L;

	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}
