package com.example.demo.web.response;

import java.util.Date;

import com.example.demo.vo.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class CommentResponse {

	private int commentNo;
	private int postNo;
	private int userNo;
	private String name;
	private String content;
	@JsonFormat(pattern = "yyyy년 M월 d일 EEEE a h시 m분 s초")
	private Date createdDate;
	
	public CommentResponse(Comment comment) {
		this.commentNo = comment.getCommentNo();
		this.postNo = comment.getPostNo();
		this.userNo = comment.getUserNo();
		this.name = comment.getUser().getName();
		this.content = comment.getContent();
		this.createdDate = comment.getCreatedDate();
	}
}
