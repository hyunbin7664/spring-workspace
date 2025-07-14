package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.exception.UserRegisterException;
import com.example.demo.service.UserService;
import com.example.demo.web.form.UserRegisterForm;

import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}

	@GetMapping("/register")
	public String registerform(Model model) {
		UserRegisterForm userRegisterForm = new UserRegisterForm();
		model.addAttribute("userRegisterForm", userRegisterForm);
		
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("userRegisterForm") UserRegisterForm userRegisterForm, 
			BindingResult errors) {
		if (errors.hasErrors()) {
			// 유효성 검증 실패시 회원가입 폼으로 내부이동한다.
			return "user/register";
		}
		try {
			userService.registerUser(userRegisterForm);
			
		} catch (UserRegisterException ex) {
			String field = ex.getField();
			String message = ex.getMessage();
			errors.rejectValue(field, null, message);
			
			return "user/register";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginform() {
		return "user/login";
	}
}















