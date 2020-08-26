<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function add_go() {
			location.href="add.do";
		}	
	
		function sub_go() {
			location.href="sub.do";
		}	
	
		function mul_go() {
			location.href="mul.do";
		}	
	
		function div_go() {
			location.href="div.do";
		}	
	
		function add_go2() {
			location.href="calc.ict?cmd=1";
		}	
	
		function sub_go2() {
			location.href="calc.ict?cmd=2";
		}	
	
		function mul_go2() {
			location.href="calc.ict?cmd=3";
		}	
	
		function div_go2() {
			location.href="calce.ict?cmd=4";
		}	
	
	</script>
</head>
<body>
	<h2> 사칙 연산</h2>
	<h3>
		<button onclick="add_go()"> 더하기 </button>
		<button onclick="sub_go()"> 빼기   </button>
		<button onclick="mul_go()"> 곱하기 </button>
		<button onclick="div_go()"> 나누기 </button>
	</h3>
	<br>
	<hr>
	<h3>
		<button onclick="add_go2()"> 더하기 </button>
		<button onclick="sub_go2()"> 빼기   </button>
		<button onclick="mul_go2()"> 곱하기 </button>
		<button onclick="div_go2()"> 나누기 </button>
	</h3>
	<br>
</body>
</html>