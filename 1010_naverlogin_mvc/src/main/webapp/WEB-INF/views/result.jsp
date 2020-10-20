<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#res").empty();
		$.ajax({
			url : "member.do",
			method : "post",
			dataType : "json",
			success : function(data) {
				var name = "";
				var email = "";
				$.each(data, function(k, v) {
					var response = data['response'];
					$.each(response, function(k, v) {
						name = response["name"];
						email = response["email"];
					});
				});
				$("#res").append(name + ", " + email + "님 환영합니다.");
			},
			error : function() {
				alert("읽기실패");
			}
		});
	});
</script>
</head>
<body>
	<h1>결과 보기</h1>
	<hr>
	<div id="res"></div>
</body>
</html>