<%@page import="bit.com.a.util.DateUtil"%>
<%@page import="bit.com.a.model.PollDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%
List<PollDto> plists = (List<PollDto>)request.getAttribute("plists"); 
%>

<!-- 관리자 -->
<c:if test="${login.auth eq '1' }">
<table class="list_table" style="width: 95%">
<col width="50"><col width="50"><col width="300"><col width="50">
<col width="100"><col width="100"><col width="50"><col width="100">
<tr>
<th>번호</th><th>아이디</th><th>질문</th><th>시작일</th>
<th>종료일</th><th>보기 수</th><th>투표 수</th><th>등록일</th>
</tr>

<%
	for (int i = 0; i < plists.size(); i++) {
		PollDto poll = plists.get(i);
		%>
		<tr bgcolor="#aabbcc">
			<td><%=i+1 %></td>
			<td><%=poll.getId() %></td>
			<td><%=poll.getQuestion() %></td>
			<td><%=poll.getSdate() %></td>
			<td><%=poll.getEdate() %></td>
			<td><%=poll.getItemcount() %></td>
			<td><%=poll.getPolltotal() %></td>
			<td><%=poll.getRegdate() %></td>
		</tr>
		<% 
	}
%>


	</table>

</c:if>
<!-- 사용자 -->
<c:if test="${login.auth eq '3' }">
	<table class="list_table" style="width: 95%">
<col width="50"><col width="50"><col width="300"><col width="50"><col width="50">
<col width="100"><col width="100"><col width="50"><col width="100">
<tr>
<th>번호</th><th>아이디</th><th>질문</th><th>결과</th><th>시작일</th>
<th>종료일</th><th>보기 수</th><th>투표 수</th><th>등록일</th>
</tr>
<%
	for(int i = 0;i <plists.size(); i++){
		PollDto poll = plists.get(i);
	%>
		<tr bgcolor="#aabbcc">
		<td><%=i+1 %></td>
		<td><%=poll.getId() %></td>
		<%
		boolean isS = poll.isVote();
		
		// 투표를 못하게 하는 조건
	    if(isS || DateUtil.isEnd(poll.getEdate())){
	   // 투표했음 || 투표기한이 지났다
	   %>
	   <td>[마감]<%=poll.getQuestion() %></td>
	   <% 
		} else{
	%>
		<td>	
			<a href="polldetail.do?pollid=<%=poll.getPollid() %>">
			<%=poll.getQuestion() %></a>
		</td>
	<% 	
	}
	%>
 	<td> 
	<%
	// 결과를 볼 수 있다
	if(isS || DateUtil.isEnd(poll.getEdate())){
	%>
		<a href="pollresult.do?pollid=<%=poll.getPollid() %>">
		결과
		</a>
		
	<% 	
	} else {
		%>
		<img alt="" src="image/pen.gif">
	<%
	}
	%>
	</td>
	<td><%=poll.getSdate()%></td>
	<td><%=poll.getEdate()%></td>
	<td><%=poll.getItemcount()%></td>
	<td><%=poll.getPolltotal()%></td>
	<td><%=poll.getRegdate()%></td>
	</tr>
		
<%
	}
%>
</table>

</c:if>

<c:if test="${login.auth eq '1' }">
<a href="pollmake.do">투표 만들기</a>

</c:if>
