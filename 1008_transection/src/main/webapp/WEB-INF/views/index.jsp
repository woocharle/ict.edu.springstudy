<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function result_go(f) {
			f.amount.value = f.countnum.value;
			f.action = "result.do";
			f.submit();
		}
	
	</script>
</head>
<body>
	<h2> 결제하기 </h2>
	
	<form method="post">
		<p> 고객 아이디: <input type="text" name="consumerid"></p>
		<p> 티켓 구매수: <input type="text" name="countnum"></p>
		<input type="hidden" name="amount" value="">
		<input type="button" value="구매하기" onclick="result_go(this.form)">
	</form>
	
</body>
</html>