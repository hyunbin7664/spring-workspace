package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return userMapper.getUsers();
	}
}
