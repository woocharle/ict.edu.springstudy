<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>카카오 로그인</title>
</head>
<body>
   <!-- 제품설정 : RedirectURl 지정해야 됨 -->
   <a href="https://kauth.kakao.com/oauth/authorize?client_id=3b4a6321cd8a2b5b3c9ab33e7ed7e322&redirect_uri=http://localhost:8090/login.do&response_type=code">
            <img src="resources/img/kakao_login_btn_medium_narrow.png">
    </a>
</body>
</html>