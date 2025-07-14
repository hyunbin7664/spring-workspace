package com.example.demo.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Comment")
public class Comment {

	private int commentNo;
	private int postNo;
	private int userNo;
	private String content;
	private String deleted;
	private Date createdDate;
	private Date updatedDate;

	// app_users 테이블과 조인시 사용
	private User user;

}
