package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponseDto;
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {

	private final PostService postService;
	
	/*
	 * 요청 방식 : POST
	 * 요청 URL : /api/comments
	 * 요청 데이터 : {"postNo":73, "cpntent":"내용입니다."}
	 * 응답 데이터 : 지금 등록된 댓글
	 */
	@PostMapping
	public CommentResponseDto create(@RequestBody CommentCreateRequest request) {
		int userNo = 1;
		CommentResponseDto dto = postService.createComment(userNo, request);
		
		return dto;
	}
	
	/*
	 * 요청 방식 : GET
	 * 요청 URL : /api/comments/{postNo}
	 * 응답 데이터 : 댓글 목록
	 */
	@GetMapping("/{postNo}")
	public List<CommentResponseDto> getComments(@PathVariable("postNo") int postNo) {
		return postService.getComments(postNo);
	}
}
