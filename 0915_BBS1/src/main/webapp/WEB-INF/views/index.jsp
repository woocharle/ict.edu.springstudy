<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		tbody{border: 1px solid black;}
		tfoot{border: 1px solid black;}
		div{margin-top: 50px;}
		input {font-size: 25px;}
		button {font-size: 25px;}
		td {height: 45px;}
		
	</style>
	<script type="text/javascript">
		
		function list_go1(f){
			f.action="login.do?";
			f.submit();
		}
	
		function list_go2() {
			location.href="list.do?cPage=1";
		}
		
	</script>
</head>
<body>
	
	<c:if test="${comment eq 'ok'}">
		<script type="text/javascript">
			alert("비밀번호가 일치하지 않습니다.");
		</script>
	</c:if>

	<div align="center">
		
		<table>
			<form method="post">
			<tbody>
					<tr>
						<td style="width: 30%">아이디</td>
						<td><input type="text" name="id" ></td>
					</tr>
					
					<tr>
						<td>비번</td>
						<td><input type="password" name="password" ></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input type="button" value="로그인" onclick="list_go1(this.form)">
						</td>
					</tr>
			</tbody>
			</form>
			<tfoot>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button  onclick="list_go2(this.form)">비회원 로그인</button>
					</td>
				</tr>
			</tfoot>
		
		</table>	
		
	
	</div>



	
</body>
</html>