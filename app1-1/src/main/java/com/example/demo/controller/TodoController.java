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
import com.example.demo.service.TodoService;
import com.example.demo.vo.Todo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	/*
	 * 요청 방식 :  GET
	 * 요청 URL : /todos/list
	 * view 이름 : todos/list
	 */
	@GetMapping("/list")
    public String list(Model model) {
       List<Todo> todos = todoService.getTodos();
       
       model.addAttribute("todos", todos);
		
       return "todos/list";
    }
	
	/*
	 * 요청 방식 :  GET
	 * 요청 URL : /todos/detail?no=일정번호
	 * view 이름 : todos/detail
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int no, Model model) {
		Todo todo = todoService.getTodo(no);
		model.addAttribute("todo", todo);
		
		return "todos/detail";
	}
	
	@GetMapping("/register")
    public String form(Model model) {
    // 카테고리정보를 조회해서 Model에 담는다.
		model.addAttribute("todo", new TodoCreateRequest());
 
    return "todos/register";
    }
	
	@PostMapping("/register")
    public String register(@ModelAttribute("todo") TodoCreateRequest request) {
       // TodoService의 registerTodo(int userNo, TodoCreateRequest request)실행
       // 사용자번호는 1번으로 정한다.
		int userNo = 1;
		todoService.registerTodo(userNo, request);

       return "redirect:/todos/list";
    }
}
