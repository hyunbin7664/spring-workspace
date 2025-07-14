package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Todo;

@Mapper
public interface TodoMapper {
	
	/**
	 * 모든 일정 정보를 조회해서 반환한다.
	 * @return 전체 일정 목록
	 */
	List<Todo> getAllTodos();
	
	/**
	 * 일정 번호에 해당하는 일정 정보를 조회해서 반환한다.
	 * @param todoNo 일정 번호
	 * @return 일정 정보
	 */
	Todo getTodo(int todoNo);
	
	/**
	 * 새 일정 정보를 전달받아서 테이블에 저장한다.
	 * @param todo 새 일정 정보
	 */
	void insertTodo(Todo todo);
	
}
