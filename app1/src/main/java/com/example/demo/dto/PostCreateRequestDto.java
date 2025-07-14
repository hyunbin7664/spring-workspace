package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequestDto {

	@NotBlank(message = "제목은 필수입력값입니다.")
	@Size(min = 5, message = "제목은 5글자 이상 입력하세요.")
	private String title;
	
	@NotBlank(message = "내용은 필수입력값입니다.")
	private String content;
}
