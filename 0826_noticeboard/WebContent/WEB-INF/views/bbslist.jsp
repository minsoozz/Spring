<%@page import="java.util.List"%>
<%@page import="bit.com.a.model.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
#hor-minimalist-b
{
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	background: #fff;
	margin: 45px;
	width: 900px;
	border-collapse: collapse;
	text-align: center;

}

#hor-minimalist-b th
{
	font-size: 16px;
	font-weight: normal;
	color: black;
	padding: 10px 8px;
 	border-bottom: 2px solid gray;
 	border-top: 2px solid gray; 
	font-weight: bold;
}

#hor-minimalist-b td
{
	border-bottom: 1px solid #ccc;
	color: black;
	font-size : 13px;
	padding: 13px;
}

#hor-minimalist-b tbody tr:hover td
{
	color: #98dcae;
}

#btn {
position: relative;
    left: -28px;
} 

#search{
position: relative;
       left: 272px;
    bottom: -31px;
	
}
#searchbtn{
	width: 50px;
	height: 28px;
	border-radius: 5px;
	opacity: 0.7;
	position: relative;
    top: -1px;
}
#searchtxt{
	border-radius: 5px;
	border: solid 1px #d6dade;
	padding-left: 10px;
}
select{
	border-radius: 5px;
    border: solid 1px #d6dade;
    color: #808284;
    padding: 2px;
    padding-left: 10px;
}
</style>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
</head>
<body>

<h1><a href="getBoardList">목록으로 돌아가기 ~</a></h1>

<!-- arrow생성 -->
<jsp:useBean id="ubbs" class="bit.com.a.util.BbsArrow"/>

<h3>게시판 목록 로그인 아이디 : ${login.getId() }</h3>
<div align="center">
	<table id="hor-minimalist-b">
	<c:if test="${fn:length(bbslist) <= 0 }">
	<tr>
	<th colspan="5">게시글이 없습니다	</th>
	</tr>
	</c:if>
	<c:if test="${fn:length(bbslist) > 0 }">
	<tr>
	<th>번호</th><th>ID</th><th>제목</th><th>작성일</th><th>조회수</th>
	</tr>
	
	<c:forEach var="board" items="${bbslist}">
	<tr>
		<td>${board.seq}</td>
		<td>${board.id}</td>
		<td>
<!-- depth를 변수에 할당 -->
<jsp:setProperty property="depth" name="ubbs" value="${board.depth }"/>
<!-- setDepth 호출 -->
<jsp:getProperty property="arrow" name="ubbs"/>
		<a href="bbsdetail?seq=${board.seq}">${board.title}</a></td>
		<td>${board.wdate}</td>
		<td>${board.readcount}</td>
	</tr>
	</c:forEach>
	</c:if>
	</table>
	<button type="button" id="write">작성</button><br><br>
<input type="text" id="searchtxt" value="${paging.keyword }"> &nbsp;
<input type="button" value="검색" id="searchbtn"> &nbsp;
<select id="select">
<option value="0" <c:out value="${paging.cond == '0'? 'selected':'' }"/>>제목</option>
<option value="1" <c:out value="${paging.cond == '1'? 'selected':'' }"/>>내용</option>
<option value="2" <c:out value="${paging.cond == '2'? 'selected':'' }"/>>작성자</option>
</select>
</div>
<ul class="pagingNav">
				<c:choose>				 
				    <c:when test="${paging.cond eq '4'}">
				    	<c:forEach var="i" begin="${paging.firstNavIndex }" end="${paging.lastNavIndex }" step="1">
							<li class="pagingNavItem"><c:out value="${i}"/></li>
						</c:forEach>
				    </c:when>				 			 
				    <c:otherwise>
						<c:forEach var="i" begin="${paging.firstNavIndex }" end="${paging.lastNavIndex }" step="1">
							<li class="pagingNavItemWithSearchCond"><c:out value="${i}"/></li>
						</c:forEach>
				    </c:otherwise>				 
				</c:choose>
			</ul>
<script type="text/javascript">
$(function() {
	$("#write").click(function() {
		location.href = "writeAf";
	});
	
	$("#searchbtn").click(function() {

		location.href="getBoardList?pageNum=1&keyword="+$("#searchtxt").val()+"&cond="+$("#select option:selected").val();
		
	});
	
	//검색 조건이 없을 때 페이징 네비게이션
	$(".pagingNavItem").click(function(){
		location.href='getBoardList?pageNum=' + $(this).text();
	});
	
	//검색 조건이 있을 때 페이징 네비게이션
	$(".pagingNavItemWithSearchCond").click(function(){
		var cond = $("#select option:selected").val();
		var keyword = $("#searchtxt").val();
		location.href='getBoardList?pageNum=' + $(this).text() + '&cond=' + cond + '&keyword=' + keyword; 
	});
	
})



</script>
</body>
</html>