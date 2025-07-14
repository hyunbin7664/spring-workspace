package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ListDto;
import com.example.demo.dto.PostCreateRequestDto;
import com.example.demo.service.PostService;
import com.example.demo.vo.Post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	/*
	 * 요청 방식: GET
	 * 요청 URL: /posts/create
	 * 역할: 게시글 등록화면 요청을 접수받아 form.jsp로 내부 이동
	 */
	@GetMapping("/create")
	public String form(Model model) {
		model.addAttribute("dto", new PostCreateRequestDto());
		return "posts/form";
	}

	/*
	 * 요청 방식: POST
	 * 요청 URL: /posts/create
	 * 요청 파라미터
	 * 	title = 제목...
	 * 	content = 내용...
	 * 역할: 게시글 입력폼의 폼데이터를 제출받아서 게시글을 추가하고, 목록을 재요청하는 응답을 보낸다.
	 * 
	 * @ModelAttribute
	 * 	폼 입력값을 지정된 객체에 바인딩 시키고(지정된 객체의 멤버변수에 대입)
	 * 	해당 객체를 Model의 속성으로 자동 추가시킨다.
	 * 	
	 */
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("dto") PostCreateRequestDto dto, BindingResult errors) {
		
		// 유효성 검사를 통과하지 못한 경우
		// errors.hasErrors()의 실행결과는 true다.
		if (errors.hasErrors()) {
			// 입력폼으로 이동한다.
			return "posts/form";
		}
		
		// 유효성 검사를 통과한 경우
		// 게시글 정보를 추가하는 업무로직을 실행시킨다.
		int userNo = 1;
		postService.create(userNo, dto);
		
		return "redirect:/posts/list";
	}

//	@Autowired
//	private PostService postService;
	
	/*
	 * 요청/응답 정보
	 * 
	 * - 요청 URL : /posts/detail
	 * - 요청 파라미터 : no=게시글번호
	 * 				  예시
	 * 				  detail(@RequestParam("no") int postNo)
	 *				  요청파라미터 중에서 이름이 "no"인 값을 추출해서 postNo에 전달해라
	 *				  예시
	 *				  detail(@RequestParam("no") int no)
	 *				  detail(int no)
	 *				  요청파라미터 중에서 이름이 "no"인 값을 추출해서 no에 전달하라
	 *
	 * - 뷰 페이지 : /WEB-INF/views/posts/detail.jsp
	 * - 뷰 페이지에 전달할 데이터 : 이름 - "post"
	 * 						   값  - Post
	 */
//	@GetMapping("/detail")
//	public String detail(@RequestParam("no") int no, Model model) {
//		Post post = postService.getPost(no);
//		model.addAttribute("post", post);
//		
//		return "posts/detail";
//		
//	}
	
	@GetMapping("/detail/{no}")
	public String detail(@PathVariable("no") int no, Model model) {
		Post post = postService.getPost(no);
		model.addAttribute("post", post);
		
		return "posts/detail";
		
	}
	
	
	/*
	 * 요청/응답 정보
	 * - 요청 URL : 
	 * 		/posts/list
	 * 		/posts/list?page=3
	 * - 요청 파라미터 : page - 페이지번호
	 * 				  opt - 검색옵션(title, writer, content 중 하나)
	 * 				  keyword - 검색어
	 * - 뷰 페이지 : /WEB-INF/views/posts/list.jsp
	 * - 뷰 페이지에 전달하는 데이터 :  이름 - "posts"
	 * 							 값 - List<Post>
	 */
	@GetMapping("/list")
	public String posts(@RequestParam(name = "page", required = false, defaultValue = "1" ) int page,  Model model) {
		// 페이지번호를 전달받아서 해당 페이지의 게시글 목록 정보를 반환한다.
		// 게시글 목록 정보는 ListDto<Post>다.
		ListDto<Post> dto = postService.getPosts(page);
		
		/*
		 * "posts"로 List<Post> 객체를 Model에 담는다.
		 * "paging"로 Pagination 객체를 Model에 담는다.
		 */
		model.addAttribute("posts", dto.getItems());
		model.addAttribute("paging", dto.getPagination());
		
		return "posts/list";
	}
}
/*
 * @Autowired 어노테이션 없이 의존성 주입 받기
 * 
 * 의존성 주입에 final 키워드 활용
 * @Controller
 * public class PostController {
 * 	private final PostService postService;
 * 
 * 	public PostController(PostService postService) {
 * 		this.postService = postService;
 * 	}
 * }
 * 
 * 의존성 주입에 final 키워드 활용, @RequiredArgsConstructor 활용
 * @Controller
 * @RequiredArgsConstructor
 * public class PostController {
 * 	private final PostService postService;
 * 
 * }
 * 
 * DispatchServlet
 * 	1. 요청을 접수받는다.
 * 	2. HandlerMapping에게 요청을 처리할 컨트롤러를 찾게한다.
 * 	3. HandlerMapping에게 컨트롤러 실행을 위임한다.
 * 	4. ViewResolver에게 적절한 View를 찾게한다.
 * 	5. View를 실행한다.
 * 
 * --------------------------------------------------------------
 * @ModelAttribute, @RequestParam, @RequestBody
 * 
 * @ModelAttribute
 * 	폼 입력값, 요청파라미터값 객체에 바인딩 시킨다.
 * 		form 입력값 ---> 객체
 * 			* 회원가입 폼의 입력값을 UserRequestDto에 바인딩
 * 			register(@ModelAttribute UserRequestDto dto) {...}
 * 			* 게시글 등록 폼의 입력값을 PostRequestDto에 바인딩
 * 			create(@ModelAttribute PostRequestDto dto) {...}
 * 		여러 개의 요청파라미터값 ---> 객체
 * 			* page=1&rows=10&opt=title&keyword=자바&sorT=DATE
 * 			search(@ModelAttribute Condition condition) {...}
 * 	값이 바인딩된 객체를 Model 객체의 속성으로 자동으로 추가한다.
 * 		@ModelAttribute를 지정하지 않은 경우
 * 			register(UserRequestDto dto) {...}
 * 			* 스프링이 자동으로 요청파라미터값을 객체에 바인딩하고, 
 * 			  클래스명의 첫번재 글자를 소문자로 변환한 이름
 * 			  즉, "userRequestDto"라는 이름으로 Model 객체에 추가한다.
 * 		@ModelAttribute를 지정한 경우
 * 			register(@ModelAttribute UserRequestDto dto) {...}
 * 			* @ModelAttribute를 지정하지 않은 경우와 동일하게 동작한다.
 * 			register(@ModelAttribute("dto) UserRequestDto dto) {...}
 * 			* Model객체에 "dto"라는 이름으로 추가한다.
 * 		
 * @RequestParam
 * 	단일 요청 파라미터 값을 추출해서 int, String 타입의 변수에 바인딩 시킨다.
 * 		요청 파라미터 값 ---> int, long, String
 * 			* /post/detail?no=12
 * 			detail(int no)
 * 			detail(@RequestParam("no") int no)
 * 		요청 파라미터 값 ---> List, 배열
 * 			* /cart/add?no=12&no=34&no=14
 * 			add(@RequestParam("no") List<Integer> productNos)
 * 
 * @RequestBody
 * 	요청 메시지의 바디부에 JSON 혹은 XML 형식으로 전달된 데이터를 객체에 바인딩 시킨다.
 * 	REST AIP에서 클라이언트가 서버로 전달한 값을 받을 때 사용한다.
 * 		JSON 혹은 XML ---> 객체에 바인딩
 * 		create(@RequestBody UserCreateDto dto) {...}
 * 
 */
