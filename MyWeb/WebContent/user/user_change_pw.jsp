<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("user_login.jsp"); //로그인 페이지로 리다이렉트 처리 
	}
%>

<%@ include file="../include/header.jsp" %>
	<section>
		<div align="center">
			<form action="user_change_pw_ok.jsp" method="post">
				<h3>비밀번호 변경 페이지</h3>
				<hr>
				현재 비밀번호 : <input type="password" name="old_pw"><br>
				변경 비밀번호 : <input type="password" name="new_pw"><br>
				<br>
				<input type="submit" value="변경">
				<input type="button" value="취소" onclick="history.go(-1)">
			</form>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>

