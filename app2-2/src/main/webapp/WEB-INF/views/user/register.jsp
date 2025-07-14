<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row justify-content-center">
	<div class="col-6">
		<div class="card" >
			<div class="card-header">
				<h3 class="text-center">회원가입</h3>
			</div>
			<div class="card-body">
				<form:form action="/register" method="post" modelAttribute="userRegisterForm">
					<%-- 
						<sec:csrfInput/>
							CSRF(사이트간 요청 변조) 취약점 공격을 방어하기 위한
							csrf 토큰을 폼에 추가한다.
						
						<sec:csrfInput/>           : form:form 이 태그를 사용하면 안적어도됨
					--%>
					<div class="mb-3">
						<label class="form-label">아이디</label>
						<form:input class="form-control" path="username" />
						<form:errors path="username" cssClass="text-danger" />
					</div>
					<div class="mb-3">
						<label class="form-label">비밀번호</label>
						<form:input class="form-control" path="password" />
						<form:errors path="password" cssClass="text-danger" />
					</div>
					<div class="mb-3">
						<label class="form-label">이름</label>
						<form:input class="form-control" path="name" />
						<form:errors path="name" cssClass="text-danger" />
					</div>
					<div class="mb-3">
						<label class="form-label">이메일</label>
						<form:input class="form-control" path="email" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<div class="d-grid">
						<button type="submit" class="btn btn-primary">회원가입</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>