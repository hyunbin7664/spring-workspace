package com.example.demo.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
	
	private int postNo;
	private String content;
}
