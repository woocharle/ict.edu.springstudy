<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function send_go(f) {
			f.action = "play.do"
			f.submit();
		}
	
	</script>
</head>
<body>
	<form method="post">
		<p> 수1: <input type="number" size="15" name="s1"> </p>
		<p> 수2: <input type="number" size="15" name="s2"> </p>
		<p> 연산자:
			<input type="radio" name="op" value="+"> +
			<input type="radio" name="op" value="-"> -
			<input type="radio" name="op" value="x"> *
			<input type="radio" name="op" value="/"> /
		</p>
		<p>
			취미: 
			<input type="checkbox" name="hobby" value="축구"> 축구
			<input type="checkbox" name="hobby" value="야구"> 야구
			<input type="checkbox" name="hobby" value="배구"> 배구
			<input type="checkbox" name="hobby" value="농구"> 농구
		</p>
		
		<input type="hidden" name="cPage" value="12">
		
		<p> <input type="button" value="보내기" onclick="send_go(this.form)"> </p>
		
	
	</form>

</body>
</html>