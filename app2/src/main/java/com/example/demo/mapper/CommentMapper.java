package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Comment;

@Mapper
public interface CommentMapper {

	Comment getCommentByCommentNo(int commentNo);

	void insertComment(Comment comment);

	List<Comment> getCommentsByPostNo(int postNo);

	void deleteCommentByCommentNo(int commentNo);
}
