<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일정 상세 - 일정 관리 시스템</title>
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
                <h3 class="mb-0">일정 상세</h3>
                <div>
                	<c:if test="${todo.status ne '처리완료'}">
	                    <a href="/todos/update?no=${todo.todoNo }" class="btn btn-warning">
	                        <i class="bi bi-pencil"></i> 수정
	                    </a>
	                    <a href="/todos/complete?no=${todo.todoNo }" class="btn btn-success">
	                        <i class="bi bi-check-circle"></i> 완료
	                    </a>
	                    <a href="/todos/remove?no=${todo.todoNo }"  class="btn btn-danger">
	                        <i class="bi bi-trash"></i> 삭제
	                    </a>
                	</c:if>
                    <a href="/todos/list" class="btn btn-secondary">
                        <i class="bi bi-list"></i> 목록
                    </a>
                </div>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">일정번호</div>
                    <div class="col-md-4">${todo.todoNo }</div>
                    <div class="col-md-2 fw-bold">카테고리</div>
                    <div class="col-md-4">${todo.category.name }</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">제목</div>
                    <div class="col-md-10">${todo.title }</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">내용</div>
                    <div class="col-md-10">${todo.content }</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">예정일자</div>
                    <div class="col-md-4">
                    	<fmt:formatDate value="${todo.dueDate }" pattern="yyyy-MM-dd"/>
                    </div>
                    <div class="col-md-2 fw-bold">완료일자</div>
                    <div class="col-md-4">
                    	<fmt:formatDate value="${todo.completeDate }" pattern="yyyy-MM-dd"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">상태</div>
                    <div class="col-md-4">
                    	<c:choose>
                       		<c:when test="${todo.status == '처리예정' }">
	                        	<span class="badge bg-warning">${todo.status }</span>
                       		</c:when>
                       		<c:when test="${todo.status == '진행중' }">
	                        	<span class="badge bg-info">${todo.status }</span>
                       		</c:when>
                       		<c:when test="${todo.status == '처리완료' }">
	                        	<span class="badge bg-primary">${todo.status }</span>
                       		</c:when>
                       		<c:when test="${todo.status == '미처리' }">
	                        	<span class="badge bg-danger">${todo.status }</span>
                       		</c:when>
                       	</c:choose>
                    </div>
                    <div class="col-md-2 fw-bold">작성자</div>
                    <div class="col-md-4">${todo.user.name }</div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-2 fw-bold">등록일</div>
                    <div class="col-md-4">
                    	<fmt:formatDate value="${todo.createdDate }" pattern="yyyy-MM-dd"/>
                    </div>
                    <div class="col-md-2 fw-bold">최종수정일</div>
                    <div class="col-md-4">
                    	<fmt:formatDate value="${todo.updatedDate }" pattern="yyyy-MM-dd"/>                    	
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 