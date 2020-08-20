<%@page import="com.ict.db.DAO"%>
<%@page import="com.ict.db.VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
   request.setCharacterEncoding("utf-8");
   VO vo = new VO();
   vo.setM_id(request.getParameter("m_id"));
   vo.setM_pw(request.getParameter("m_pw"));
   vo.setM_name(request.getParameter("m_name"));
   vo.setM_age(request.getParameter("m_age"));
   
   // DB 처리를 위해서 session에 있는 DAO 호출
   DAO dao = (DAO)session.getAttribute("dao");
   
   int result = dao.getIDU(vo, "Insert");
   pageContext.setAttribute("result", result);
   
 %>   
<c:choose>
 	<c:when test="${result > 0}">
 		<script>
 			alert("삽입성공");
 			location.href = "index.jsp";
 		</script>
 	</c:when>
 	<c:otherwise>
 		<script>
 			alert("삽입실패");
 			history.go(-1);
 		</script>
 	</c:otherwise>
</c:choose>