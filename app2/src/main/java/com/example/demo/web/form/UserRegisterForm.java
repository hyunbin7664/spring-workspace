package com.example.demo.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterForm {
	
	// 1단계 입력폼의 값을 담는 변수
//	@NotBlank(message = "아이디는 필수 입력값입니다.")
//	@Size(min = 3, message = "아이디는 최소 3글자 이상입니다.")
	private String username;
	
//	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
//	@Size(min = 8, message = "비밀번호는 8글자 이상입니다.")
	private String password;
	
//	@NotBlank(message = "이름은 필수 입력값입니다.")
//	@Size(min = 2, message = "이름은 2글자 이상입니다.")
	private String name;
	
//	@NotBlank(message = "이메일은 필수 입력값입니다.")
//	@Email(message = "유효한 이메일 형식이 아닙니다.")
	private String email;
	
	// 2단계 입력폼의 값을 담는 변수
	private String phone;
	private String zipcode;
	private String address1;
	private String address2;
}
