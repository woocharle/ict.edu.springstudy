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
	<h2>결과보기</h2>
	<p>${res}</p>
	<c:choose>
		<c:when test="${res >= 2}">
			<ul>
				<li>${vo.consumerid} 고객님</li>
				<li>${vo.amount} 자리결재 했습니다.</li>
				<li>${vo.countnum} 개 티켓을 구매했습니다.</li>
				
			</ul>
		</c:when>
		<c:otherwise>
			<h2>결제가 취소되었습니다.</h2>
		</c:otherwise>
	
	</c:choose>
</body>
</html>