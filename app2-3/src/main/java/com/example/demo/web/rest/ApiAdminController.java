package com.example.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import com.example.demo.web.response.ApiResponse;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		ApiResponse<List<User>> apiResponse
		= ApiResponse.success(users);
		
		return ResponseEntity
				.ok()					// 응답 메시지 상태코드 설정
				.body(apiResponse);		// 응답 메시지 바디부 값 설정
	}
}

