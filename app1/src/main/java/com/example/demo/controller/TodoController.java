package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.TodoCreateRequest;
import com.example.demo.dto.TodoUpdateRequest;
import com.example.demo.exception.AppException;
import com.example.demo.service.TodoService;
import com.example.demo.vo.Category;
import com.example.demo.vo.Todo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/update")
	public String edit(@RequestParam("no") int todoNo, Model model) {
		int userNo = 1;
		Todo todo = todoService.getTodo(userNo, todoNo);
		model.addAttribute("todo", todo);
		
		return "todos/edit";
	}
	
	@PostMapping("/update")
	public String update(TodoUpdateRequest request) {
		int userNo = 1;
		todoService.updateTodo(userNo, request);
		
		return "redirect:/todos/detail?no=" + request.getTodoNo();
	}

	/*
	 * 일정등록화면, 일정수정화면에 필요한 카테고리 목록을 Model객체에 저장시킨다.
	 * @ModelAttribute를 메소드에 선언하면,
	 * 해당 메소드가 반환하는 객체가 지정된 이름으로 Model객체에 저장된다.  
	 */
	@ModelAttribute("categories")
	public List<Category> categories() {
		return todoService.getCategories();
	}
	
	/*
	 * 역할
	 * 	- 일정 등록 화면 요청을 처리한다.
	 * 요청 방식
	 * 	- GET
	 * 요청 URL
	 * 	- /todos/register
	 * 뷰이름
	 * 	- todos/register
	 *  - register.jsp로 내부이동해서 등록화면을 응답으로 보낸다.
	 */
	@GetMapping("/register")
	public String form() {
		return "todos/register";
	}
	
	/*
	 * 역할
	 * 	- 신규 일정정보를 폼 데이터로 전달받아서 일정 등록 요청을 처리한다.
	 * 요청 방식
	 * 	- POST
	 * 요청 URL
	 * 	- /todos/register
	 * 요청 파라미터
	 * 	- catNo, title, content, dueDate
	 * 뷰이름
	 * 	- redirect:/todos/list
	 *  - 일정 목록을 재요청하는 URL을 응답으로 보낸다. 
	 */
	@PostMapping("/register")
	public String register(TodoCreateRequest request) {
		int userNo = 1;
		todoService.register(userNo, request);
		
		return "redirect:/todos/list";
	}
	
	/*
	 * 역할
	 * 	- 일정 목록 요청을 처리한다.
	 *  - 전체 일정목록을 조회해서 Model에 "todos"로 담고, 
	 *    list.jsp에서 표현한다.
	 * 요청 방식
	 * 	- GET
	 * 요청 URL
	 * 	- /todos/list
	 * 뷰이름
	 * 	- todos/list
	 *  - 
	 */
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("todos", todoService.getTodos());
		
		int x = 1;
		if (x==1) {
			throw new AppException("목록 조회 중 오류 발생");
		}
		
		return "todos/list";
	}
	
	/*
	 * 역할
	 * 	- 일정 상세정보 요청을 처리한다.
	 * 	- 요청 파라미터로 전달받은 일정번호에 해당하는 일정정보를 조회하고,
	 *    Model객체에 "todo"라는 이름으로 저장한 다음
	 *    detail.jsp에서 표현한다.
	 * 요청 방식
	 * 	- GET
	 * 요청 URL
	 * 	- /todos/detail
	 * 요청 파라미터
	 * 	- no
	 * 뷰 이름
	 * 	- todos/detail
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int todoNo, Model model) {
		Todo todo = todoService.getTodoDetail(todoNo);
		model.addAttribute("todo", todo);
		
		return "todos/detail";
	}
	
	/*
	 * 역할
	 * 	- 일정을 처리완료로 변경하는 요청을 처리한다.
	 * 	- 일정번호를 전달받아서 해당 일정의 상태로 "처리완료"로 변경하고,
	 *    일정 상세정보를 재요청하는 URL을 응답으로 보낸다.
	 * 요청 방식
	 * 	- GET
	 * 요청 URL
	 *  - /todos/complete
	 * 요청 파라미터
	 * 	- no
	 * 뷰 이름
	 * 	- redirect:/todos/detail?no=일정번호
	 */
	@GetMapping("/complete")
	public String complete(@RequestParam("no") int todoNo) {
		int userNo = 1;
		todoService.completeTodo(userNo, todoNo);
		
		return "redirect:/todos/detail?no=" + todoNo;
	}
	
	/*
	 * 역할
	 * 	- 일정정보 삭제 요청을 처리한다.
	 *  - 일정번호를 전달받아서 해당 일정정보를 삭제하고,
	 *    일정 목록을 재요청하는 URL을 응답으로 보낸다.
	 * 요청 방식
	 * 	- GET
	 * 요청 URL
	 * 	- todos/remove
	 * 요청 파라미터
	 * 	- no
	 * 뷰 이름
	 * 	- redirect:/todos/list
	 */
	@GetMapping("/remove")
	public String remove(@RequestParam("no") int todoNo) {
		int userNo = 1;
		todoService.removeTodo(userNo, todoNo);
		
		return "redirect:/todos/list";
	}

}
