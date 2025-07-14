package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public String hello() {
		
		return "Hello, Spring Boot Application";
	}
	
	@GetMapping("/books")
	public Book book() {
		Book book = new Book();
		book.setTitle("이것이 자바다");
		book.setAuthor("신용권");
		book.setPrice(35000);
		
		return book;
	}
}
