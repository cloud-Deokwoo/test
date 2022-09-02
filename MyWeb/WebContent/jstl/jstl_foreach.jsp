<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL forEach... 및 기타</title>
	</head>
	<body>
		<%--
		1 ~ 100까지 합	

		<%
			int sum = 0;
			for (int i = 1 ; i <=100 ; i++){
				sum += i;
			}
		%>
		결과 : <%=sum %>
		 --%>
		<!-- jstl의 변수 선언 c:set -->
		<c:set var="sum" value="0" />
		
		<c:forEach var="i" begin="1" end="100" step="1">
			<c:set var="sum" value="${sum + i }" />
		</c:forEach>
		
		결과 (EL표기): ${sum }
		결과 (c:out): <c:out value="${sum }" />
		 
		<hr>
		<h2>구구단 3단 출력</h2>
		<c:forEach var="i" begin="1" end="9" step="1">
			3 x ${i } = ${3*i }<br>		
		</c:forEach>
		
		<hr>
		<h2>모든 구구단 출력</h2>
		<c:forEach var="i" begin="2" end="9" step="1">
			<h3>${i }단 출력</h3>
			<c:forEach var="j" begin="1" end="9" step="1">
				${i } x ${j } = ${i*j }<br>
			</c:forEach> 
		</c:forEach>
		
		<h2>향상된 for문</h2>
		<%
			int[] arr = {1,2,3,4,5,6};
			for (int a : arr){
				out.println(a);
			}
		%>
		<br>
		<c:set var="arr2" value="<%= new int[]{1,2,3,4,5} %>" />
		<h3>JSTL을 이용한 향상된 for문 출력</h3>
		<c:forEach var="i" items="${arr2 }" varStatus="status">
			${i } : ${status.count } : ${status.index }:${status.current }<br>
		</c:forEach>
		<!-- 
		status.index : 0부터 시작하는 루프 인덱스 값을 의미
		status.count : 현재 몇번째 루프인지 표시하는 값으로 1부터 시작
		status.current : 현재 아이템의 값을 의미함. 즉, var의 속성값과 같습니다. 
		status.first : 현재가 첫번째 루프이면 참입니다. 
		status.last : 현재가 마지막 루프이면 참입니다.
		status.begin : begin 속성을 사용했을 경우에 그 값이 출력
		status.end : end속성을 사용했을 경우에 그 값이 출력
		status.step : step속성을 사용했을 경우 그 값이 출력  
		 -->
		 
		<h2>forTokens 태그 </h2>
		<!-- 자바의 StringTokenizer를 JSTL로 사용을 구현한 것. 문자열을 구분자로 
		나눌 때에 사용 -->
		<c:forTokens var="abc" items="안녕/하세요/지금은/ 배부른/ 시간/입니다." 
		delims="/">
			${abc }<br>
		</c:forTokens>
		
		<h2>c:catch</h2>
		<!-- 자바의 try ~ catch 구분을 JSTL로 구현 -->
		<%--
		try{
			검증할 내용이 있는 영역
		} catch (Exception e){
			에러 발생시 내용이 출력
		}
		 --%>
		<c:catch var="e">
			검증할 내용이 있는 영역
		</c:catch>
			에러 내용은 ${e }로 빼내서 처리함. 
	</body>
</html>




