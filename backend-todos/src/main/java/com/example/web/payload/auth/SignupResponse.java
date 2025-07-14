package com.example.web.payload.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignupResponse {	
	private Long id;
	private String username;
	private String email;
	private String nickname;
}
