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
			<h1>게시글 목록</h1>
			
			<table class="table">
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${posts }">
					<tr>
						<td>${post.postNo }</td>
						<td><a href="detail/${post.postNo }">${post.title }</a></td>
						<td>${post.name }</td>
						<td><fmt:formatDate value="${post.createdDate }" pattern="yyyy년 MM월 dd일"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:if test="${paging.totalRows gt 0 }">
	<div class="row mb-3">
      <div class="col-12">
         <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
            	<c:choose>
            		<c:when test="${not paging.first }">
               			<li class="page-item"><a class="page-link" href="list?page=${paging.prevPage }">이전</a></li>
            		</c:when>
            		<c:otherwise>
               			<li class="page-item disabled"><span class="page-link">이전</span></li>
            		</c:otherwise>
            	</c:choose>
               
               <c:forEach var="num" begin="${paging.beginPage }" end="${paging.endPage }">
               	<li class="page-item ${paging.currentPage eq num ? 'active' : '' }">
               		<a class="page-link" href="list?page=${num }">${num }</a>
               	</li>
               </c:forEach>
               
               <c:choose>
            		<c:when test="${not paging.last }">
               			<li class="page-item"><a class="page-link" href="list?page=${paging.nextPage }">다음</a></li>
            		</c:when>
            		<c:otherwise>
               			<li class="page-item disabled"><span class="page-link">다음</span></li>
            		</c:otherwise>
            	</c:choose>
            </ul>
         </nav>
      </div>
   </div>
   </c:if>
   
   <div class="row mb-3">
   	<div class="col-12 text-end">
   		<a href="create" class="btn btn-primary">새 게시글</a>
   	</div>
   </div>
</div>
</body>
</html>