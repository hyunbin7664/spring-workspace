package com.example.demo.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias("Post")
public class Post {

	private int postNo;
	private int userNo;
	private String title;
	private String content;
	private String deleted;
	private Date createdDate;
	private Date updatedDate;
	
	// 게시글의 작성자 정보
	private String username;
	private String name;
}
