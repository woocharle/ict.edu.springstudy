<%@page import="com.ict.db.DAO"%>
<%@page import="com.ict.db.VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
   	request.setCharacterEncoding("utf-8");
	String idx = (String)session.getAttribute("idx");

	VO vo = new VO();
	vo.setIdx(idx);
   
   	// DB 처리를 위해서 session에 있는 DAO 호출
   	DAO dao = (DAO)session.getAttribute("dao");
   
  	int result = dao.getIDU(vo, "Delete");
   	pageContext.setAttribute("result", result);
   
 %>   
<c:choose>
 	<c:when test="${result > 0}">
 		<script>
 			alert("삭제성공");
 			location.href = "index.jsp";
 		</script>
 	</c:when>
 	<c:otherwise>
 		<script>
 			alert("삭제실패");
 			history.go(-1);
 		</script>
 	</c:otherwise>
</c:choose>