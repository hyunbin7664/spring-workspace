package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.AppException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.vo.Post;
import com.example.demo.web.form.PostCreateForm;

@Service
public class PostService {
	
	@Value("${app.file.save-directory}")
	private String saveDirectory;

	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public File getDownloadFile(int postNo) {
		Post post = postMapper.getPostByPostNo(postNo);
		String filename = post.getFilename();
		
		File file = new File(saveDirectory, filename);
		if (!file.exists()) {
			throw new AppException("파일이 존재하지 않습니다.");
		}
		
		return file;
	}
	
	public Post getPost(int postNo) {
		return postMapper.getPostByPostNo(postNo);
	}
	
	public List<Post> getPosts() {
		return postMapper.getPosts();
	}
	
	public void createPost(PostCreateForm form, int userNo) {
		// PostCreateForm -> Post로 복사한다.
		// title, content가 Post에 복사된다.
		Post post = modelMapper.map(form, Post.class);
		post.setUserNo(userNo);
		
		// 첨부파일 업로드
		MultipartFile upfile = form.getUpfile();
		if (!upfile.isEmpty()) {
			try {
			// 첨부파일명을 조회하고, Post객체에 담는다.
			String originalFilename = upfile.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + originalFilename;
			post.setFilename(filename);
			
			// 첨부파일을 지정된 디렉토리에 저장시킨다.
			File dest = new File(saveDirectory, filename);
			upfile.transferTo(dest);
			} catch (IOException ex) {
				throw new AppException("첨부파일 저장 중 오류가 발생하였습니다.", ex);
			}
		}
		
		// 게시글 정보를 저장시킨다.
		postMapper.insertPost(post);
	}
}
