package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {

	List<Post> getAllPosts();
	Post getPostByPostNo(int postNo);
	void insertPost(Post post);
	void updatePost(Post post);
	void deletePost(int postNo);
}
