package com.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserServiceImpl implements UserService {

	@Override
	public void createUser(String name) {
		System.out.println("[" + name + "] 사용자 생성");
	}
}
