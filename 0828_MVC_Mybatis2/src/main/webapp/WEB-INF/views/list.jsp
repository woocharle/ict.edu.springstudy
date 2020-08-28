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
						 	<td>${((paging.nowPage-1)*paging.numPerpage + vs.index)+1}</td>
							<td>${k.name}</td>
							<td><a href="onelist.do?idx=${k.idx}">${k.subject}</a></td>
							<td>${k.regdate.substring(0,10)}</td>
						 </tr>
						</c:forEach>
					</c:otherwise>
					
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<!-- 이전 -->
							<c:choose>
								<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
									<span style="color:lightgray;">이전으로&nbsp;&nbsp;</span>						
								</c:when>
								<c:otherwise>
									<a href="list.do?cPage=${paging.beginBlock-paging.pagePerBlock}"><span style="color:black;">이전으로&nbsp;&nbsp;</span></a>
								</c:otherwise>
							</c:choose>
							
							<!-- 페이지 번호 -->
							<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
								<!-- 현재페이지이냐, 아니냐 구분 -->
								<c:choose>
									<c:when test="${k == paging.nowPage }">
										<font style="font-weight: bold;">${k}</font>
									</c:when>
									<c:otherwise>
										<a href="list.do?cPage=${k}"><font style="font-weight: bold; color: tomato">${k}</font></a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<!-- 이후-->
							<c:choose>
								<c:when test="${paging.endBlock >= paging.totalBlock}">
									<span style="color:lightgray;">&nbsp;&nbsp;다음으로</span>						
								</c:when>
								<c:otherwise>
									<a href="list.do?cPage=${paging.beginBlock+paging.pagePerBlock}"><span style="color:black;">&nbsp;&nbsp;다음으로</span></a>
								</c:otherwise>
							</c:choose>					
						</td>
					</tr>						
				</tfoot>			
			</table>
		</form>
	</div>
</body>
</html>