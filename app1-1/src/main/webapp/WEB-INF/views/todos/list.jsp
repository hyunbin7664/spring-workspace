<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정 관리 시스템</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">일정 관리</a>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="d-flex justify-content-between mb-3">
            <h2>일정 목록</h2>
            <a href="register" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> 새 일정 등록
            </a>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>예정일자</th>
                        <th>상태</th>
                        <th>작성자</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="todos" items="${todos }">
	                    <tr>
	                        <td>${todos.todoNo }</td>
	                        <td>${todos.category.name }</td>
	                        <td><a href="detail?no=${todos.todoNo }">${todos.title }</a></td>
	                        <td><fmt:formatDate value="${todos.dueDate }" pattern="yyyy-MM-dd"/></td>
	                        <td><span class="badge bg-warning">${todos.status }</span></td>
	                        <td>${todos.user.name }</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">이전</a>
                </li>
                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">다음</a>
                </li>
            </ul>
        </nav>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 