<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row mb-3">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h3>새 게시글 작성</h3>
			</div>
			<div class="card-body">
				<form method="post" action="/posts/create" enctype="multipart/form-data">
					<sec:csrfInput/>
					<div class="form-group mb-3">
						<label class="form-label">제목</label>
						<input type="text" class="form-control" name="title" />
					</div>
					<div class="form-group mb-3">
						<label class="form-label">내용</label>
						<textarea class="form-control" rows="5" name="content"></textarea>
					</div>
					<div class="form-group mb-3">
						<label class="form-label">첨부파일</label>
						<input type="file" class="form-control" name="upfile" />
					</div>
					<div class="text-end">
						<button type="submit" class="btn btn-primary">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>

</script>

<%@ include file="../common/footer.jsp" %>