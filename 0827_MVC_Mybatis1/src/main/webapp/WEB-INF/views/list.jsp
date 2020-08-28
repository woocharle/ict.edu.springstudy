<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		a {text-decoration: none;}
		table, td, th{border:1px solid black; padding:3px;}
		table {width: 800px; border-collapse: collapse; text-align: center;}
	
	</style>
</head>
<body>
	<div align="center">
		<h2>방명록</h2>
		<p>[<a href="write.do"> 방명록 쓰기 </a>]</p>
		<form method="post">
			<table>
				<thead>
					<tr bgcolor="#99ccff">
						<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
					<c:when test="${empty list}">
						<td colspan="4"> 데이터가 없음. </td>	
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${list}" varStatus="vs">
						 <tr>
							<td>${k.idx}</td>
							<td>${k.name}</td>
							<td><a href="onelist.do?idx=${k.idx}">${k.subject}</a></td>
							<td>${k.regdate.substring(0,10)}</td>
						 </tr>
						</c:forEach>
					</c:otherwise>
					
					</c:choose>
				</tbody>			
			</table>
		</form>
	</div>
</body>
</html>