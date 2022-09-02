<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
	1. 폼값을 처리 
	2. login(id, 예전비밀번호)로 아이디와 비밀번호가 맞는지 확인
	  예전비밀번호가 틀렸다면 뒤로가기
	3. 일치하면 changePassword(??) 를 실행
	4. 성공하면, "비밀번호 변경처리 되었습니다." 출력하고, mypage로 이동
	      실패하면, "비밀번호 변경에 실패했습니다." 출력하고, mypage로 이동 
	*/
	request.setCharacterEncoding("utf-8");
	
	if(session.getAttribute("user_id")==null){
		response.sendRedirect("user_login.jsp");
	}
	
	String id = (String)session.getAttribute("user_id");
	
	String oldpw = request.getParameter("old_pw");
	String newpw = request.getParameter("new_pw");
	
	UserDAO dao = UserDAO.getInstance();
	int result = dao.login(id, oldpw);
	
	if (result == 0) {  //변경전 비밀번호 틀린 경우, 
		%>
		<script>
			//alert("비밀번호 변경 실패입니다.")
			history.go(-1);
		</script>
		<%
	}else{ //변경전 비밀번호가 맞는 경우, 패스워드 변경 진행...  
		int result2 = dao.changePassword(id, newpw);
		if (result2 ==1) { //변경 성공
			%>
			<script>
				alert("비밀번호 변경처리가 되었습니다.");
				location.href="user_mypage.jsp";
			</script>
			<%
		}else{  //변경 실패 
			%>
			<script>
				alert("비밀번호 변경에 실패했습니다.");
				location.href="user_mypage.jsp";
			</script>
			<%
		}
		
	}

%>    
    
 