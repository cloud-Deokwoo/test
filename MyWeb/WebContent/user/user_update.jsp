<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	이 페이지에 들어왔을 때, getUserInfo() 메서드를 재활용해서 정보를 불러온 후에
	아래의 Form태그 내에 input태그들에게 해당 값을 보여주게 처리해야 한다. 
	주의)id태그는 변경 할 수 없게 처리해야 함. 
	세션 정보 없이 접근 불가하게 설정
	*/
	if (session.getAttribute("user_id")== null){
		response.sendRedirect("user_login.jsp");
	}
	
	//id를 세션에서 얻어오기
	String id = (String)session.getAttribute("user_id");
	
	//DAO객체 생성
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = dao.getUserInfo(id); //vo에 사용자에 대한 정보 저장

%>    
<%@ include file="../include/header.jsp" %>
	<section>
		<div align="center">
			<form name="regform" action="user_update_ok.jsp" method="post">
				<h2>회원정보 수정 페이지</h2>
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" value="<%=id %>" readonly></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" value="<%=vo.getName() %>"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" name="email" value="<%=vo.getEmail() %>"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address" value="<%=vo.getAddress() %>"></td>
					</tr>
				</table>
				<br><br>
				<input type="button" value="정보수정" class="btn btn-primary" onclick="check()">
				<input type="button" value="마이페이지" class="btn btn-info" onclick="location.href='user_mypage.jsp'">
			</form>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>
	<script>
		function check() {
			//form은 document.태그이름.태그이름 으로 하위 태그에 접근이 가능함. 
			if (document.regform.name.value == ''){
				alert("이름은 필수 사항입니다.");
				return;
			}else if (confirm("회원정보 수정을 하시겠습니까?")){
				//confirm() 확인 창에 "예(확인)"을 클릭시 true반환, "아니요(취소)" 클릭시 false반환
				document.regform.submit(); //자바스크립트의 submit() 기능
			}
		}
	</script>
