<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	tr {
		text-align: center;
		padding: 4px 10px;
		background-color: #F6F6F6;
	}
	
	th {
		text-align: center;
		padding: 4px 10px;
		background-color: #B2CCFF;
	}
	
	h2{text-align: center;}
	table{margin: 0px auto;}
	</style>
	<script type="text/javascript">
		function write_go(){
			location.href = "write.do";
		}
	
	</script>
</head>
<body>
	<h2>BBS 리스트</h2>
	<table width="800px" >
		<thead>
			<tr>
				<th width="10%">번호</th>
				<th>제목</th>
				<th width="25%">글쓴이</th>
				<th width="20%">날짜</th>
				<th width="10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="5">
							<h3>원하시는 자료가 존재하지 않습니다.</h3>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="vs">
						<tr>
							<td>${paging.totalRecord - ((paging.nowPage-1)*paging.numPerpage + vs.index)}</td>
							<td style="width:40%; text-align: left; padding-left: 20px;">
								<c:forEach begin="1" end="${k.step}">
									&nbsp;&nbsp;[RE]
								</c:forEach>
								<a href="onelist.do?idx=${k.idx}&cPage=${paging.nowPage}">${k.title}</a>
							</td>
							<td>${k.writer}</td>
							<td>${k.regdate.substring(0,10)}</td>
							<td>${k.hit}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<!-- 이전 -->
					<c:choose>
						<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
							<span style="color:lightgray;">이전으로&nbsp;&nbsp;</span>						
						</c:when>
						<c:otherwise>
							<a href="list.do?&cPage=${paging.beginBlock-paging.pagePerBlock}"><span style="color:black;">이전으로&nbsp;&nbsp;</span></a>
						</c:otherwise>
					</c:choose>
					
					<!-- 페이지 번호 -->
					<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
						<!-- 현재페이지이냐, 아니냐 구분 -->
						<c:choose>
							<c:when test="${k == paging.nowPage }">
								<font style="font-weight: bold;">${k}</font>
							</c:when>
							<c:otherwise>
								<a href="list.do?&cPage=${k}"><font style="font-weight: bold; color: tomato">${k}</font></a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<!-- 이후-->
					<c:choose>
						<c:when test="${paging.endBlock >= paging.totalBlock}">
							<span style="color:lightgray;">&nbsp;&nbsp;다음으로</span>						
						</c:when>
						<c:otherwise>
							<a href="list.do?&cPage=${paging.beginBlock+paging.pagePerBlock}"><span style="color:black;">&nbsp;&nbsp;다음으로</span></a>
						</c:otherwise>
					</c:choose>					
				</td>
				<td>
					<input type="button" value="글쓰기" onclick="write_go()"/>
				</td>
			</tr>
		
		</tfoot>

	</table>
</body>
</html>