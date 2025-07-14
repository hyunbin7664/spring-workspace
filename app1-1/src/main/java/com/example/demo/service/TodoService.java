package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoCreateRequest;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.vo.Todo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final CategoryMapper categoryMapper;
	private final TodoMapper todoMapper;
	private final ModelMapper modelMapper;
	
	/**
	 * 전체 일정 목록 정보를 조회해서 반환하는 업무로직을 수행한다.
	 * @return 전체 일정 목록
	 */
	public List<Todo> getTodos() {
		return todoMapper.getAllTodos();
    }
	
	/**
	 * 지정된 일정 번호에 해당하는 일정 정보를 조회해서 반환하는 업무로직을 수행한다.
	 * @param todoNo 일정 번호
	 * @return 일정 정보
	 */
	public Todo getTodo(int todoNo) {
		return todoMapper.getTodo(todoNo);
	}
	
	public void registerTodo(int userNo, TodoCreateRequest request) {
        // TodoCreateRequest ---> Todo
		Todo todo = modelMapper.map(request, Todo.class);
		todo.setUserNo(userNo);

        // TodoMapper의 insertTodo(Todo todo) 실행
		todoMapper.insertTodo(todo);
        
     }

}
