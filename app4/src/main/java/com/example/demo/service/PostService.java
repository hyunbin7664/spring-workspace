package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PostMapper;
import com.example.demo.vo.Post;

@Service
public class PostService {

   @Autowired
   private PostMapper postMapper;
   
   public List<Post> getPosts() {
      return postMapper.getAllPosts();
   }
   
   public Post getPost(int postNo) {
      return postMapper.getPostByPostNo(postNo);
   }
   
   public void createPost(Post post) {
      postMapper.insertPost(post);
   }
   
   public void deletePost(int postNo) {
      postMapper.deletePost(postNo);
   }
}

