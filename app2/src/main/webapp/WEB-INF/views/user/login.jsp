<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row justify-content-center">
	<div class="col-6">
		<div class="card">
			<div class="card-header">
				<h3 class="text-center">로그인</h3>
			</div>
			<div class="card-body">
				<form method="post" action="/login">
					<sec:csrfInput/>
					<div class="mb-3">
						<label class="form-label">아이디</label>
						<input type="text" class="form-control" name="username" />
					</div>
					<div class="mb-3">
						<label class="form-label">비밀번호</label>
						<input type="password" class="form-control" name="password" />
					</div>
					<div class="d-grid">
						<button type="submit" class="btn btn-primary">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>