<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
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

<form action="HomeController">
<div id="box1"> <!-- 큰네모 -->

<div id="box2"> 
<h1>Login</h1>
<input type="text" class="infor"  placeholder="아이디">
<input type="password" class="infor" placeholder="비밀번호">
<input type="submit" class="button" value="로그인">
 <hr>
 <p>아직 계정이 없으신가요? <a class="fpwd" href="account">회원가입</a></p>
</div>
</form>


</body>
</html>