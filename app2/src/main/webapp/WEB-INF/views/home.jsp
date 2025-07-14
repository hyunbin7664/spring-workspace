<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>

<div class="jumbotron mb-3">
	<h1 class="display-4">샘플 애플리케이션 프로젝트</h1>
	<p class="lead">
		샘플 애플리케이션은 
		spring boot 3.3.x, spring security, spring web,
		mybatis를 활용한 프로젝트다.
	</p>

	<hr class="my-4">

	<sec:authorize access="isAnonymous()">
		<a href="/register-1" class="btn btn-primary btn-lg">회원가입</a>
		<a href="/login" class="btn btn-secondary btn-lg">로그인</a>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<%--
			Authentication의 principal 속성 정보로 인증된 사용자정보 표현하기
			
			<sec:authentication property="principal" />는
			 SecurityUser를 반환한다.
		 --%>
		<dl>
			<dt>아이디</dt>
			<dd><sec:authentication property="principal.username"/> </dd>

			<dt>사용자 번호</dt>
			<dd><sec:authentication property="principal.user.userNo"/></dd>

			<dt>이름</dt>
			<dd><sec:authentication property="principal.user.name"/></dd>

			<dt>이메일</dt>
			<dd><sec:authentication property="principal.user.email"/></dd>
		</dl>
		
		<%--
			<sec:authentication property="principal.user" />는
			 SecurityUser의 user 프로퍼티값을 반환한다.
			 즉, com.example.vo.User 객체다.
			 
			 아래의 코드는 com.example.vo.User를 "user"라는 이름으로
			 PageContext의 속성으로 저장한다.
			 PageContext는 속성을 저장하는 객체다. 해당 JSP 내부에서만
			 접근가능한 객체다.
		 --%>
		<sec:authentication property="principal.user" var="user"/>
		<dl>
			<dt>사용자 번호</dt>
			<dd>${user.userNo }</dd>

			<dt>이름</dt>
			<dd>${user.name }</dd>

			<dt>이메일</dt>
			<dd>${user.email }</dd>
		</dl>
	</sec:authorize>
</div>

<%@ include file="common/footer.jsp" %>
