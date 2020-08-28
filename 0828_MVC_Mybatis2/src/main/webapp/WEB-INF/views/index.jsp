<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function list_go() {
			location.href="list.do?cPage=1";
		}
		
	</script>
</head>
<body>
	<button onclick="list_go()">방명록 리스트</button>
</body>
</html>