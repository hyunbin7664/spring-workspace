package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponseDto;
import com.example.demo.dto.ListDto;
import com.example.demo.dto.Pagination;
import com.example.demo.dto.PostCreateRequestDto;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final CommentMapper commentMapper;
	private final PostMapper postMapper;
	private final ModelMapper modelMapper;
	
	/**
	 * 작성자 번호, 신규 게시글 정보를 전달받아서 등록시키는 업무로직을 수행한다.
	 * @param userNo 작성자 번호
	 * @param dto 신규 게시글 정보
	 */
	public void create(int userNo, PostCreateRequestDto dto) {
		/*
		 * PostCreateRequestDto 객체의 값을 Post 객체를 생성해 값을 복사했다.
		 */
		Post post =  modelMapper.map(dto, Post.class);
		post.setUserNo(userNo);
		
		postMapper.insertPost(post);
	}
	
	/**
	 * 페이지번호를 전달받아서 목록정보를 반환하는 업무로직을 수행한다.
	 * @param page 페이지번호
	 * @return 목록정보(게시글 목록, 검색조건, 페이징 정보)
	 */
	public ListDto<Post> getPosts(int page) {
		ListDto<Post> dto = new ListDto<Post>();
		
		int totalRows = postMapper.totalRows();
		Pagination pagination = new Pagination(page, totalRows, 10);
		
		int offset = pagination.getOffset();
		int rows = pagination.getRows();
		
		List<Post> posts = postMapper.getPosts(offset, rows);
		
		dto.setItems(posts);
		dto.setPagination(pagination);
			
		return dto;
	}
	
	/**
	 * 지정된 게시글 번호에 해당하는 게시글 정보를 조회해서 반환하는 업무로직을 수행한다.
	 * @param postNo 게시글 번호
	 * @return 게시글 정보
	 */
	public Post getPost(int postNo) {
		return postMapper.getPostByPostNo(postNo);
	}
	

	/**
	 * 전체 게시글 정보를 조회해서 반환하는 업무로직을 수행한다.
	 * @return 전체 게시글 목록
	 */
	public List<Post> getAllPosts() {
		return postMapper.getAllPosts();
	}
	
	/**
	 * 지정된 게시글의 댓글을 조회해서 반환하는 업무로직을 수행한다.
	 * @param postNo 게시글 번호
	 * @return 댓글 목록
	 */
	public List<CommentResponseDto> getComments(int postNo) {
	      List<Comment> comments = commentMapper.getCommentsByPostNo(postNo);
	      
	      return comments.stream()
	            .map(c -> new CommentResponseDto(c))
	            .toList();
	   }
	
	public CommentResponseDto createComment(int userNo, CommentCreateRequest request) {
		
		// CommentCreateRequest --> Comment
		Comment comment = modelMapper.map(request, Comment.class);
		comment.setUserNo(userNo);
		
		// Comment 객체를 전달해서 저장시킨다.
		// {commentNo:0, postNo:73, userNo:1, content:내용입니다.}
		commentMapper.insertComment(comment);
		// {commentNo:6, postNo:73, userNo:1, content:내용입니다.}
		
		// 새로 저장된 Comment 객체를 조회한다.
		Comment savedComment = commentMapper.getCommentByCommentNo(comment.getCommentNo());
		
		// Comment --> CommentResponseDto
		CommentResponseDto dto = new CommentResponseDto(savedComment);
		
		return dto;
	}
	
}
