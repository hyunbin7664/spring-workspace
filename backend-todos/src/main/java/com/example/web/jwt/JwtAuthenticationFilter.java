package com.example.web.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.model.ERole;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 요청메시지의 헤더정보에서 Authorization로 전달된 값을 조회한다.
		final String authorizationHeader = request.getHeader("Authorization");

        String accessToken = null;
        // 요청헤더정보가 null이 아니고, 요청헤더 정보에 "Bearer "가 포함되어있는지 체크한다.
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        	// 요청헤더 정보에서 7번째 글자부터 잘라낸다.
        	// 즉, accessToken이 획득된다.
        	accessToken = authorizationHeader.substring(7);
        }

        // accessToken이 null이 아니고, 유효한 토큰인지 검증한다.
    	if (accessToken != null && jwtUtil.validateAccessToken(accessToken)) {
    		// 유효한 accessToken이면 사용자번호, 접근권한을 추출한다.
    		Long userId = jwtUtil.extractUserIdInAccessToken(accessToken);
			ERole role = jwtUtil.extractRoleInAccessToken(accessToken);
    	
			// Spring Security에서 인증된 사용자정보를 표현하는
			// Authentication객체를 생성한다. 추출된 정보를 저장한다.
    		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, this.getAuthorities(role));
    		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		// SecurityContext에 저장한다.
    		SecurityContextHolder.getContext().setAuthentication(authentication);        		
    	}
  
        // 요청처리를 다음 단계로 보낸다.
        filterChain.doFilter(request, response);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(ERole role) {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
}
