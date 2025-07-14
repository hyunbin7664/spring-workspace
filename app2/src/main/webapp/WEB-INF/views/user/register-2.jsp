<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row justify-content-center">
	<div class="col-6">
		<div class="card">
			<div class="card-header">
				<h3 class="text-center">회원가입 - 2단계</h3>
			</div>
			<div class="card-body">
				<form:form action="/register-3" method="post" 
					modelAttribute="userRegisterForm">
					<div class="mb-3">
						<label class="form-label">전화번호</label>
						<form:input class="form-control" path="phone" />
						<form:errors path="username" cssClass="text-danger"/>
					</div>
					<div class="mb-3">
						<label class="form-label">우편번호</label>
						<form:password class="form-control" path="zipcode" />
						<form:errors path="password" cssClass="text-danger"/>
					</div>
					<div class="mb-3">
						<label class="form-label">기본 주소</label>
						<form:input class="form-control" path="address1" />
						<form:errors path="name" cssClass="text-danger"/>
					</div>
					<div class="mb-3">
						<label class="form-label">상세주소</label>
						<form:input class="form-control" path="address2" />
						<form:errors path="email" cssClass="text-danger"/>
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