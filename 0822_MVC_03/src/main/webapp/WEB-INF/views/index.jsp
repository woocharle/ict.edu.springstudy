<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function say_go() {
			location.href="say.do";
		}
	</script>
</head>
<body>
	<button onclick="say_go()">인사하기</button>
</body>
</html>