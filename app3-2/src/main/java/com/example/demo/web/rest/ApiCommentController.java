package com.example.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.SecurityUser;
import com.example.demo.service.PostService;
import com.example.demo.web.request.CommentRequest;
import com.example.demo.web.response.ApiResponse;
import com.example.demo.web.response.CommentResponse;
import com.example.demo.web.response.ResponseEntityUtils;

@RestController
@RequestMapping("/api/comments")
public class ApiCommentController {
	
	@Autowired
	private PostService postService;

	/*
	 * 요청 방식 : GET
	 * 요청 URI : /api/comments
	 * 검색 옵션 : ?postNo=64
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<CommentResponse>>> getComments(
			@RequestParam("postNo") int postNo) {
		List<CommentResponse> data = postService.getComments(postNo);
		
		return ResponseEntityUtils.ok(data);
	}
	
	/*
	 * 요청 방식 : DELETE
	 * 요청 URI : /api/comments/23
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/{commentNo}")
	public ResponseEntity<ApiResponse<Void>> deleteComment(
			@PathVariable("commentNo") int commentNo,
			@AuthenticationPrincipal SecurityUser securityUser) {

		postService.deleteComment(commentNo, securityUser.getUser().getUserNo());
		
		return ResponseEntityUtils.ok("댓글이 삭제되었습니다.");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<ApiResponse<CommentResponse>> createComment(
			@RequestBody CommentRequest request,
			@AuthenticationPrincipal SecurityUser securityUser) {
		
		CommentResponse data 
			= postService.createComment(request, securityUser.getUser().getUserNo());
		
		return ResponseEntityUtils.ok(data);
	}
}











