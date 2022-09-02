<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EL표기법1</title>
	</head>
	<body>
		<%=1+2 %><br>
		<%="안녕" %><br>
		<%=1>2 || 1==2 %><br>
		--------------------------<br>
		${1+2 }<br>
		${'안녕' }<br>
		
		<!-- 비교/논리 연산 -->
		${1 > 2 || 1 == 2 }<br>
		${1 > 2 or 1 == 2 }<br>
		
		${1 < 2 && 1 > 2 }<br>
		${1 < 2 and 1 >2 }<br>
		
		${!(1 < 2) && !(1 > 2) }<br>
		${not(1 < 2) && not(1 > 2) }<br>
		
		${'홍길동' == '홍길동' }<br>
		${'홍길동' eq '홍길동' }<br>
		
		${'홍길동' != '홍길동' }<br>
		<%-- ${'홍길동' ne '홍길동' }<br> --%>
		
		
		${10 <= 9 }<br>
		${10 le 9 }<br>
		
		${10 < 9 }<br>
		${10 lt 9 }<br>
		
		${10 > 9 }<br>
		${10 gt 9 }<br>
		
		${10 >= 9 }<br>
		${10 ge 9 }<br>
		
		<!-- 산술연산 -->
		${1-2 }<br>
		${1*2 }<br>
		${1/2 }<br>
		${1%2 }<br>
		${1 mod 2 }<br>
		
		<!-- 삼항연산 -->
		${ 1==2 ? "같음":"다름" }
	
	</body>
</html>