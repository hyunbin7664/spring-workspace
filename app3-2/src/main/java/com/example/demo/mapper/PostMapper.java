package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {

	int getTotalRows(Map<String, Object> condition);
	List<Post> getPosts(Map<String, Object> condition);
	void insertPost(Post post);
	Post getPostByPostNo(int postNo);
	void updatePost(Post post);
}
