package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImp1 implements UserService {

	@Override
	public void 회원가입(String name, String password) {
		// 공통 기능
		System.out.println("회원가입 기능 실행됨");
		System.out.println("이름: " + name);
		System.out.println("비밀번호: " + password);
		
		// 핵심 기능
		System.out.println("회원가입 업무로직 실행");
		
		// 공통 기능                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		System.out.println("회원가입 기능 종료됨");
		
	}
	
	@Override
	public void 회원탈퇴(String name) {
		// 공통 기능
		System.out.println("회원탈퇴 기능 실행됨");
		System.out.println("이름: " + name);
		
		// 핵심 기능
		System.out.println("회원가입 업무로직 실행");
		
		// 공통 기능
		System.out.println("회원탈퇴 기능 종료됨");
		
	}
	
	@Override
	public String 회원조회(String name) {
		System.out.println("#### 회원조회 업무로직");
		return name;
	}
}
