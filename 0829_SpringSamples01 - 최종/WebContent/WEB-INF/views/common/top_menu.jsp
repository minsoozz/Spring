<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="top_menu_wrap">
	<div id="_top_menu">
		<ul class="navi">
			<li><a href="bbslist.do" title="HOME">HOME</a></li>
			<li><a href="bbslist.do" title="답변형 게시판">게시판</a></li>
			<li><a href="Calendar.do" title="달력">달력</a></li>
			<li><a href="pdslist.do" title="자료실">자료실</a></li>
			<li><a href="polllist.do" title="투표">투표</a></li>
			<li><a href="chatting.do" title="채팅">채팅</a></li>
			<li><a href="yutube.do" title="유튜브">유튜브</a></li>
			
			<c:if test="${login.auth eq '1' }">
				<li class="menu_item"><a href="pollmake.do" title="투표만들기">투표만들기</a>
			</c:if>
			
		</ul>
	</div>

</div>
