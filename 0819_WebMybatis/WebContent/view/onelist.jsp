<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.ict.db.VO"%>
<%@page import="java.util.List"%>
<%@page import="com.ict.db.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("UTF-8");
	WebApplicationContext context = 
	WebApplicationContextUtils.getWebApplicationContext(application);
	DAO dao = (DAO)context.getBean("dao");
	

	String idx = request.getParameter("idx");
	VO vo = dao.getOne(idx);
	 
	session.setAttribute("vo", vo);
	session.setAttribute("idx", idx);
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 상세 보기 </title>
	<style type="text/css">
		div{text-align: center; margin: 0px auto;}
		table{width: 600px; margin: 0px auto; 
			border-collapse: collapse; text-align: center;}
		table, th, td {border: 1px solid black;}
		h1{text-align: center;}
		a{text-decoration: none;}
	</style>
	<script type="text/javascript">
		function update_go(f) {
			if(f.pass.value == "${vo.m_pw}"){
				f.action = "update.jsp"
				f.submit();
				
			}else{
				alert("비밀번호가 틀렸습니다.");
				f.pw.value="";
				f.pw.focus();
				return;
			}
		
		}
		
		function delete_go(f) {
			if(f.pass.value == "${vo.m_pw}"){
				var chk = confirm("정말 삭제할까요?");
				if(chk){				
					f.action = "delete.jsp"
					f.submit();
				} else{
					return;
				}
				
			}else{
				alert("비밀번호가 틀렸습니다.");
				f.pw.value="";
				f.pw.focus();
				return;
			}
		}
		
	</script>
</head>
	<body>
		<div>
			<h1> 전체 정보보기 </h1>
			<p>[<a href="index.jsp">목록으로 가기</a>]</p>
			<form method="post">
				<fieldset style="width:700px; margin: 0px auto;">
					<legend> 회원가입 </legend>
					<table>
						<tbody>
							<tr> 
							   <td> 아이디 </td> 
							   <td> ${vo.m_id} </td> 
							 </tr>
							 <tr> 
							   <td> 이름 </td> 
							   <td> ${vo.m_name} </td> 
							 </tr>
							 <tr> 
							   <td> 나이 </td> 
							   <td> ${vo.m_age} </td> 
							 </tr>
							 <tr> 
							   <td> 시간 </td> 
							   <td> ${vo.m_reg} </td> 
							 </tr>
							 <tr>
							 	<td>비밀번호</td>
							 	<td><input type="password" name="pass"></td>
							 </tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
								<input type="button" onclick="update_go(this.form)" value="수정"> 
								<input type="button" onclick="delete_go(this.form)" value="삭제"> 
								</td>	
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>