package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @Controller
 * - 이 클래스가 클라이언트의 HTTP 요청을 처리하는 클래스임을 나타낸다.
 * - 스캔해서 자동으로 스프링 컨테이너의 빈으로 등록된다.
 * - 웹 페이지를 응답으로 제공하는 요청 핸들러 메소드가 정의되는 클래스다.
 *   (@Controller 어노테이션이 선언된 컨트롤러 클래스의 요청핸들러 메소드는 언제나 뷰페이지의 이름을 반환한다.)
 */
@Controller
public class HomeController {
	
	/*
	 * 요청핸들러 메소드
	 * - 매핑된 URL 요청이 접수되면 실행되는 메소드다.
	 * - 이 메소드는 HTTP 요청을 처리하고, View 페이지에 전달할 데이터를 담고
	 *   데이터를 표현할 View 페이지의 이름을 반환한다.
	 */
	@GetMapping("/home")
	public String index(Model model) {
		
		model.addAttribute("msg", "안녕하세요");
		
		return "index";
	}

}
