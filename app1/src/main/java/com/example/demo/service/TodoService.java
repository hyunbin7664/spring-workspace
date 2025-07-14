package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoCreateRequest;
import com.example.demo.dto.TodoUpdateRequest;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.vo.Category;
import com.example.demo.vo.Todo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final CategoryMapper categoryMapper;
	private final TodoMapper todoMapper;
	
	private final ModelMapper modelMapper;
	
	public Todo getTodo(int userNo, int todoNo) {
		Todo todo = todoMapper.getTodoByNo(todoNo);
		if (todo == null) {
			throw new RuntimeException("일정번호에 해당하는 일정정보가 없습니다.");
		}
		if (todo.getUserNo() != userNo) {
			throw new RuntimeException("다른 사람의 일정은 수정할 수 없습니다.");
		}
		return todo;
	}
	
	/**
	 * 모든 일정 정보를 반환한다.
	 * @return 일정 목록
	 */
	public List<Todo> getTodos() {
		return todoMapper.getTodos();
	}
	
	/**
	 * 일정 번호를 전달받아서 일정 상세정보를 반환한다.
	 * @param todoNo 일정번호
	 * @return 일정 상세 정보
	 */
	public Todo getTodoDetail(int todoNo) {
		return todoMapper.getTodoByNo(todoNo);
	}

	/**
	 * 모든 일정 카테고리 정보를 조회해서 반환한다.
	 * @return 카테고리 정보 목록
	 */
	public List<Category> getCategories() {
		return categoryMapper.getAllCategories();
	}

	/**
	 * 사용자번호, 새 일정정보를 전달받아서 신규 일정을 등록한다.
	 * @param userNo 사용자 번호
	 * @param request 신규 일정정보
	 */
	public void register(int userNo, TodoCreateRequest request) {
		// TodoCreateRequest -> Todo
		Todo todo = modelMapper.map(request, Todo.class);
		// 일정정보에 등록요청한 사용자번호를 저장한다.
		todo.setUserNo(userNo);
		
		// 일정 등록 요청을 한다.
		todoMapper.insertTodo(todo);		
	}

	/**
	 * 사용자번호, 일정번호를 전달받아서 해당 일정정보의 상태를 처리완료로 변경한다.
	 * @param userNo 일정 번호
	 * @param todoNo 사용자 번호
	 */
	public void completeTodo(int userNo, int todoNo) {
		// 일정번호로 일정정보를 조회한다.
		Todo todo = todoMapper.getTodoByNo(todoNo);
		// 일정을 작성한 사용자번호와 처리완료 요청을 한 사용자번호가 일치하지 않으면
		// 예외를 발생시킨다.
		if (todo.getUserNo() != userNo) {
			throw new RuntimeException("다른 사용자의 일정은 변경할 수 없습니다.");
		}
		
		// 일정정보를 처리완료 상태로 변경하는데 필요한 정보를 담는 Todo객체를 생성한다.
		// 일정번호, 상태, 처리완료 일자를 저장한다.
		Todo updatedTodo = new Todo();
		updatedTodo.setTodoNo(todoNo);
		updatedTodo.setStatus("처리완료");
		updatedTodo.setCompleteDate(new Date());
		
		// 처리완료 상태로 변경하는데 필요한 정보가 저장된 Todo객체를
		// 전달해서 변경시킨다.
		todoMapper.updateTodo(updatedTodo);
	}

	/**
	 * 사용자번호, 일정번호를 전달받아서 해당 일정정보를 삭제하는 업무로직을 수행한다.
	 * @param userNo 사용자 번호
	 * @param todoNo 삭제할 일정번호
	 */
	public void removeTodo(int userNo, int todoNo) {
		// 일정번호를 일정 정보를 조회한다.
		Todo todo = todoMapper.getTodoByNo(todoNo);
		// 일정을 작성한 사용자번호와 삭제 요청을 한 사용자번호가 일치하지 않으면
		// 예외를 발생시킨다.
		if (todo.getUserNo() != userNo) {
			throw new RuntimeException("다른 사용자의 일정은 삭제할 수 없습니다.");
		}
		
		// 일정정보를 삭제한다.
		todoMapper.deleteTodo(todoNo);
	}
	
	public void updateTodo(int userNo, TodoUpdateRequest request) {
		Todo todo = todoMapper.getTodoByNo(request.getTodoNo());
		if (todo == null) {
			throw new RuntimeException("일정정보가 존재하지 않습니다.");
		}
		if (todo.getUserNo() != userNo) {
			throw new RuntimeException("다른 사람의 일정정보는 수정할 수 없습니다.");
		}
		if ("처리완료".equals(todo.getStatus())) {
			throw new RuntimeException("처리완료된 일정은 수정할 수 없습니다.");
		}
		
		Todo updatedTodo = modelMapper.map(request, Todo.class);
		todoMapper.updateTodo(updatedTodo);
		
		
	}
	
}
