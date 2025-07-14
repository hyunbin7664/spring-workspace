package com.example.web.payload.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {	
	private String username;
	private String password;
	private String email;
	private String nickname;
}
