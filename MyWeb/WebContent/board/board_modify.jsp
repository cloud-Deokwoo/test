<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

	<section>
		<div align="center">
			<form name="regform" action="update.board?pageNum=${requestScope.pageNum}" method="post" class="form-inline">
				<h2>게시판 글 수정 페이지</h2>
				<hr>
				<table border="1" width="500">
					<tr>
						<td>글번호</td>
						<td><input type="text" name="num" value="${vo.num }" readonly></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" value="${vo.writer }" readonly></td>
					</tr>
					<tr>
						<td>글제목</td>
						<td><input type="text" name="title" value="${vo.title }" style="width:100%;"></td>
					</tr>
					<tr>
						<td>글내용</td>
						<td align="center"><textarea name="content" rows="10" style="width:100%;">${vo.content }</textarea></td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<div align="center" class="form-group">
								<input type="button" value="수정하기" onclick="modifyCheck()" class="btn btn-primary">
								<input type="button" value="목록" onclick="location.href='list.board?pageNum=${requestScope.pageNum}'" class="form-control">
								<input type="button" value="삭제하기" onclick="location.href='delete.board?num=${vo.num }&pageNum=${requestScope.pageNum}'" class="btn btn-default">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	
<%@ include file="../include/footer.jsp" %>
	<script>
		function modifyCheck(){
			//글제목 공백을 확인하고 공백이 아니면 submit()를 실행
			if (document.regform.title.value == ""){
				alert("제목을 입력하세요!!!");
				return;
			}else if(confirm("게시글을 수정하시겠습니까?")){
				document.regform.submit();
			}
		}
	</script>