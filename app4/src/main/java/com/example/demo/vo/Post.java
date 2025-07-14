package com.example.demo.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Post")
public class Post {

	private int postNo;
	private int userNo;
	private String title;
	private String content;
	private String deleted;
	private Date createdDate;
	private Date updatedDate;
}
