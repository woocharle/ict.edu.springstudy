<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajax 예제</title>
	<style type="text/css">
		table{width: 80%; margin: 0 auto; width:600px;}
		table, th, td{border: 1px solid black; text-align: center; border-collapse: collapse; }
	</style>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#btn1").click(function() {
				$("#result").empty();
				$.ajax({
					url: "text.do",
					method: "post",
					dataType: "text",
					success: function(data) {
						$("#result").append(data);
					},
					error: function(){
						alert("읽기실패");
					}
					
				});
			});
			
			$("#btn2").click(function() {
				$("#result").empty();
				$.ajax({
					url: "xmltest.do",
					method: "post",
					dataType: "text",
					success: function(data) {
						var table ="<table>";
						table += "<thead><tr><th>종류</th><th>가격</th></tr></thead>"
						table += "<tbody>"
						$(data).find("product").each(function(){
							var name = $(this).find("name").text();
							var price = $(this).find("price").text();
							table += "<tr><td>"+name+"</td><td>"+price+"</td></tr>";
						});
						table += "</body></table>"
						
						
						$("#result").append(table);
					},
					error: function(){
						alert("읽기실패");
					}
					
				});
			});
			
			$("#btn3").click(function() {
				$("#result").empty();
				$.ajax({
					url: "xmltest2.do",
					method: "post",
					dataType: "text",
					success: function(data) {
						var table ="<table>";
						table += "<thead><tr><th>종류</th><th>가격</th></tr></thead>"
						table += "<tbody>"
						$(data).find("product").each(function(){
							var name = $(this).attr("name");
							var price = $(this).attr("price");
							table += "<tr><td>"+name+"</td><td>"+price+"</td></tr>";
						});
						table += "</body></table>"
						
						
						$("#result").append(table);
					},
					error: function(){
						alert("읽기실패");
					}
					
				});
			});
			
			$("#btn4").click(function() {
				$("#result").empty();
				$.ajax({
					url: "xmltest3.do",
					method: "post",
					dataType: "text",
					success: function(data) {
						var table ="<table>";
						table += "<thead><tr><th>종류</th><th>가격</th></tr></thead>"
						table += "<tbody>"
						$(data).find("product").each(function(){
							var name = $(this).text();
							var price = $(this).attr("price");
							table += "<tr><td>"+name+"</td><td>"+price+"</td></tr>";
						});
						table += "</body></table>"
						
						
						$("#result").append(table);
					},
					error: function(){
						alert("읽기실패");
					}
					
				});
			});
			
			// Controller에서 처리 
			$("#btn5").click(function() {
				$("#result").empty();
				$.ajax({
					url: "xmltest4.do",
					method: "post",
					dataType: "text",
					success: function(data) {
						
						$("#result").append(data);
					},
					error: function(){
						alert("읽기실패");
					}
					
				});
			});
			
			// jsp에서 처리
			$("#btn6").click(function() {
				$("#result").empty(); // 초기화
				$.ajax({
					url: "xmltest5.do",
					method : "post",
					dataType : "xml",
					success:function(data){
						var table = "<table><thead><th>지역</th><th>온도</th><th>상태</th><th>아이콘</th></thead>";
						table += "<body>"
						$(data).find("local").each(function() {
							var local = $(this).text();			//텍스트
							var ta = $(this).attr("ta");		//속성
							var desc = $(this).attr("desc");	
							var icon = $(this).attr("icon");
							table += "<tr>";
							table += "<td>"+local+"</td>";
							table += "<td>"+ta+"</td>";
							table += "<td>"+desc+"</td>";
							table += "<td><img src='http://www.kma.go.kr/images/icon/NW/NB"+
										icon+".png'></td></tr>";
							
						});
						
						table += "</body></table>"
						$("#result").append(table);
					},
					error : function(){
						alert("읽기실패");
					}
				});
			});
			
			$("#btn7").click(function() {
				$("#result").empty(); // 초기화
				$.ajax({
					url: "jsontest.do",
					method : "post",
					dataType : "json",
					success:function(data){
						var table = "<table>"
						table += "<thead>";
						table += "<tr><th>종류</th><th>가격</th></tr>"
						table += "</thead>";
						table += "<tbody>";
						$.each(data, function(k,v) {
							table +="<tr>"
							var name = v["name"];
							var price = v["price"];
							table +="<td>"+name+"</td><td>"+price+"</td>";
							table +="</tr>"
						});
						table += "</tbody>";
						table += "</table>"
						$("#result").append(table);
					},
					error : function(){
						alert("읽기실패");
					}
				});
			});
			
			$("#btn8").click(function() {
				$("#result").empty(); // 초기화
				$.ajax({
					url: "jsontest2.do",
					method : "post",
					dataType : "json",
					success:function(data){
						var table = "<table>"
						table += "<thead>";
						table += "<tr><th>도서관이름</th><th>주소</th><th>전화번호</th></tr>"
						table += "</thead>";
						table += "<tbody>";
						$.each(data, function(k, v) {
							var row = v['row'];
							
							$.each(row, function(k,v) {
								table +="<tr>"
								var name = v["LBRRY_NAME"];
								var addr = v["ADRES"];
								var tel_no = v["TEL_NO"];
								table +="<td>"+name+"</td><td>"+addr+"</td><td>"+tel_no+"</td>";
								table +="</tr>"
							});						
							
						});

						table += "</tbody>";
						table += "</table>"
							
						$("#result").append(table);
					},
					error : function(){
						alert("읽기실패");
					}
				});
			});
			
		
		
		
			$("#btn10").click(function() {
				var num1 = Number($("#number1").val());
				var num2 = Number($("#number2").val());
 				var result = num1 + num2; 
				
				$("#result").append(result);
		
				
			});
			
		});
	</script>
</head>
<body>
	<h1> Ajax 예제 </h1>
	<hr>
	<button id="btn1"> 텍스트 </button>
	<button id="btn2"> XML </button>
	<button id="btn3"> XML 속성 </button>
	<button id="btn4"> XML 최종 </button>
	<button id="btn5"> 외부 XML </button>
	<button id="btn6"> 외부 XML2 </button>
	<button id="btn7"> json </button>
	<button id="btn8"> 외부 json </button>
	<button id="btn9"> 외부 json2 </button>
	<button id="btn10"> 사칙연산 </button>
	<input id="number1" type="number">
	<input id="number2" type="number">
	
	<br>
	<hr>
	<div id="result">
	
	
	</div>
</body>
</html>