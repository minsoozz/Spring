<%@page import="bit.com.a.model.MyClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<h3>도착!</h3>

<!-- 방법 1 -->
<%
MyClass cls = (MyClass)request.getAttribute("mycls");
%>
number:<%=cls.getNumber() %>
name:<%=cls.getName() %>
<br><br><br>


<!-- 방법 2 EL tag -->
number : ${mycls.number }
name : ${mycls.name }
<br><br><br>


<form action="inputData">
번호:<input type="text" name="number"><br>
이름:<input type="text" name="name"><br>
<input type="submit" value="전송">
</form>


<br><br>
<!-- 3 -->
<form action="move">
<input type="submit" value="move">
</form>
<br><br>


<!-- 4 -->
<form method="get"></form>
아이디:<input type="text" id="checkid"><br>
<button type="button" id="_check" onclick="idcheck()">id check</button>

<script type="text/javascript">
function idcheck(){
	$.ajax({
		url:"idcheck",
		type:"get",
		data:"id=" + $("#checkid").val(),
		success:function(data){
			alert(data);
		},
		error:function(r, s, e){
			alert("error : " + r + " " + s + " " + e);
		}
	
	});
}
</script>

<br><br>

<!-- 5 -->
<form action="post">
이름:<input type="text" id="_name"><br>
전화:<input type="text" id="_phone"><br>
메일:<input type="text" id="_email"><br>
생일:<input type="text" id="_birth"><br>
<button type="button" id="account">account</button>
</form>

<script type="text/javascript">
$(document).ready(function() {
	$("#account").click(function() {
		
		var mydata = {
				name:$("#_name").val(),
				tel:$("#_phone").val(),
				email:$("#_email").val(),
				birth:$("#_birth").val()
		};
		
		$.ajax({
			url:"account.do",
			data:mydata,
			type:'post',
			dataType:'json',		// 보내는 데이터
			async:true,  /* 비동기 */
			success:function(resp){
				alert("success");	// 받는 데이터
				alert(resp.msg);
				alert(resp.data);
			
			},
			error:function(error){
				alert("error : " + error);
			}
		});
	})
});

</script>

<br><br>

<!-- 6 -->
이름:<input type="text" id="_name1"><br>
전화:<input type="text" id="_phone1"><br>
메일:<input type="text" id="_email1"><br>
생일:<input type="text" id="_birth1"><br>
<button type="button" id="account1">account</button>

<script type="text/javascript">
$(function() {
	$("#account1").click(function() {
		
		var data = {}; /* 배열의 초기화 */
		
		/* 보내려는 데이터 준비 */
		data["name"] = $("#_name1").val();
		data["tel"] = $("#_phone1").val();
		data["email"] = $("#_email1").val();
	
		var birth = $("#_birth1").val();
		data["birth"] = birth.replace(/-/g,""); // 2019-08-28 -> 20180826
	
		/* 보내는 부분 */
		$.ajax({
			contentType:'application/json', 
			dataType:'json',
			url:'updateUser',
			type:'post',
			data:JSON.stringify(data),		// 보내는 부분 -> Map(컨트롤러)
			success:function(resp){			// Map -> json
				alert("success");
			},
			error:function(e){
				alert("error : " + e);
			}
		});
	});
})
</script>


</body>
</html>