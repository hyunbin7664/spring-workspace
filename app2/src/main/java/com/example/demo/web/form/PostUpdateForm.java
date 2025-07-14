package com.example.demo.web.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateForm {

	private int postNo;
	private String title;
	private String content;
	private MultipartFile upfile;
	
}
