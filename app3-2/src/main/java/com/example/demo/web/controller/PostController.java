package com.example.demo.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.Page;
import com.example.demo.security.SecurityUser;
import com.example.demo.service.PostService;
import com.example.demo.vo.Post;
import com.example.demo.vo.User;
import com.example.demo.web.form.PostCreateForm;
import com.example.demo.web.form.PostUpdateForm;
import com.example.demo.web.view.FileDownloadView;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private FileDownloadView fileDownloadView;
	
	@Autowired
	private PostService postService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update")
	public String modifyform(@RequestParam("postNo") int postNo,
			Model model) {
		
		Post post = postService.getPost(postNo);
		model.addAttribute("post", post);
		
		return "posts/modify";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update")
	public String modify(@ModelAttribute PostUpdateForm form,
			@AuthenticationPrincipal SecurityUser securityUser,
			RedirectAttributes redirectAttributes) {
		
		postService.updatePost(form, securityUser.getUser().getUserNo());
		
		redirectAttributes.addAttribute("no", form.getPostNo());
		//redirectAttributes.addAttribute("page", 1);
		//redirectAttributes.addAttribute("rows", 10);
		//redirectAttributes.addAttribute("sort", "date");
		//redirectAttributes.addAttribute("opt", "title");
		//redirectAttributes.addAttribute("keyword", "자바");
		
		return "redirect:/post/detail"; 
		//return "redirect:/posts/detail?no=" + form.getPostNo();
	}
	
	@GetMapping("/download")
	public ModelAndView download(@RequestParam("no") int postNo) {
		File file = postService.getDownloadFile(postNo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("file", file);
		mav.setView(fileDownloadView);
		
		return mav;
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int postNo,
			Model model) {
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
	
	/*
	 * 요청 방식: GET
	 * 요청 URL: 
	 * 	/posts/list
	 *	/posts/list?page=1
	 * 	/posts/list?page=1&opt=title&keyword=연습
	 */
	@GetMapping("/list")
	public String list(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			@RequestParam(name = "opt", required = false) String opt, 
			@RequestParam(name = "keyword", required = false) String keyword, 
			Model model) {
		
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
			condition.put("opt", opt);
			condition.put("keyword", keyword);
		}
		
		Page<Post> page = postService.getPosts(currentPage, condition);
		model.addAttribute("page", page);
		
		return "posts/list";
	}
}
