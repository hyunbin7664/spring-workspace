package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {

	List<Post> getPosts();
	void insertPost(Post post);
	Post getPostByPostNo(int postNo);
	void updatePost(Post post);
	
}
