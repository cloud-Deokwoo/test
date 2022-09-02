<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    	세션 정보를 확인 : url을 통해서 강제 접근 차단
 --%>
<%
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("user_login.jsp"); //로그인 페이지로 리다이렉트 처리 
	}

	//세션으로 넘겨 받은 값 처리 
	String id = (String)session.getAttribute("user_id");
	String name = (String)session.getAttribute("user_name");
%>
<%@ include file="../include/header.jsp" %>
	<section>
		<div align="center">
			<h2>MyPage</h2>
			<hr>
			<%=id %>(<%=name %>)님의 회원정보 관리합니다.
			<hr>
			<input type="button" value="비밀번호 변경" class="btn btn-default" 
			onclick="location.href='user_change_pw.jsp'">
			<input type="button" value="회원정보 변경" class="btn btn-primary"
			onclick="location.href='user_update.jsp'">
			<input type="button" value="회원탈퇴" class="btn btn-info"
			onclick="location.href='user_delete.jsp'">
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>