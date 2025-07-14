package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.JwtException;
import com.example.model.RefreshToken;
import com.example.model.User;
import com.example.repository.RefreshTokenRepository;
import com.example.repository.UserRepository;
import com.example.web.jwt.JwtUtil;
import com.example.web.payload.auth.AuthRequest;
import com.example.web.payload.auth.AuthResponse;
import com.example.web.payload.auth.RefreshRequest;

import lombok.RequiredArgsConstructor;

/*
 * AuthService
 * - 사용자 인증 후 엑세스 토큰과 리프레시 토큰을 발행한다.
 * - 리프레시토큰을 전달받아서 새로운 엑세스토큰을 발행한다.
 */
@Transactional
@Service
@RequiredArgsConstructor
public class AuthService {    
    private final JwtUtil jwtUtil;
    
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    
    private final PasswordEncoder passwordEncoder;

    /*
     * 인증작업을 수행하는 메소드다.
     */
    public AuthResponse login(AuthRequest request) {
    	// username에 해당하는 사용자 정보를 조회한다.
    	// 사용자가 존재하지 않으면 예외를 발생한다.
    	Optional<User> optional = userRepository.findByUsername(request.getUsername());
        User user = optional.orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호가 올바르지 않습니다."));
        
        // 비밀번호가 일치하지 않으면 예외를 발생한다.
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new JwtException(HttpStatus.UNAUTHORIZED, "아이디 혹은 비밀번호가 올바르지 않습니다.");
        }

        // 인증이 완료되었다.
        // 엑세스 토큰을 생성한다.
        String accessTokenValue = jwtUtil.generateAccessToken(user);
        
        // 리프레시 토큰을 생성한다.
        String refreshTokenValue = UUID.randomUUID().toString();
        
        // 리프레시 토큰을 테이블에 저장하기 위해서 RefreshToken객체를 생성한다.
        // 사용자번호(id), 리프레시토큰, 만료일자를 담는다.
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(user.getId());
        refreshToken.setToken(refreshTokenValue);
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        
        // 기존에 존재하는 리프레시 토큰을 삭제한다.
        // 1. 사용자 번호로 refreshToken 정보를 조회한다.
		Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByUserId(user.getId());
		// 2. refreshToken 정보가 존재하는지 체크한다.
		if (refreshTokenOptional.isPresent()) {
			// 3. 존재하면 Optional에서 RefreshToken객체를 꺼낸다.
			RefreshToken savedRefreshToken = refreshTokenOptional.get();
			// 4. 삭제할 refreshToken 정보를 전달해서 삭제한다.
			refreshTokenRepository.delete(savedRefreshToken);
		}
        // 새 리프레시토큰 정보를 저장한다.
        refreshTokenRepository.save(refreshToken);
        
        AuthResponse authResponse = AuthResponse.builder()
				.accessToken(accessTokenValue)
				.refreshToken(refreshTokenValue)
				.build();

        return authResponse;
    }
    
    public AuthResponse refresh(RefreshRequest request) {
    	String refreshTokenValue = request.getRefreshToken();
    	// 테이블에서 refreshToken정보를 조회하고, 존재하지 않으면 예외를 발생시킨다.
    	RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(() -> new JwtException(HttpStatus.UNAUTHORIZED, "유효한 RefreshToken이 아닙니다."));
    	
    	// 조회된 refreshToken 정보의 만료일자를 체크해서 만료되었으면
    	// 테이블에서 refreshToken정보를 삭제하고 예외를 발생시킨다.
    	if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new JwtException("만료된 RefreshToken입니다.");
        }
    	
    	// refreshToken정보가 아직 유효한(만료일자가 경과되지 않은) 경우
    	// 사용자번호로 사용자 정보를 조회한다.
    	User user = userRepository.findById(refreshToken.getUserId()).orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호가 올바르지 않습니다."));
    	
    	// 조회된 사용자정보로 accessToken를 새로 발행한다.
    	String newAccessTokenValue = jwtUtil.generateAccessToken(user);
    	
    	// refreshToken의 만료일자가 임박했다면 
    	// refreshToken을 다시 발생하고, 테이블에 저장시킨다.
    	String newRefreshTokenValue = UUID.randomUUID().toString();
        refreshToken.setUserId(user.getId());
        refreshToken.setToken(newRefreshTokenValue);
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        
        // 기존에 존재하는 refreshToken 정보를 삭제한다.
        refreshTokenRepository.delete(refreshToken);
        // 새 refreshToken 정보를 저장한다.
        refreshTokenRepository.save(refreshToken);
        
        // accessToken과 refreshToken 정보를 담아서 반환한다.
        AuthResponse authResponse = AuthResponse.builder()
				.accessToken(newAccessTokenValue)
				.refreshToken(newRefreshTokenValue)
				.build();

        return authResponse;
    }
    
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(1));

        return refreshTokenRepository.save(refreshToken);
    }

}
