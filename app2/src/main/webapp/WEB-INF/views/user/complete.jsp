<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row justify-content-center">
	<div class="col-6">
		<div class="card">
			<div class="card-header">
				<h3 class="text-center">회원가입 완료</h3>
			</div>
			<div class="card-body">
				<p>회원가입 정보를 확인하세요</p>
				<table class="table table-bordered">
					<colgroup>
						<col width="20%">
						<col width="80%">
					</colgroup>
					<tbody>
						<tr>
							<th>사용자 번호</th>
							<td>${user.userNo }</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td>${user.username }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${user.email }</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${user.name }</td>
						</tr>
						<tr>
							<th>보유권한</th>
							<td>${user.roleNames }</td>
						</tr>
						<tr>
							<th>가입일자</th>
							<td>${user.createdDate }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp" %>