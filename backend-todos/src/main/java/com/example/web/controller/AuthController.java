package com.example.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AuthService;
import com.example.service.UserService;
import com.example.web.payload.Response;
import com.example.web.payload.auth.AuthRequest;
import com.example.web.payload.auth.AuthResponse;
import com.example.web.payload.auth.RefreshRequest;
import com.example.web.payload.auth.SignupRequest;
import com.example.web.payload.auth.SignupResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<Response<SignupResponse>> signup(@RequestBody SignupRequest signupRequest) {
		SignupResponse signupResponse = userService.createUser(signupRequest);
		
		return ResponseEntity.ok()
			.body(Response.success(signupResponse, "회원가입이 완료되었습니다."));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
		AuthResponse authResponse = authService.login(authRequest);
		
		return ResponseEntity.ok()
			.body(Response.success(authResponse, "로그인이 완료되었습니다."));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Response<AuthResponse>> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse authResponse = authService.refresh(refreshRequest);
		
		return ResponseEntity.ok()
			.body(Response.success(authResponse, "토큰이 갱신되었습니다."));
	}
	
}
