package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

/**
 * 사용자 관리에 필요한 업무로직을 수행하는 클래스다.
 * UserRepository 인터페이스의 구현체가 필요하다.
 */
@Service
public class UserService {

	/*
	 * UserService객체가 의존하는 객체가 무엇인지 나타낸다.
	 * UserService객체가 의존하는 객체를 조립받기 위해서 정의한 변수다.
	 */
	private UserRepository userRepository;
	
	/*
	 * UserService객체가 의존하는 객체를 
	 * 제 3자로부터 전달받기 위한 메서드를 정의한다.
	 */
	@Autowired
	public void setUserRepository(@Qualifier("oracleUserRepo") UserRepository userRepository) {
		System.out.println("UserService의 setUserRepository가 실행됨");
		System.out.println("setUserRepository가 주입받은 객체: " + userRepository);
		this.userRepository = userRepository;
	}
	
	public void 신규회원가입(String name) {
		System.out.println("UserService의 신규회원가입 업무로직 수행됨");
		
		userRepository.createUser(name);
	}
	
	public void 회원탈퇴(String name) {
		System.out.println("UserService의 회원탈퇴 업무로직 수행됨");
		
		userRepository.deleteUser(name);
	}
}
