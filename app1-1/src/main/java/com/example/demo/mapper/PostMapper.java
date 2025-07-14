package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Post;

@Mapper
public interface PostMapper {
	/**
	 * 새 게시글을 전달받아서 테이블에 저장한다.
	 * @param post 새 게시글 정보
	 */
	void insertPost(Post post);
	
	/**
	 * 전체 게시글 개수를 조회해 반환한다.
	 * @return 전체 게시글 개수
	 */
	int totalRows();
	
	/**
	 * offset과 rows를 전달받아서 해당 범위의 게시글 목록을 조회해 반환한다.
	 * @param offset 오프셋
	 * @param rows 행의 개수
	 * @return 게시글 목록
	 */
	List<Post> getPosts(@Param("offset") int offset, @Param("rows") int rows);

	/**
	 * 모든 게시글 정보를 조회해서 반환한다.
	 * @return 전체 게시글 목록
	 */
	List<Post> getAllPosts();
	
	/**
	 * 게시글 번호에 해당하는 게시글 정보를 조회해서 반환한다.
	 * @param postNo 게시글 번호
	 * @return 게시글 정보
	 */
	Post getPostByPostNo(int postNo);
}
