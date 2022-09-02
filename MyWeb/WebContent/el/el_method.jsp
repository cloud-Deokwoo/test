<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="eltest" uri="/WEB-INF/tlds/el_function.tld" %>
<%	Date today = new Date();
	request.setAttribute("today", today);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EL메서드 호출</title>
	</head>
	<body>
		오늘은 <b>${eltest:dateFormat(today) }</b>입니다.
	</body>
</html>