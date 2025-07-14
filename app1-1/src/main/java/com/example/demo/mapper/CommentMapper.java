package com.example.demo.mapper;

import java.util.List;

import com.example.demo.vo.Comment;

public interface CommentMapper {
	
	/**
	 * 지정된 댓글 번호를 전달받아서 댓글 정보를 반환한다.
	 * @param commentNo 댓글번호
	 * @return 댓글 정보
	 */
	Comment getCommentByCommentNo(int commentNo);

	/**
	 * 새 댓글 정보를 전달받아서 테이블에 저장시킨다.
	 * @param comment
	 */
	void insertComment(Comment comment);
	
	/**
	 * 지정된 게시글에 등록된 댓글을 조회해서 반환한다.
	 * @param postNo 게시글 번호
	 * @return 댓글 목록
	 */
	List<Comment> getCommentsByPostNo(int postNo);
}
