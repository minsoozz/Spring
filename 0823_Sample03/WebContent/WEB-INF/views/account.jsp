<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
font-family: 'Open Sans', sans-serif;
text-align:center;
background-size: 100%;
 margin: 80px 0px 120px 0px; 

}
#box1{
 background:white;
 margin: 0 auto ;
 width:600px;
 height: 900px;
 text-align: center;
 border: solid 1px;
 border-color: gray;


}
#box2{
 width:450px;
 margin:  auto;
 text-align:left;
 padding:10px 0px 40px 60px;
}
.infor{
width:350px;
height:40px;
color:black;
margin-bottom:15px;
font-size:1em;

}
.button{
margin-top:15px;
width:350px;
height:50px;
font-size:1.5em;
background-color: #ff6f61;
color: white;
}
h1{

font-size:2.5em;
color: #ff6f61;
font-family: Verdana, sans-serif;
}
p{
font-size:1.5ex;
font-weight:bolder;
text-align: left;
margin-right: 4px;
color: #353535;
}
#conf{  /* 중복확인 버튼 */
width:65px;
height:40px;
background-color: #ff6f61;
color: white;
}
#call1{ /* 전화번호 앞부분 */
width:70px;
height:45px;
}
#call2{  /* 전화번호 뒷부분 */
width:270px;
height:40px;
}
.birth{      /* 생일 */
width:110px;
height:40px;
}
#birth{      /* 생일 월 선택부분*/
width:110px;
height:45px;
}

</style> 
</head>
<body>

<div id="box1"> <!-- 큰네모 -->
<h1>회원가입</h1>

<!-- form 에서 넘길 값이 Dto의 변수 명과 일치하여야 한다 -->


<div id="box2"> 
<p>아이디</p>
<form action="accountAf" method="post">
<input type="text" class="infor"  name="id" placeholder="아이디">
<input type="button" id="conf" value="중복확인">
<p>비밀번호</p>
<input type="password" class="infor" name="pwd" placeholder="비밀번호(4~10자리)">
<p>이름</p>
<input type="text" class="infor"  name="name" placeholder="이름">

<p>이메일</p>
<input type="text" class="infor" id="email" name="email" placeholder="이메일">
<input type="submit" class="button" value="가입하기">
</form>


</div>
</div>

</body>
</html>