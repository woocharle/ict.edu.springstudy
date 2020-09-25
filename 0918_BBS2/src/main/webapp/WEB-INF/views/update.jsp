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
			f.action="update_ok.do"
			f.submit();
		}else{
			alert("비밀번호가 틀립니다.");
			f.pwd.value="";
			f.pwd.focus();
		}
	
	}
	
	function list_go() {	
		location.href="list.do?cPage="+f.cPage.value;
	}
	
</script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<table width="700" align="center">
			<caption  style=" font-size:20px; margin-bottom:10px;">게시판 글 수정</caption>
			<tbody>
				<tr>
					<th>작성자</th>
					<td align="left"><input type="text" name="writer" value="${vo.writer}"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td align="left"> <input type="text" name="title" value="${vo.title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						 <script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
						 <textarea name="content">${vo.content}</textarea>
	               		 <script>
	                       	CKEDITOR.replace( 'content' );
	               		</script>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td align="left"><input type="file" name="file"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td align="left"><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="수정" onclick="sendData(this.form)" /> 
						<button onclick="list_go()"> 목록 </button> 
						<input type="reset" value="취소" />
						<input type="hidden" name="f_name" value="${vo.filename}" />
						<input type="hidden" name="idx" value="${vo.idx}" />
					</td>
				</tr>
	            </tbody>
		</table>
	</form>
</body>
</html>