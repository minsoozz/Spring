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
<h3>글 쓰기 화면 로그인 아이디 : ${login.getId() }</h3>

<form action="bbswriteAf.do" id="frmWrite">
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
<td colspan="2"><button type="button" id="btnWrite">작성</button></td>
</tr>
</table>
</form>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnWrite").click(function() {
		$("#frmWrite").submit();
	});
})
</script>
</body>
</html>