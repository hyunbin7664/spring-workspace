package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.UserRegisterException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;
import com.example.demo.web.form.UserRegisterForm;

@Service
@Transactional
public class UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	public void registerUser(UserRegisterForm form) {
		User foundUser = userMapper.getUserByUsername(form.getUsername());
		if (foundUser != null) {
			throw new  UserRegisterException("username", "이미 사용중인 아이디입니다.");
		}
		
		foundUser = userMapper.getUserByEmail(form.getEmail());
		if(foundUser != null) {
			throw new UserRegisterException("email", "이미 사용중인 이메일입니다.");
		}
		
		User user = modelMapper.map(form, User.class);
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		
		userMapper.insertUser(user);
		userMapper.insertUserRole(user.getUserNo(), "ROLE_USERS");
	}
	
	
}
