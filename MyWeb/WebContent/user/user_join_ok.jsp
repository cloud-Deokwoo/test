<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
	   진행 흐름 : 1)전달 받은 값을 처리(user_join.jsp으로부터 받은) -> 2)회원 가입여부 확인
	   -> 가입된 경우(실패-뒤로 가기) | 가입 안된 경우  -> 3)가입 진행 -> 성공시, 회원 가입 축하 및
	   로그인 페이로 이동 | 실패시, 재가입 페이지로 이동
	*/
	
	   //request.setCharacterEncoding("utf-8");
	   
	   String id = request.getParameter("id");
	   String pw = request.getParameter("pw");
	   String name = request.getParameter("name");
	   String email = request.getParameter("email");
	   String address = request.getParameter("address");
	   
	   //DAO객체 생성
	   UserDAO dao = UserDAO.getInstance();
	   
	   //VO객체 생성 => 값을 전달하기 위해서 
	   UserVO vo = new UserVO(id, pw, name, email, address, null);
	   
	   //회원 가입 이전에 이미 존재하는 아이디인지 검사(중복 검사)
	   int result = dao.IdConfirm(id);
	   
	   if(result == 1) {  //아이디 중복%> 
		   <script>
		   		alert("아이디가 중복되었습니다.");
		   		history.go(-1); //뒤로 가기
		   </script>
	   <%
	   }else{ //중복이 없는 경우 
		   //insert를 진행(회원 가입)
		   int result2 = dao.join(vo);
	   
	       if (result2 == 1){  //회원 가입 성공
	    	   %>
	    	   <script>
	    	      alert("회원 가입을 축하합니다.");
	    	      location.href="user_login.jsp";
	    	   </script>
	    	   <%
	       }else{  //회원 가입 실패 
	    	   %>
	    	   <script>
	    	      alert("회원 가입이 실패했습니다.");
	    	      history.go(-1); //뒤로 가기
	    	   </script>
	    	   <%
	       }
		   
	   }


%>
