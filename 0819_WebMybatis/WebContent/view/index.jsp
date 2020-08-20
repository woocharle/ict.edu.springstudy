<%@page import="com.ict.db.VO"%>
<%@page import="java.util.List"%>
<%@page import="com.ict.db.DAO"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	WebApplicationContext context = 
		WebApplicationContextUtils.getWebApplicationContext(application);
	
	DAO dao = (DAO)context.getBean("dao");
	List<VO> list = dao.getList();
	
	pageContext.setAttribute("list", list);
	session.setAttribute("dao", dao);
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div{text-align: center; margin: 0px auto;}
		table{width: 600px; margin: 0px auto;
			 border-collapse: collapse; text-align: center;}
		table, th, td {border: 1px solid black;}	
	</style>
</head>
<body>
	<div>
		<h1> 전체 정보보기 </h1>
		<p>[<a href="insert.jsp">인원 추가</a>]</p>
		
		<table>
			<thead>
				<tr>
					<td>idx</td><td>m_id</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr><td colspan="2"><h3>정보가 없습니다.</h3></td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${list}">
							<tr>
								<td>${k.idx}</td>
								<td><a href="onelist.jsp?idx=${k.idx}">${k.m_id}</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			
		
		</table>
			
	</div>
</body>
</html>