package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Todo;

@Mapper
public interface TodoMapper {
	/**
	 * 모든 일정정보를 조회해서 반환한다.
	 * @return 일정 정보 목록
	 */
	List<Todo> getTodos();
	
	/**
	 * 지정된 일정번호의 일정정보를 조회해서 반환한다.
	 * @param todoNo 일정번호
	 * @return 일정 정보
	 */
	Todo getTodoByNo(int todoNo);

	/**
	 * 새 일정정보를 전달받아서 저장시킨다.
	 * @param todo 새 일정정보
	 */
	void insertTodo(Todo todo);

	/**
	 * 변경된 일정정보를 전달받아서 반영시킨다.
	 * @param todo 변경된 일정정보
	 */
	void updateTodo(Todo todo);

	/**
	 * 지정된 일정번호의 일정정보를 삭제한다.
	 * @param todoNo 일정번호
	 */
	void deleteTodo(int todoNo);
}
