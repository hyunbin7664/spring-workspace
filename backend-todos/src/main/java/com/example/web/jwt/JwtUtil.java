package com.example.web.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.model.ERole;
import com.example.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * JwtUtil
 * - JWT 토큰 관련 기능을 제공하는 유틸리티 클래스다.
 * - 인증된 사용자 정보로 JWT 토큰을 생성한다.
 * - 클라이언트가 보낸 JWT 토큰의 유효성을 검증한다.(변조 여부, 만료여부)
 * - 클라이언트가 보낸 JWT 토큰에서 사용자 정보를 추출한다.
 */
@Component
public class JwtUtil {
   // JWT 토큰을 암호화할 때 사용되는 시크릿 키
   private static final String JWT_ACCESS_SECRET = "tvBOmQXZMS1U4je7lM7tI0vIbBPszz13NahgFFe8yx0gjz8HwqpPhUMbvRuATTvXmbvMFHVmbqc3tvl3Rn3JMA4JcnjsTei4";
   // JWT 엑세스 토큰의 만료 시간 : 15분
   private static final int JWT_ACCESS_TOKEN_VALIDITY_IN_SECONDS = 60*15*1000;
   
   // Base64로 인코딩된 시크릿 키을 전자서명용 SecretKey 객체로 변환한다.
   // SecretKey 객체는 JWT 토큰 생성/검증에 사용된다.
   private final SecretKey ACCESS_SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_ACCESS_SECRET));
   
   // 인증된 사용자정보로 JWT 엑세스 토큰을 생성한다.
   public String generateAccessToken(User user) {
        return Jwts.builder()
        	  // sub 클레임정보(식별자정보)를 설정한다.(사용자번호를 식별자정보로 설정)
              .subject(String.valueOf(user.getId()))
              // iat 클레임정보(발행시간) 설정
              .claim("role", user.getRole().name())
              // exp 클레임정보(만료시간) 설정
              .issuedAt(new Date())
              // claim(key, value) 메소드는 사용자 정의 클레임정보를 설정한다.
              // 여기서는 "role"이라는 이름으로 사용자의 보유권한을 설정한다.
              .expiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY_IN_SECONDS))
              // JWT에 전자서명을 추가한다.
              .signWith(ACCESS_SECRET_KEY)
              // JWT 토큰(문자열)을 생성한다.
              .compact();
   }
      
   // JWT 엑세스 토큰의 유효성을 검증한다.
   public boolean validateAccessToken(String token) {
      try {
         Jwts
         	// JWT 토큰을 검증하는 JwtParser객체를 쵝득한다.
         	.parser()
         	// JWT 토큰을 검증할 때 사용되는 전자서명키를 지정한다.
            .verifyWith(ACCESS_SECRET_KEY)
            .build()
            // JWT 토큰을 분석한다.
            // 최종적으로 JWT 토큰 문자열을 파싱하고 전자서명을 검증한다.
            // 이 메소드의 실행결과는 Claim 객체가 반환된다.
            // 만약, JWT 토큰이 유효하지 않으면 이 메소드를 실행했을 때 예외가 발생한다.
            .parseSignedClaims(token);
         return true;
      } catch (Exception ex) {
         return false;
      }
   }
   
   // JWT 엑세스 토큰에서 사용자아이디(사용자 번호)를 추출한다.
   public Long extractUserIdInAccessToken(String accessToken) {
      return Long.valueOf(Jwts.parser()
            .verifyWith(ACCESS_SECRET_KEY)
            .build()
            // JWT 토큰을 파싱하고, 전자서명을 검증한다.
            // Claim 객체를 반환한다.
            .parseSignedClaims(accessToken)
            // Payload를 반환한다.
            .getPayload()
            // Payload에서 subject(식별자 정보)를 반환한다.
            .getSubject());
   }
   
   // JWT 엑세스 토큰에서 사용자의 보유 권한을 추출한다.
   public ERole extractRoleInAccessToken(String accessToken) {
      return ERole.valueOf(Jwts.parser()
            .verifyWith(ACCESS_SECRET_KEY)
            .build()
            // JWT 토큰을 파싱하고 Claim 객체를 반환한다.
            .parseSignedClaims(accessToken)
            // Payload를 반환한다.
            .getPayload()
            // 사용자정의 클레임정보 중 "role"를 반환한다.
            .get("role", String.class));
   }

}
