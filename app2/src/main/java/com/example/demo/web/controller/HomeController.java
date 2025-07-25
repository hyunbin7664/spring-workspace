package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exception.UserRegisterException;
import com.example.demo.service.UserService;
import com.example.demo.vo.User;
import com.example.demo.web.form.UserRegisterForm;

import jakarta.validation.Valid;

@Controller
@SessionAttributes({"userRegisterForm"})
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
	
//	@GetMapping("/register")
//	public String registerform(Model model) {
//		UserRegisterForm userRegisterForm = new UserRegisterForm();
//		model.addAttribute("userRegisterForm", userRegisterForm);
//		
//		return "user/register";
//	}

	/*
	 * @Valid
	 * - 폼 입력값 유효성 검증을 활성화하는 어노테이션이다.
	 * 
	 * @ModelAttribute
	 * - UserRegisterForm가 폼데이터(폼입력값)이 바인딩될 객체임을 나타낸다.
	 * - UserRegisterForm객체를 Model객체에 자동으로 저장한다.
	 *   이름을 지정하지 않으면 "userRegisterForm"을 이름으로
	 *                    UserRegisterForm객체를 값으로 저장한다.
	 *   즉, Model객체에 폼입력값이 바인딩된 UserRegisterForm객체가
	 *   등록되어 있다.
	 *   따라서, 유효성 검증을 통과하지 못해서 다시 회원가입화면으로 되돌아갔을 때
	 *   이미 사용자가 입력한 값을 표시할 수 있게 된다.
	 * 
	 * BindingResult
	 * - 폼입력값 유효성 검증 결과가 저장되는 객체다.
	 * - 반드시 폼입력값이 바인딩되는 객체 바로 뒤에 위치해야 한다.
	 * - 유효성 검증을 통과하지 못한 입력필드 마다 FieldError객체를
	 *   생성해서 추가한다.
	 * - hasErrors() 메소드는 등록된 FieldError객체가 하나라도 있으면
	 *   true를 반환한다.
	 */
//	@PostMapping("/register")
//	public String register(@Valid @ModelAttribute UserRegisterForm userRegisterForm, BindingResult errors, RedirectAttributes redirectAttributes) {
//		if (errors.hasErrors()) {
//			// 유효성 검증 실패시 회원가입 폼으로 내부이동한다.
//			return "user/register";
//		}
//		try {
//			User user = userService.registerUser(userRegisterForm);
//			redirectAttributes.addFlashAttribute("user", user);
//		} catch (UserRegisterException ex) {
//			/*
//			 * BindingResult객체에 FieldErrror 추가하자.
//			 * 
//			 * BindingResult의 .rejectValue(필드명, 에러코드, 에러메세지)는
//			 * 필드명, 에러코드, 에러메세지를 전달받아서 FieldError객체를 생성하고
//			 * BindingResult에 추가한다.
//			 */
//			String field = ex.getField();
//			String message = ex.getMessage();
//			errors.rejectValue(field, null, message);
//			
//			return "user/register";
//		}
//		
//		return "redirect:/complete";
//	}
// -----------------------------------------------------------------------------
	@GetMapping("/register-1")
	public String register1(Model model) {
		UserRegisterForm form  = new UserRegisterForm();
		model.addAttribute("userRegisterForm", form);
		
		return "user/register-1";
	}
	
	
	@PostMapping("/register-2")
	public String register2(@ModelAttribute("userRegisterForm") UserRegisterForm form) {
		// 1단계 입력폼의 값을 전부 전달받아서 UserRegisterForm에 저장한다.
		return "user/register-2";
	}
	
	@PostMapping("/register-3")
	public String register3(@ModelAttribute("userRegisterForm") UserRegisterForm form
							, RedirectAttributes redirectAttributes
							, SessionStatus sessionStatus) {
		// 2단계 입력폼의 값을 전부 전달받아서 UserRegisterForm에 저장한다.
		User user = userService.registerUser(form);
		redirectAttributes.addFlashAttribute("user", user);
		
		// 최종 단계가 완료되면 @SessionAttributes로 세션에 저장한 것을 전부 삭제한다.
		sessionStatus.setComplete();
		
		return "redirect:/complete";
	}
	
	@GetMapping("/complete")
	public String complete() {
		return "user/complete";
	}
	
	@GetMapping("/login")
	public String loginform() {
		return "user/login";
	}
}















