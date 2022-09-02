<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    //request.setCharacterEncoding("utf-8");  //한글 인코딩
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    /*
     1. UserDAO의 login메서드의 결과 값을 확인
       success인 경우(반환 값이 1인 경우), getUserInfo()를 통해서
       id와 name값을 얻어서 session에 저장(user_id, user_name으로 저장)
       user_mypage.jsp으로 이동함. 
     2. fail인 경우(반환값이 0인 경우), 경고창을 출력하고, 뒤로가기로 로그인 페이지로 이동
    */
    
    //DAO 객체 생성
    UserDAO dao = UserDAO.getInstance();
    int result = dao.login(id, pw);
    
    if(result == 0){ //로그인 실패시
    	%>
    	<script>
    		alert("아이디 혹은 비밀번호를 확인하세요.");
    		history.go(-1);
    	</script>   	
    	<%	
    }else{  //로그인 성공시 
    	//회원 정보를 얻어오기
    	UserVO vo = dao.getUserInfo(id);
    	String name = vo.getName();
    	
    	//아이디와 이름을 세션에 저장
    	session.setAttribute("user_id", id);
    	session.setAttribute("user_name", name);
    	
    	response.sendRedirect("user_mypage.jsp");
    
    }
    %>
