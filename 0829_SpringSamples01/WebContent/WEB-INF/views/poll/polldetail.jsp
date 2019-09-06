<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<form action="polling.do" method="post">

<table class="list_table" style="width: 95%">
	<colgroup>
		<col width="200px">
		<col width="500px">
	</colgroup>

<tr>
	<th>투표번호</th>
	<td style="text-align: left">
		<input type="text" name="pollid" value="${poll.pollid }"
			readonly="readonly">
	</td>
</tr>
<tr>
	<th>아이디</th>
	<td style="text-align: left">
		<input type="text" name="id" value="${login.id }"
			readonly="readonly">
	</td>
</tr>

<tr>
	<th>투표기한</th>
	<td style="text-align: left;">
		${poll.sdate } ~ ${poll.edate }
	</td>
</tr>

<tr>
	<th>투표내용</th>
	<td style="text-align: left;">
		<textarea rows="10" cols="30" readonly="readonly"
		name="question">${poll.question }</textarea>
	</td>
</tr>

<tr>
	<th>투표 보기 수</th>
	<td style="text-align: left;">
		${poll.itemcount }
	</td>
</tr>

<tr>
	<th>투표보기</th>
	<td style="text-align: left;">
		<c:forEach items="${pollsublist }" var="psub" varStatus="vs">
			${vs.count }번:
			<input type="radio" name="pollsubid"
			${vs.count == 1?"checked='checked'":"" }
			value="${psub.pollsubid }">
			
			<input type="text" name="answer" 
			 value="${psub.answer }" readonly="readonly">
			 <br>
		</c:forEach>
	</td>
</tr>

<tr align="center">
	<td colspan="2">
		<input type="submit" value="투표">
	</td>
</tr>

</table>

</form>
<script type="text/javascript">
	
</script>
