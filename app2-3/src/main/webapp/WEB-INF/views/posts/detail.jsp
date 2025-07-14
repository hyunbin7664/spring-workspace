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
						<td>${post.user.name }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">${post.title }</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${post.createdDate }" /></td>
						<th>수정일</th>
						<td><fmt:formatDate value="${post.updatedDate }" /></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<c:choose>
								<c:when test="${not empty post.filename }">
									${post.originalFilename }
									<a href="/posts/download?no=${post.postNo }" class="btn btn-outline-primary btn-sm">다운로드</a>
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
						- 태그로 접근 제어
						- 로그인/비로그인 상태에 따라서 특정 컨텐츠 표시여부 제어
						- 로그인 상태일 때 사용자가 가진 권한에 따라서 특정 컨텐츠 표시여부 제어
					<sec:authentication /> 
						- 인증된 사용자 정보를 표현하거나 사용할 수 있게한다.
						- 로그인된 사용자의 정보를 표현할 때 사용
						- 로그인된 사용자의 정보를jstl의 조건식에서 활용할 때 사용
				 --%>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.user" var="user"/>
					<c:choose>
						<c:when test="${user.userNo eq post.userNo }">
							<a href="" class="btn btn-warning">수정</a>
							<a href="" class="btn btn-danger">삭제</a>
						</c:when>
						<c:otherwise>
							<a href="" class="btn btn-warning disabled">수정</a>
							<a href="" class="btn btn-danger disabled">삭제</a>
						</c:otherwise>
					</c:choose>
				</sec:authorize>
				<a href="" class="btn btn-primary">목록</a>
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>