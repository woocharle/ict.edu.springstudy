<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		function write_go(f) {
			for (var i = 0; i < f.elements.length; i++) {
				if(i == 4){continue;}
				if(f.elements[i].value==""){

					alert(f.elements[i].name+"를 입력하세요");
					f.elements[i].focus();
					return;
				}
			}

			f.action="write_ok.do";
			f.submit();
		}
	</script>	
	
	
</head>
<body>
	<div align="center">
		<h2>방명록: 추가</h2>
		<p>[<a href="list.do"> 목록으로 가기 </a>]</p>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr align="center">
						<td bgcolor="#99ccff">*작성자</td>
						<td><input type="text" name="name" size ="20"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*제  목</td>
						<td><input type="text" name="subject" size ="20"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*email</td>
						<td><input type="text" name="email" size ="20"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">*비밀번호</td>
						<td><input type="password" name="pwd" size ="20"/></td>
					</tr>
					<tr align="center">
						<td bgcolor="#99ccff">첨부파일</td>
						<td><input type="file" name="filename" size="20"></td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<textarea rows="10" cols="60" name="content"></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="저장" onclick="write_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	
	</div>
	
</body>
</html>