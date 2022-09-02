<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EL표기법2-ok</title>
	</head>
	<body>
		이름 : ${param.name }<br>
		아이디 : ${param.id }<br>
		비밀번호 : ${param.pw }<br>	
	</body>
</html>