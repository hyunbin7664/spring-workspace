<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<%--
		<sec:csrfMetaTags />
			- csrf 관련 <meta /> 태그를 자동으로 추가한다.
			
			<meta name="_csrf_header" content="X-CSRF-TOKEN">
			<meta name="_csrf" content="1111111111111">
	 --%>
	 <%-- 
	 	1.
	 	<sec:csrfMetaTags />
	 	2.
	 	<meta name="_csrf" content="${_csrf.token}" />
		<meta name="_csrf_header" content="${_csrf.headerName}" />
		
		둘 중 아무거나 쓰면 되는..건..가......
	 --%>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/resources/favicon.ico" type="image/x-icon" />
	<title>샘플 애플리케이션</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
	<script type="text/javascript">
		// <meta> 태그에서 csrf 정보를 조회한다.
		// _csrf_header을 조회하면 요청헤더명이 조회된다.
		// _csrf을 조회하면 csrf 토큰이 조회된다.
		const token = $('meta[name="_csrf"]').attr('content');
		const header = $('meta[name="_csrf_header"]').attr('content');

		/*
			.on('ajaxSend', 함수)
				- 모든 ajax요청이 보내지기 전에 함수가 실행된다.
				- 즉, ajax요청 전에 실행될 공통기능을 함수로 작성할 수 있다.
				
			.on('ajaxStart', 함수)
			.on('ajaxStop', 함수)
			.on('ajaxError', 함수)
			.on('ajaxSuccess', 함수)
		*/
		
		/*
			이 페이지에서 모든 ajax 작성이 보내지기전에 
			요청메세지의 헤더에 csrf 토큰정보를 담는다.
		*/
		$(document).on('ajaxSend', function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		})
	</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/">홈</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/posts/list">게시판</a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
	                    <li class="nav-item">
	                        <a class="nav-link" href="/admin/home">관리자</a>
	                    </li>
                    </sec:authorize>
                </ul>
                
                <sec:authorize access="isAuthenticated()">
	                <span class="navbar-text">
	                	<strong class="text-white">
	                		<sec:authentication property="principal.username"/>
	                	</strong>님 환영합니다.
	                </span>
                </sec:authorize>
                
                <ul class="navbar-nav">
                	<sec:authorize access="isAnonymous()">
	                	<li class="nav-item">
							<a class="nav-link" href="/login">로그인</a>
						</li>
	                    <li class="nav-item">
	                    	<a class="nav-link" href="/register-1">회원가입</a>
	                    </li>
                	</sec:authorize>
                    <sec:authorize access="isAuthenticated()">
						<li class="nav-item">
							<form action="/logout" method="post" class="d-inline">
								<sec:csrfInput/>
								<button type="submit" class="btn btn-link nav-link">로그아웃</button>
							</form>
						</li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
	<div class="container mt-4">