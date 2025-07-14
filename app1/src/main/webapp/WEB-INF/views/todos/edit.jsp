<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정 수정 - 일정 관리 시스템</title>
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
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h3 class="mb-0">일정 수정</h3>
                <a href="detail.html" class="btn btn-secondary">
                    <i class="bi bi-arrow-left"></i> 돌아가기
                </a>
            </div>
            <div class="card-body">
                <form method="post" action="/todos/update">
                	<input type="hidden" name="todoNo" value="${todo.todoNo }" /> 
                    <div class="mb-3">
                        <label for="category" class="form-label">카테고리</label>
                        <select class="form-select" name="catNo" required>
                            <option value="">카테고리 선택</option>
                            <c:forEach  var="cat" items="${categories }">
                            	<option value="${catNo }" ${todo.catNo eq cat.catNo ? 'selected' : '' }>${cat.name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label">제목</label>
                        <input type="text" class="form-control" name="title" value="${todo.title }" required>
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" name="content" rows="5" required>${todo.content }</textarea>
                    </div>
					<div class="mb-3">
						<label for="scheduledDate" class="form-label">예정일자</label>
						<fmt:formatDate value="${todo.dueDate }" pattern="yyyy-MM-dd"
							var="dueDate" />
						<input type="date" class="form-control" name="dueDate"
							value="${dueDate }" required>
					</div>
					<div class="mb-3">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status"
								value="처리예정" ${todo.status eq '처리예정' ? 'checked' : '' }> <label class="form-check-label">처리예정</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status"
								value="진행중" ${todo.status eq '진행중' ? 'checked' : '' }> <label class="form-check-label">진행중</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status"
								value="처리완료" ${todo.status eq '처리완료' ? 'checked' : '' }> <label class="form-check-label">처리완료</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="status"
								value="미처리" ${todo.status eq '미처리' ? 'checked' : '' }> <label class="form-check-label">미처리</label>
						</div>

					</div>
					<div class="text-end">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> 저장
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 