package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl2 implements UserService {

	@Override
	public void createUser(String name) {
		System.out.println("[[" + name + "]] 사용자 생성");
	}
}
