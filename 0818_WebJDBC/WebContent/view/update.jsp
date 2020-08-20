<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div{text-align: center; margin: 0px auto;}
		table{width: 600px; margin: 0px auto; 
			border-collapse: collapse; text-align: center;}
		table, th, td {border: 1px solid black;}
		h1{text-align: center;}
		a{text-decoration: none;}
	</style>
	<script type="text/javascript">
		function update_go(f) {
			f.action = "update_ok.jsp"
			f.submit();	
		}
	</script>	
	
</head>

<body>
	<div>
		<h1> 수정하기 </h1>
		<p>[<a href="index.jsp">목록으로 가기</a>]</p>
		<form method="post">
			<fieldset style="width:700px; margin: 0px auto;">
				<legend> 회원가입 </legend>
				<table>
					<tbody>
						<tr> 
						   <td> 번호 </td> 
						   <td> <input type="text" value="${vo.idx}" disabled></td> 
						 </tr>
						<tr> 
						   <td> 아이디 </td> 
						   <td> <input type="text" name="m_id" value="${vo.m_id}"></td> 
						 </tr>
						 <tr> 
						   <td> 이름 </td> 
						   <td> <input type="text" name="m_name" value="${vo.m_name}"></td> 
						 </tr>
						 <tr> 
						   <td> 나이 </td> 
						   <td> <input type="number" name="m_age" value="${vo.m_age}"></td>

					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
							<input type="button" onclick="update_go(this.form)" value="수정"> 
							<input type="reset" value="취소"> 
								</td>	
							</tr>
						</tfoot>
					</table>
				</fieldset>
			</form>
		</div>
</body>
</html>