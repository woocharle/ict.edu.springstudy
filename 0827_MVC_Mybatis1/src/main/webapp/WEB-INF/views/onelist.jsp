<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		a { text-decoration: none;}
		table{width: 500px; border-collapse:collapse; }
		table,tr,td{border: 1px solid black; padding: 3px}
	</style>	
	<script type="text/javascript">
		function update_go(f) {
			if(f.pwd.value == f.pass.value){
				f.action="update.do";
				f.submit();
			}else{
				alert("비밀번호를 잘못 입력 하셨습니다.");
				f.pw.value="";
				f.pw.focus();
				return;
			}
		}
		
		function delete_go(f) {
			if(f.pwd.value == f.pass.value){
				f.action="delete.do";
				f.submit();
			}else{
				alert("비밀번호를 잘못 입력 하셨습니다.");
				f.pw.value="";
				f.pw.focus();
				return;
			}
		}
		
	</script>	
	
	
</head>
<body>
	<div align="center">
		<h2>방명록: 추가</h2>
		<p>[<a href="list.do"> 목록으로 가기 </a>]</p>
		<form method="post">
			<table>
				<tbody>
					<tr align="center">
						<td bgcolor="#99ccff">*작성자</td>
						<td><input type="text" name="name" size ="20"  value="${vo.name}"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*제  목</td>
						<td><input type="text" name="subject" size ="20" value="${vo.subject}"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*email</td>
						<td><input type="text" name="email" size ="20" value="${vo.email}"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*비밀번호</td>
						<td><input type="password" name="pwd" size ="20"/>
							<input type="hidden" name="pass" value="${vo.pwd}">
							<input type="hidden" name="idx" value="${vo.idx}">
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<textarea rows="10" cols="60" name="content" >${vo.content}</textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="수정" onclick="update_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="삭제"  onclick="delete_go(this.form)" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	
	</div>
	
</body>
</html>