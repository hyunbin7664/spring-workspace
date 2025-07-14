package com.example.web.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AuthResponse {	
	private String accessToken;
	private String refreshToken;
}
