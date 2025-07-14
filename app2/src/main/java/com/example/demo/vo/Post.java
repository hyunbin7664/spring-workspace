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
	private String filename;
	private Date createdDate;
	private Date updatedDate;
	
	private User user;
	
	public String getOriginalFilename() {
		if (filename == null) {
			return null;
		}
		return filename.substring(36);
	}
}
