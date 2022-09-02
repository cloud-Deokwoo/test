<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

	<section>
		<div align="center">
			<form name="regform" action="register.board" method="post">
				<h2>게시판 글작성 페이지</h2>
				<hr>
				<table border="1" width="500">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" size="10"></td>
					</tr>
					<tr>
						<td>글제목</td>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<td>글내용</td>
						<td><textarea rows="10" style="width:100%;" name="content"></textarea></td>
					</tr>
					<tr>
						<!-- 글등록 메뉴 -->
						<td colspan="2" align="center">
							<input type="button" value="등록" onclick="registCheck()">
							<input type="button" value="목록" onclick="location.href='list.board'">
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</section>

<%@ include file="../include/footer.jsp" %>
	<!-- 
		registCheck() 구현(form은 regform 이름으로 설정)
		작성자, 글제목에 공백을 확인하고, 공백이 아니라면 submit() 처리함
	 -->
	<script>
		function registCheck(){
			//작성자, 글제목에 공백 확인, 공백이 아니라면 submit()처리
			if (document.regform.writer.value == ""){
				alert("작성자를 입력하세요!");
				return;
			}else if(document.regform.title.value == ""){
				alert("제목을 입력하세요");
				return;
			}else if(confirm("게시글을 등록하시겠습니까?")){
				document.regform.submit();
			}
		}
	
	</script>