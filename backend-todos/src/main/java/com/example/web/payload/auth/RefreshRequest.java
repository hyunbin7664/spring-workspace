package com.example.web.payload.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {

	private String refreshToken;
}
