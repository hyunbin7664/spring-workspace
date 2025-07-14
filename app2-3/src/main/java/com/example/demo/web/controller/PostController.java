package com.example.demo.web.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.security.SecurityUser;
import com.example.demo.service.PostService;
import com.example.demo.vo.User;
import com.example.demo.web.form.PostCreateForm;
import com.example.demo.web.view.FileDownloadView;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private FileDownloadView fileDownloadView;

	@Autowired
	private PostService postService;
	
	@GetMapping("/download")
	public ModelAndView download(@RequestParam("no") int postNo) {
		File file = postService.getDownloadFile(postNo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("file", file);
		mav.setView(fileDownloadView);
		
		return mav;
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int postNo, Model model) {
		model.addAttribute("post", postService.getPost(postNo));
		
		return "posts/detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String form() {
		return "posts/form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(PostCreateForm form, 
							@AuthenticationPrincipal SecurityUser securityUser) {
		User user = securityUser.getUser();
		postService.createPost(form, user.getUserNo());
		
		return "redirect:/posts/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("posts", postService.getPosts());
		
		return "posts/list";
	}
}
