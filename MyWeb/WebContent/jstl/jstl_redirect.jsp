<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL_redirect</title>
	</head>
	<body>
		<c:redirect url="test.jsp">
			<c:param name="abc" value="안녕!!!" />
			<c:param name="efg" value="안녕2" />
		</c:redirect>
	</body>
</html>