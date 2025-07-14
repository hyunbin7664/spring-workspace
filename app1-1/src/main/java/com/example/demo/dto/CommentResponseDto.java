package com.example.demo.dto;

import java.util.Date;

import com.example.demo.vo.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

	private int commentNo;			// 댓글 번호
	private int postNo;				// 게시글 번호
	private int userNo;				// 작성자 번호
	private String name;			// 작성자 이름
	private String content;			// 내용
	@JsonFormat(pattern = "yyyy년 M월 d일")
	private Date createdDate;		// 등록일
	
	public CommentResponseDto(Comment comment) {
		this.commentNo = comment.getCommentNo();
		this.postNo = comment.getPostNo();
		this.userNo = comment.getUserNo();
		this.name = comment.getUser().getName();
		this.content = comment.getContent();
		this.createdDate = comment.getCreatedDate();
	}
 }
