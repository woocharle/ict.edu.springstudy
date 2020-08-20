<%@page import="com.ict.edu01.Welcome"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		WebApplicationContext context = 
			WebApplicationContextUtils.getWebApplicationContext(application);	
	
		Welcome welcome = (Welcome)context.getBean("welcome");
		out.print("<h2>"+welcome.sayWelcome()+"</h2>");
	
	
	%>
</body>
</html>