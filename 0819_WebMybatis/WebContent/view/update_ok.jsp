<%@page import="com.ict.db.DAO"%>
<%@page import="com.ict.db.VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	request.setCharacterEncoding("utf-8");
	VO vo = new VO();
	String idx = (String)session.getAttribute("idx");
	
	vo.setIdx(idx);
	vo.setM_id(request.getParameter("m_id"));
	vo.setM_name(request.getParameter("m_name"));
	vo.setM_age(request.getParameter("m_age"));
	
	DAO dao = (DAO)session.getAttribute("dao");
	
	int result = dao.getIDU(vo, "Update");
	pageContext.setAttribute("result", result);	
	session.setAttribute("vo", vo);
	
%>   
<c:choose>
	<c:when test="${result > 0}">
		<script>
			alert("갱신성공");
			location.href = "onelist.jsp?idx=${vo.idx}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("갱신실패");
			history.go(-1);
		</script>
	</c:otherwise>
</c:choose>
