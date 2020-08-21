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
	<h1> 호러물과 영웅들 </h1>
	
	<h2>악당들</h2>
	<h3>
		<c:forEach var="k" items="${horror}">
			<li>${k}</li>
		</c:forEach>
	</h3>
	
	<h2>영웅들</h2>
	<h3>
		<c:forEach var="k" items="${hero}">
			<li>${k}</li>
		</c:forEach>
	</h3>
	
	
	
</body>
</html>