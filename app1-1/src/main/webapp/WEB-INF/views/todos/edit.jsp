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
                <form>
                    <div class="mb-3">
                        <label for="category" class="form-label">카테고리</label>
                        <select class="form-select" id="category" required>
                            <option value="">카테고리 선택</option>
                            <option value="1" selected>업무</option>
                            <option value="2">개인</option>
                            <option value="3">가족</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label">제목</label>
                        <input type="text" class="form-control" id="title" value="프로젝트 회의" required>
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" id="content" rows="5" required>프로젝트 진행 상황 점검 및 다음 단계 계획 수립</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="scheduledDate" class="form-label">예정일자</label>
                        <input type="date" class="form-control" id="scheduledDate" value="2024-03-20" required>
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