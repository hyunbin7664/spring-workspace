package com.example.service;

public interface UserService {

	void 회원가입(String name, String password);
	void 회원탈퇴(String name);
	String 회원조회(String name);
}
