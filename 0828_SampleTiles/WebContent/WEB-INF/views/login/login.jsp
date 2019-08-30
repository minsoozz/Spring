<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
body{
font-family: 'Open Sans', sans-serif;
width:100%; 
text-align:center;
/* background: url("bg.jpg"); */
background-size: 100%;
background-repeat: no-repeat;
 margin: 120px 0px 150px 0px; 

}
#box1{
 background:rgba(255,255,255,0.5);
 margin: 0 auto ;
 width:400px;
 height: 400px;
 text-align: center;
 border: solid 2px;
 border-color: #6D6AB7;


}
#box2{

 width:300px;
 margin:  auto;
 padding:10px 0px 20px 0px;
}
.infor{
width:300px;
height:40px;
color:black;
margin-top:15px;
font-size:1em;
}
.button{
margin-top:15px;
width:300px;
height:50px;
font-size:1.5em;
background-color: #494693;
color: white;
}
h1{

font-size:2.5em;
color: #6B66FF;
font-family: Verdana, sans-serif;
}
p{
font-size:1px;
text-align: right;
margin-right: 2px;
color: #353535;
}

</style>   
</head>
<body>
<c:if test="${not empty login }"> <!-- 로그인 되어있을때 -->
${login.id }님 안녕하세요

<table border="1" style="background-color: gray;">
<col width="200">
<tr>
   <td>
      <a href="getBoardList.do">글목록</a>
   </td>
</tr>
<tr>
   <td>
      <a href="bbswrite.do">글쓰기</a>
   </td>
</tr>


</table>

</c:if>

<c:if test="${ empty login }"> <!-- 로그인 되어있을때 -->

<form action="getBoardList.do" id="frm" method="post">
<div id="box1"> <!-- 큰네모 -->
<div id="box2"> 
<h1>Login</h1>
<input type="text" id="id" class="infor"  name="id" placeholder="아이디">
<input type="password" id="pwd" class="infor" name="pwd" placeholder="비밀번호">
<input type="button" class="button" id="btn" value="로그인">
 <hr>
 <p>아직 계정이 없으신가요? <a class="fpwd" href="account">회원가입</a></p>
</div>
</div>
</form>
</c:if>
<script type="text/javascript">
$(document).ready(function() {
		$("#btn").click(function() {
			
			var userid =  $("#id").val();
			var userpwd = $("#pwd").val();
			
			var dto = {
					id:userid,
					pwd:userpwd	
			};
			
			
			$.ajax({
				//contentType:'application/json', /* 문자열로 바꾸어 줄 때 필요하다 */
				dataType:'json',
				url:'logincheck.do',
				type:'post',
				data: dto,
				success :function(data){
					if(data.logincheck > 0){
						alert("안녕하세요 ! ");	
						$("#frm").submit();
					} else {
						alert("아이디와 비밀번호가 틀렸습니다");
					}
				},
				error : function () {
					alert("실패");
				}
			});
		});
});
</script>

</body>
</html>