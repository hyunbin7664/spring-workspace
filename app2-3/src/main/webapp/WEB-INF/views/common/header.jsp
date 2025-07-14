<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/resources/favicon.ico" type="image/x-icon" />
	<title>샘플 애플리케이션</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
	                <span class="navbar-nav">
	                	<strong class="text-white">
	                		<sec:authentication property="principal.username"/>
	                	</strong>
	                	<span class="text-white">님 환영합니다.</span>
	                </span>
                </sec:authorize>
                
                <ul class="navbar-nav">
                	<sec:authorize access="isAnonymous()">
	                	<li class="nav-item">
							<a class="nav-link" href="/login">로그인</a>
						</li>
	                    <li class="nav-item">
	                    	<a class="nav-link" href="/register">회원가입</a>
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