<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row mb-3">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h3 class="d-flex justify-content-between">
					게시글
					<a href="/posts/create" class="btn btn-primary btn-sm">새 글 작성</a>	
				</h3>
			</div>
			<div class="card-body">
				<table class="table">
					<colgroup>
						<col width="10%" />
						<col width="10%" />
						<col width="*" />
						<col width="10%" />
						<col width="15%" />
					</colgroup>
					<thead>
						<tr>
							<th>순번</th>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="post" items="${posts }" varStatus="loop">
							<tr>
								<td>${loop.count }</td>
								<td>${post.postNo }</td>
								<td><a href="/posts/detail?no=${post.postNo }">${post.title }</a></td>
								<td>${post.user.name }</td>
								<td><fmt:formatDate value="${post.createdDate }"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>	
</div>

<%@ include file="../common/footer.jsp" %>