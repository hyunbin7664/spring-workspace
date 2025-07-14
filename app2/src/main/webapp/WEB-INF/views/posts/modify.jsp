<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<div class="row mb-3">
	<div class="col-12">
		<div class="card">
			<div class="card-header">
				<h3>게시글 수정</h3>
			</div>
			<div class="card-body">
				<form method="post" action="/posts/update" enctype="multipart/form-data">
				<input type="hidden" name="postNo" value="${post.postNo }" />
					<sec:csrfInput/>
					<div class="form-group mb-3">
						<label class="form-label">제목</label>
						<input type="text" class="form-control" name="title" value="${post.title }" />
					</div>
					<div class="form-group mb-3">
						<label class="form-label">내용</label>
						<textarea class="form-control" rows="5" name="content" >${post.content }</textarea>
					</div>
					<div class="form-group mb-3">
						<label class="form-label">첨부파일 (${post.originalFilename })</label>
						<input type="file" class="form-control" name="upfile" />
					</div>
					<div class="text-end">
						<button type="submit" class="btn btn-primary" >등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>

</script>

<%@ include file="../common/footer.jsp" %>