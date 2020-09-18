<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	tr {
		    text-align:center;
		    padding:4px 10px;
		    background-color: #F6F6F6;
		}
		
	th {
			width:120px;
		    text-align:center;
		    padding:4px 10px;
		    background-color: #B2CCFF;
		}
	</style>
	<script type="text/javascript">
		function sendData(f) {	
			if("${vo.pwd}" == f.pwd.value){
				f.action="/MyController?cmd=delete"
				f.submit();
			}else{
				alert("비밀번호가 틀립니다.");
				f.pwd.value="";
				f.pwd.focus();
			}
		
		}
		
		function list_go(f) {	
			f.action="/MyController?cmd=list"
			f.submit();
		}
	</script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<table width="700" align="center">
			<caption  style=" font-size:20px; margin-bottom:10px;">게시판 글 삭제</caption>
			<tbody>
			<tr>
				<th>비밀번호</th>
				<td align="left"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="삭제" onclick="sendData(this.form)" /> 
				<input type="button" value="목록" onclick="list_go(this.form)" /> 
				<input type="hidden" name="idx" value="${vo.idx}" />
				<input type="reset" value="취소" />
				</td>
			</tr>
            </tbody>
		</table>
	</form>
</body>
</html>