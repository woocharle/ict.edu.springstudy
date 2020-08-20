<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.ict.edu01.Hello"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1> Spring DI 연습 </h1>
	
	<%-- <%
		ApplicationContext context = 
			new ClassPathXmlApplicationContext("com/ict/edu01/config.xml");		
		Hello hello = (Hello)context.getBean("hello");
		
		out.print("<h2>"+ hello.sayHello() +"</h2>");%> --%>
		
	<%
		WebApplicationContext context = 
			WebApplicationContextUtils.getWebApplicationContext(application);
	
		Hello hello = (Hello)context.getBean("hello");
		out.print("<h2>"+hello.sayHello()+"</h2>");
	%>
	
	
</body>
</html>