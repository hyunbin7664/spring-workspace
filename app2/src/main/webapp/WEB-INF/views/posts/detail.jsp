<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row mb-3">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h3>게시글 상세 정보</h3>
			</div>
			<table class="table table-bordered">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>번호</th>
						<td>${post.postNo }</td>
						<th>작성자</th>
						<td>${post.user.name}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">${post.title }</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${post.createdDate }"/> </td>
						<th>수정일</th>
						<td><fmt:formatDate value="${post.updatedDate }"/></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<c:choose>
								<c:when test="${not empty post.filename }">
									${post.originalFilename }
									<a href="/posts/download?no=${post.postNo }" 
									   class="btn btn-outline-primary btn-sm">다운로드</a>
								</c:when>
								<c:otherwise>
									없음
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">${post.content }</td>
					</tr>
				</tbody>
			</table>
			<div class="card-footer text-end">
				<%--
					<sec:authorize /> 
						- 태그로 접근제어
						- 로그인/비로그인 상태에 따라서 특정 컨텐츠 표시여부 제어
						- 로그인 상태일 때 사용자가 가진 권한에 따라서 특정 컨텐츠 표시여부 제어
					<sec:authentication />
						- 인증된 사용자 정보를 표현하거나 사용할 수 있도록 지원
						- 로그인된 사용자의 정보를 표현할 때 사용
						- 로그인된 사용자의 정보를 jstl의 조건식에서 활용할 때 사용
				 --%>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.user" var="user"/>
					<c:choose>
						<c:when test="${user.userNo eq post.userNo }">
							<a href="/posts/update?postNo=${post.postNo }" class="btn btn-warning">수정</a>
							<a href="/posts/delete?postNo=${post.postNo }" class="btn btn-danger">삭제</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-warning disabled">수정</a>
							<a class="btn btn-danger disabled">삭제</a>
						</c:otherwise>
					</c:choose>
				</sec:authorize>
				<a href="/posts/list" class="btn btn-primary">목록</a>
			</div>
		</div>
	</div>
</div>

<div class="row mb-3">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h3>댓글 목록</h3>
			</div>
			<div class="card-body">
				<sec:authorize access="isAuthenticated()">
					<!-- 댓글 작성 폼 -->
					<form class="mb-3">
					<sec:csrfInput/>
						<div class="mb-3">
							<textarea rows="3" class="form-control" name="content"></textarea>
						</div>
						<button type="button" class="btn btn-primary" id="btn-add-comment">댓글 작성</button>
					</form>
				</sec:authorize>
				
				<!-- 댓글이 표시되는 곳 -->
				<div id="box-comment"></div>
			</div>
		</div>
	</div>
</div>

<script>
	// 댓글 조회하기
	function loadComment() {
		
		// 현재 로그인된 사용자의 사용자 번호를 담을 변수
		let userNo;
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.user" var="user" />
			userNo = ${user.userNo};
		</sec:authorize>
		
		/*
			요청방식: GET
			요청URL: /api/comments?postNo=12
			응답데이터: {
						"success": true, 
						"status": 200, 
						"message": "성공", 
						"data": [
							{"commentNo":1, "postNo":12, "userNo":2, 
								"name":"김유신", "content":"xxx", "createdDate":"xxx"}
							, {}
							, {}
						]
					}
			$.getJSON(url [, data] [, function])
				- $.getJSON("/api/comments?postNo=12&page=1", function(result) {});
				- $.getJSON("/api/comments", "postNo=12&page=1", function(result) {});
				- $.getJSON("/api/comments", {postNo=12&page=1", function(result) {});
				
		*/
		$.getJSON(`/api/comments?postNo=${post.postNo}`, function(result) {
			let comments = result.data;

			let htmlContent = "";
			if (comments.length != 0) {
				for (let comment of comments) {
					htmlContent += generateHtml(comment);
				}
			} else {
				htmlContent = `
					<div class="card">
						<div class="card-body">
							<p class="card-text">등록된 댓글이 없습니다.</p>
						</div>
					</div>
				`;
			}
			$("#box-comment").html(htmlContent);
		});
		
	}
				
	loadComment();
	
	// 이벤트 등록시키기 - 스크립트 코드로 동적으로 추가되는 엘리먼트에 이벤트 등록
	$("#box-comment").on("click", '.btn-danger', function() {
		let that = this;
		let commentNo = $(this).data("comment-no");

		$.ajax({
			type:"DELETE", 
			url: `/api/comments/\${commentNo}`, 
			data: {
				"_csrf": $("input[name='_csrf']").val()
			}, 
			success: function(result) {
				// 화면에서도 해당 게시글 제거
				$(that).closest('.card').remove();
			}, 
			error: function() {
				// 오류 내용 표시
			}
		});
	});
	
	// 댓글작성 버튼을 클릭했을 떄 실행되는 이벤트 핸들러 함수 등록
	$("#btn-add-comment").click(function() {
		// 댓글 입력필드의 값을 조회한다.
		// 입력된 댓글이 없으면 경고창을 표시한다.
		let content = $("textarea[name=content]").val();
		if (content == "") {
			alert("댓글 내용은 필수 입력값입니다.");
		}
		
		// JSON 형식으로 데이터를 전달하기 위해서
		// 댓글 등록에 필요한 댓글내용, 게시글 번호를 자바스크립트 객체로 표현
		// ajax로 요청을 보낼 때 JSON.stringify()로 자바스크립트 객체를 JSON형식의 텍스트로 변환할 것이다.
		let commentData = {
				content: content, 
				postNo: ${post.postNo }
		}
		
		$.ajax({
			type: "POST", 
			url: "/api/comments", 
			data: JSON.stringify(commentData), 
			contentType: "application/json", 		// 요청메시지의 타입
			dataType: 'json', 						// 응답메시지의 타입
			success: function(result) {
				/* let comment = result.data;
				let htmlContent = generateHtml(comment);
				$("#box-comment").append(htmlContent); */
				loadComment();
			}, 
			error: function() {
				
			}
		})
	});
	
	function generateHtml(comment) {
	      // 현재 로그인된 사용자의 사용자 번호를 담을 변수
	      let userNo;
	      <sec:authorize access="isAuthenticated()">
	         <sec:authentication property="principal.user" var="user" />
	         userNo = ${user.userNo};
	      </sec:authorize>
	      
	      let htmlContent = `
	         <div class="card mb-2">
	            <div class="card-body">
	               <div class="d-flex justify-content-between">
	                  <div>
	                     <strong>\${comment.name}</strong>
	                     <small>\${comment.createdDate}</small>
	                  </div>
	      `;
	      
	      if (userNo == comment.userNo) {
	         htmlContent += `
	            <button class="btn btn-danger btn-sm" data-comment-no="\${comment.commentNo}">삭제</button>                  
	         `
	      } else {
	         htmlContent += `
	            <button class="btn btn-secondary btn-sm disabled">삭제</button>
	         `
	      }
	                  
	      htmlContent += `
	               </div>
	               <p class="card-text">\${comment.content}</p>
	            </div>
	         </div>
	      `;
	      
	      return htmlContent;
	   }
	
	
</script>

<%@ include file="../common/footer.jsp" %>