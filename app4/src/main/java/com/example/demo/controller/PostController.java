package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PostService;
import com.example.demo.vo.Post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "게시글 관리 API",
	description = "게시글 전체 조회, 개별 조회, 추가, 수정, 삭제 기능을 제공한다.")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	@Operation(summary = "전체 게시글 조회")
	public ResponseEntity<List<Post>>  getPosts() {
		return ResponseEntity
				.ok()
				.body(postService.getPosts());
	}
	
	@GetMapping("/{postNo}")
	@Operation(summary = "특정 게시글 조회")
	public ResponseEntity<Post> getPost(@PathVariable int postNo) {
		return ResponseEntity
				.ok()
				.body(postService.getPost(postNo));
	}
	
	@PostMapping
	@Operation(summary = "신규 게시글 추가")
	public ResponseEntity<Void> createPost(@RequestBody Post post) {
		post.setUserNo(1);
		postService.createPost(post);
		
		return ResponseEntity
				.status(201)
				.build();
	}
	
	@DeleteMapping("/{postNo}")
	@Operation(summary = "특정 게시글 삭제")
	public ResponseEntity<Void> deletePost(@PathVariable int postNo) {
		postService.deletePost(postNo);
		
		return ResponseEntity
				.noContent()
				.build();
	}
}
