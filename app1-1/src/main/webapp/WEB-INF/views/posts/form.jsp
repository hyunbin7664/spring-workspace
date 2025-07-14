<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 폼 입력값 유효성 검증을 지원하는 태그 라이브러리다. --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<h1>게시글 등록폼</h1>
			
			<form:form class="border bg-light p-3"
				method="post"
				action="create"
				modelAttribute="dto">
				<div class="form-group mb-3">
					<label class="form-label">제목</label>
					<form:input class="form-control" path="title" />
					<form:errors path="title" cssClass="text-danger fst-italic"/>
				</div>
				<div class="form-group mb-3">
					<label class="form-label">내용</label>
					<form:textarea class="form-control" rows="10" path="content" ></form:textarea>
					<form:errors path="content" cssClass="text-danger fst-italic"/>
				</div>
				<div class="text-end">
					<form:button type="reset" class="btn btn-secondary">취소</form:button>
					<form:button type="submit" class="btn btn-primary">등록</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>