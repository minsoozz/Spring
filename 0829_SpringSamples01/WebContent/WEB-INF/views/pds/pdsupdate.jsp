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
<h1>수정</h1>
<c:choose>
<c:when test="${dto.id.trim() == login.id.trim() }">
<script type="text/javascript">
	$(function() {
		$("#update").show();
		$("#delete").show();
	})
</script>
</c:when>
<c:when test="${dto.id.trim() != login.id.trim() }">
	<script type="text/javascript">
	$(function() {
		$("#update").hide();
		$("#delete").hide();
	})
</script>
</c:when>
</c:choose>
<form id="_frm" action="pdsupdateAf.do"  method="post" enctype="multipart/form-data">
<input type="hidden" name="filename" value="${dto.filename }">
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
<th>제목:</th>
<td><input type="text" name="title" value="${dto.title }"></td>
</tr>
<tr>
<th>기존파일:</th>
<td>${dto.filename }</td>
</tr>
<tr>
<th>새로운파일:</th>
<td><input type="file" name="file2"></td>
</tr>
<tr>
<th>내용:</th>
<td><textarea name="content">${dto.content }</textarea></td>
</tr>
<tr>
<td colspan="2">
<button type="button" id="update2">완료</button>
<button type="button" id="delete">삭제</button>
</td>
</tr>
</table>
</form>

<button type="button" id="return">뒤로가기</button>
<script type="text/javascript">
$(function() {
	$("#return").click(function() {
		location.href = "pdslist.do";
	});
	
	$("#update2").click(function() {	
			$("#_frm").submit();
	});
	
})
</script>

<table>
 


</table>


</body>
</html>