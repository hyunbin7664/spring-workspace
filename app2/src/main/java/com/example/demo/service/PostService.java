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
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Post;
import com.example.demo.web.form.PostCreateForm;
import com.example.demo.web.form.PostUpdateForm;
import com.example.demo.web.request.CommentRequest;
import com.example.demo.web.response.CommentResponse;

@Service
public class PostService {

	@Value("${app.file.save-directory}")
	private String saveDirectory;
	
	@Autowired
	private PostMapper postMapper;

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * 코딩 내용
	 * 1. Comment 객체에 게시글번호, 댓글내용, 사용자번호를 담는다.
	 * 2. Comment 객체를 Mapper에 전달해서 저장시키고, 댓글번호를 발급받는다.
	 * 3. 댓글번호로 댓글 상세정보를 조회한다.
	 * 4. 조회된 댓글정보를 CommentResponse에 담는다.
	 * 5. CommentResponse를 반환한다.
	 */
	public CommentResponse createComment(CommentRequest request, int userNo) {
		Comment comment = modelMapper.map(request, Comment.class);
		comment.setUserNo(userNo);
		
		commentMapper.insertComment(comment);
		
		int commentNo = comment.getCommentNo();
		Comment savedComment = commentMapper.getCommentByCommentNo(commentNo);
		
		CommentResponse commentResponse = new CommentResponse(savedComment);
		
		return commentResponse;
	}
	
	public void deleteComment(int commentNo, int userNo) {
		Comment comment = commentMapper.getCommentByCommentNo(commentNo);
		if (comment.getUserNo() != userNo) {
			throw new AppException("다른 사람이 작성한 댓글은 삭제할 수 없습니다.");
		}
		
		commentMapper.deleteCommentByCommentNo(commentNo);
	}
	
	public List<CommentResponse> getComments(int postNo) {
		List<Comment> comments = commentMapper.getCommentsByPostNo(postNo);
		
		return comments.stream()
						.map(comment -> new CommentResponse(comment))
						.toList();
//		어차피 반환값이 같으므로 위와 같이 작성해도됨
// 1.	List<CommentResponse> commentResponseList = comments.stream()
//				.map(comment -> new CommentResponse(comment))
//				.toList();
//		return commentResponseList;
// 2.	List<CommentResponse> list = new ArrayList<CommentResponse>();
//		for(Comment comment : comments) {
//			CommentResponse response = new CommentResponse(comment);
//			list.add(response);
//		}
//		return list;
	}
	
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
	
	/*
	 * 게시글 수정작업
	 * 1. 게시글 번호로 게시글 정보를 조회한다.
	 * 2. 조회된 게시글의 작성자 번호와 현재 로그인한 사용자번호를 비교해서
	 *    일치하지 않으면 예외를 던진다.
	 * 3. 조회된 게시글 정보에 PostUpdateForm의 정보를 복사한다.
	 * 4. PostUpdateForm에 첨부파일이 포함되어 있으면, 첨부파일을 저장하고, 첨부파일명을 게시글정보에 추가한다.
	 * 5. 변경된 게시글 정보를 전달해서 변경시킨다.
	 */
	public void updatePost(PostUpdateForm form, int userNo) {
		Post savedPost = postMapper.getPostByPostNo(form.getPostNo());
		if (savedPost == null) {
			throw new AppException("게시글 정보가 존재하지 않습니다.");
		}
		if (savedPost.getUserNo() != userNo) {
			throw new AppException("다른 사용자의 게시글은 수정할 수 없습니다.");
		}
		
		// PostUpdateForm의 postNo, title, content를 Post로 복사한다.
		Post post = modelMapper.map(form, Post.class);

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
		/*
		 * post <-- postNo=xx, title=xx, content=xx, filename=null
		 * post <-- postNo=xx, title=xx, content=xx, filename=xx
		 */
		
		postMapper.updatePost(post);
	}
}













