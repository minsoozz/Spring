<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<h3>답글 화면 로그인 아이디 : ${login.getId() }</h3>

<form action="answerwriteAf" id="frm" method="post">
<input type="hidden" name="seq" value="${seq }">
<table>
<tr>
<th>작성자:</th>
<td><input type="text" name="id" value="${login.getId() }" readonly="readonly"></td>
</tr>
<tr>
<th>제목:</th>
<td><input type="text" name="title"></td>
</tr>
<tr>
<th>내용:</th>
<td><textarea cols="50" rows="20" name="content"></textarea></td>
</tr>
<tr>
<td colspan="2">
<button type="button" id="btn">작성</button>
<button type="button" id="return">뒤로가기</button>
</td>
</tr>
</table>
</form>

<script type="text/javascript">
$(document).ready(function() {
	$("#btn").click(function() {
		$("#frm").submit();
	});
	$("#return").click(function() {
		location.href = "getBoardList";
	});
})
</script>
</body>
</html>