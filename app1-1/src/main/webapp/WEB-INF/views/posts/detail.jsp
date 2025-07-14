<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<title>샘플 애플리케이션</title>
</head>
<body>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1>게시글 상세정보</h1>
			
			<table class="table">
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
						<td>${post.name }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<c:out value="${post.title }" />
						</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${post.createdDate }" pattern="yyyy년 M월 d일"/></td>
						<th>최종수정일</th>
						<td><fmt:formatDate value="${post.updatedDate }" pattern="yyyy년 M월 d일"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<c:out value="${post.content }" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col-12 text-end">
			<a href="list" class="btn btn-primary">목록</a>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4>댓글</h4>
				</div>
				<div class="card-body">
				<form id="form-comment" class="mb-3">
                  <div class="mb-3">
                     <textarea class="form-control" name="content" rows="3"></textarea>
                  </div>
                  <button type="button" id="btn-add-comment" class="btn btn-primary  btn-sm">댓글 작성</button>
               </form>
					<div id="comment-list">
						<!-- 댓글이 추가될 곳 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
   $("#btn-add-comment").click(function() {
      $.ajax({
         method: "post",
         url: `/api/comments`,
         data: JSON.stringify({
            postNo: ${post.postNo},
            content: $("textarea[name=content]").val()
         }),
         contentType: 'application/json; charset=UTF-8',
         dataType: 'json',
         success: function(comment) {
        	 // 내용 입력필드 지우기
        	 $("textarea[name=content]").val("");
        	 let htmlContent = `
             <div class="card mb-2">
                <div class="card-body">
                   <div class="d-flex justify-content-between">
                      <div>
                         <strong>\${comment.name}</strong>
                         <small class="text-muted">\${comment.createdDate}</small>
                      </div>
                      <button class="btn btn-sm btn-danger">삭제</button>
                   </div>
                   <p class="card-text">\${comment.content}</p>
                </div>
             </div>
             `;
             
             $("#comment-list").append(htmlContent);
         }
      });
   });

   function loadComments() {
      $.getJSON(`/api/comments/${post.postNo}`, function(comments) {
         let htmlContent = comments.map(c => `
            <div class="card mb-2">
               <div class="card-body">
                  <div class="d-flex justify-content-between">
                     <div>
                        <strong>\${c.name}</strong>
                        <small class="text-muted">\${c.createdDate}</small>
                     </div>
                     <button class="btn btn-sm btn-danger">삭제</button>
                  </div>
                  <p class="card-text">\${c.content}</p>
               </div>
            </div>
         `).join('');
         
         $('#comment-list').html(htmlContent);
      });
   }
   
   loadComments();
	
</script>
</html>
<!--
	* low-level ajax 메소드
	$.ajax(settings);
		settings는 자바스크립트 객체다.
		{
			method: 요청방식을 지정한다. "get", "post", "put", "delete"
			url: 요청 URL을 지정한다.
			data: 서버로 보내는 데이터
				  객체형식 -> {name:value, name:value}
				  쿼리스트링 형식 -> "name=value&name=value"
				  JSON 형식 -> '{"name":value, "name":value}'
			contentType: 서버로 보내는 데이터의 컨텐츠 타입을 지정한다.
						 기본값: 'application/x-www-form-urlencoded'
						 		'application/json'
						 		'multipart/form-data'
			dataType: 서버에서 보내는 응답컨텐츠 타입
					  * "xml", "json", "jsonp", "text", "script" 중 하나다.
			success: function(result) {...} 성공적인 응답이 왔을 때 실행되는 함수
			error: function() {...} 요청이 실패했을 때 실행되는 함수
			beforeSend: function() {...} 요청을 보내기 전 실행되는 함수
			complete: function() {...} 성공 실패와 상관없이 요청이 완료된 후 실행되는 함수
		}
	
	* shorthand ajax 메소드
		url				: 요청 URL
		data			: 서버로 보내는 데이터
						  객체형식 -> {name:value, name:value}
						  쿼리스트링 형식 -> "name=value&name=value"
						  
		success			: 서버에서 성공적인 응답이 왔을 때 실행되는 함수
						  function(result) {...}
						  * result에는 서버가 보낸 응답데이터가 전달된다.
						  * json데이터는 javascript 객체/배열로 변환된 후 전달된다.
						  * XML데이터는 XML DOM객체로 변환된 후 전달된다.
						  
		dataType		: 서버에서 보내는 응답컨텐츠 타입
						  * "xml", "json", "jsonp", "text", "script" 중 하나다.
	$.get(url [, data] [, success] [,dataType])
		- GET 전용의 ajax 메소드다.
	$.post(url [, data] [, success] [,dataType])
		- POST 전용의 ajax 메소드다.
	$.getJSON(url [, data] [, success] [,dataType])
		- GET 전용, JSON 응답 전용의 ajax 메소드다.
		
		
	
	jQuery의 DOM 조회/변경하기
	
	<p>안녕하세요</p>
	.text()
	.text("반갑습니다.")
	
	<p><span>26,000 원</span></p>
	.html()								- "<span>26,000 원</span>"
	.html("<span>54,000 원</span>")		- <p><span>54,000 원</span></p>
	
	폼 입력요소의 값을 조회하거나 변경한다.(input, checkbox, radio, select, textarea)
	.val()
	.val(value)
	
	엘리먼트의 속성을 조회하거나 변경한다.
	<span data-book-price="45000">45,000 원</span>
	.attr(name)				- .attr("data-book-price") --- > "45000"
	.attr(name, value)		- .attr("data-book-price", 50000)
	
	엘리먼트의 프로퍼티를 조회하거나 변경한다.
	폼 입력요소 "checked", "selected", "disabled" 프로퍼티를 조회/변경할 때 사용한다.
	.prop(name)	
	.prop(name, value)
	
	// 추가하기
	.prepend(htmlContent)	// 해당 엘리먼트의 맨 처음 자식 엘리먼트로 추가한다.
	.append(htmlContent)	// 해당 엘리먼트의 맨 마지막 자식 엘리먼트로 추가한다.
	.before(htmlContent)	// 해당 엘리먼트의 앞에 추가한다.
	.after(htmlContent)		// 해당 엘리먼트의 뒤에 추가한다.
	
	// 삭제하기
	.empty()				// 비우기
	.remove()				// 삭제
-->