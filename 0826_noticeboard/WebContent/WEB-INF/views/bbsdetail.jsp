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
<h1>디테일</h1>
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
<td>${dto.title }</td>
</tr>
<tr>
<th>내용:</th>
<td>${dto.content }</td>
</tr>
<tr>
<td colspan="2">
<button type="button" id="update">수정</button>
<button type="button" id="delete">삭제</button>
<button type="button" id="answer">답글</button>
</td>
</tr>
</table>
<button type="button" id="return">뒤로가기</button>
<script type="text/javascript">
$(function() {
	$("#return").click(function() {
		location.href = "getBoardList";
	});
	
	$("#update").click(function() {
		var seq = ${dto.seq};
		location.href = "updatebbs?seq="+seq;
			
	});
	
	$("#delete").click(function() {
		var seq = ${dto.seq};
		location.href = "deletebbs?seq="+seq;
	});
	
	$("#answer").click(function() {
		var seq = ${dto.seq};
		location.href = "answerbbs?seq="+seq;
	})

})
</script>

<table>
 


</table>


</body>
</html>