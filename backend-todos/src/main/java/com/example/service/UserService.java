package com.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exception.JwtException;
import com.example.model.ERole;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.web.payload.auth.SignupRequest;
import com.example.web.payload.auth.SignupResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public SignupResponse createUser(SignupRequest request) {
		// username과 email 중복 여부를 체크한다.
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new JwtException("사용할 수 없는 사용자이름입니다.");
		}
		if (userRepository.existsByEmail(request.getEmail())) {			
			throw new JwtException("사용할 수 없는 이메일입니다.");
		}
		
		// SignupRequest -> User로 복사한다.
		User user = modelMapper.map(request, User.class);
		// 비밀번호를 암호화해서 User에 저장한다.
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		// User에 접근 권한을 저장한다.
		user.setRole(ERole.ROLE_USER);
		
		// User객체를 전달해서 테이블에 저장시킨다.
		// user -> id:0, username:xxx, password:xxx
		userRepository.save(user);
		// user -> id:2, username:xxx, password:xxx
		
		// User -> SignupResponse에 복사하고, 응답으로 반환한다.
		return modelMapper.map(user, SignupResponse.class);
	}
}
