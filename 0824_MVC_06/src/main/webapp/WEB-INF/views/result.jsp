<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>결과 보기</h1>
		<h2>계산결과: ${s1} ${op} ${s2} = ${res}</h2>
		<h2>히든: ${cPage}</h2>
		<h3>취미: &nbsp;
			<c:forEach var="k" items="${hobby}">
				${k} &nbsp;&nbsp;
			</c:forEach>
		
		</h3>
		
	</div>
	
	<div>
		<h1>결과 보기</h1>
		<h2>계산결과: ${mvo.s1} ${mvo.op} ${mvo.s2} = ${res}</h2>
		<h2>히든: ${mvo.cPage}</h2>
		<h3>취미: &nbsp;
			<c:forEach var="k" items="${mvo.hobby}">
				${k} &nbsp;&nbsp;
			</c:forEach>
		
		</h3>
	</div>
	
	
</body>
</html>