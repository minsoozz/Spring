<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateAf">
<input type="hidden" name="seq" value="${dto.seq }" >
<table border="1" id="tid">
<col width="300"><col width="300">
<tr>
<th>작성자:</th>
<td>${dto.id }</td>
</tr>
<tr>
<th>조회수:</th>
<td>${dto.readcount }</td>
</tr>
<tr>
<tr>
<th>작성시간:</th>
<td>${dto.wdate }</td>
</tr>
<tr>
<th>제목:</th>
<td><input type="text" name="title"></td>
</tr>
<tr>
<th>내용:</th>
<td><input type="text" name="content"></td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="수정">
</td>
</tr>
</table>
</form>
</body>
</html>