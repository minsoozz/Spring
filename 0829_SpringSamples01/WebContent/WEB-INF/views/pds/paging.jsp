<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
 [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] 
 -->    
 
 <%
 	int totalRecordCount; 	// 전체 글 수 23 -> 3 페이지	총 ~~~~ 글 수
 	int pageNumber;			// 현재 페이지 0 ~ 9 [1] ~ [10]
	int pageCountPerScreen; // 스크린당 페이지 수 (버튼갯수들..인거같음) = 10
	int recordCountPerPage; // 페이지 당 글 수 (글의 개수를 몇개로 할 것인가?) = 10 
 
	String st1 = request.getParameter("totalRecordCount");
	if(st1 == null)	// 첫번째 페이지를 보여주었을 때 파라메터가 넘어오지 않는다
		totalRecordCount = 0;
	 else
		totalRecordCount = Integer.parseInt(st1);

	String st2 = request.getParameter("pageNumber");
	if(st2 == null)	// 첫번째 페이지를 보여주었을 때 파라메터가 넘어오지 않는다
		pageNumber = 0;
	 else
		pageNumber = Integer.parseInt(st2);

	String st3 = request.getParameter("pageCountPerScreen");
	if(st3 == null)	// 첫번째 페이지를 보여주었을 때 파라메터가 넘어오지 않는다
		pageCountPerScreen = 0;
	 else
		pageCountPerScreen = Integer.parseInt(st3);

	String st4 = request.getParameter("recordCountPerPage");
	if(st4 == null)	// 첫번째 페이지를 보여주었을 때 파라메터가 넘어오지 않는다
		recordCountPerPage = 0;
	 else
		recordCountPerPage = Integer.parseInt(st4);
	
	// 총 페이지 수 
	
	int totalPageCount = totalRecordCount / recordCountPerPage;
	//		2		    		23 페이지				10;
	
	if(totalRecordCount % recordCountPerPage != 0){ // 남는게 있다
		totalPageCount++;	
	}
	
	// 시작 페이지 [1] [11] [21]
	int screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen; 
	// 			0						1					10
	
	// 끝 페이지
	int screenEndPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen ) + pageCountPerScreen;
	
	// 끝 페이지는 다시 계산한다
		// 왜? 몇 까지 갈지 모르니까
	if(screenEndPageIndex > totalPageCount){
		screenEndPageIndex = totalPageCount;
	}
			
	if((pageNumber + 1) % pageCountPerScreen == 0){
		screenStartPageIndex = ((pageNumber + 1 ) / pageCountPerScreen * pageCountPerScreen ) - pageCountPerScreen;
		
		screenEndPageIndex = pageNumber + 1;
	}
	%>
<div style="float: left; width: 96%; text-align: center;">
	<!-- << -->
	<a href="#none" title="처음페이지" onclick="goPage('0')">
		<img alt="" src="image/arrow_first.gif" style="width: 9px; height: 9px">
	</a>
	<%
	if(screenStartPageIndex > 1){
	%>
	<a href="#none" title="이전페이지" onclick="goPage('<%=screenStartPageIndex-1 %>')">
		<img alt="" src="image/arrow_back.gif" style="width: 9px; height: 9px">
	</a>
	
	<%	
	}
	%>

	<%
		for (int i = screenStartPageIndex; i < screenEndPageIndex; i++) {
		if(i == pageNumber){
	%>
		<span style="font-size: 9px; color: #000000; font-weight: bold">
			<%=i+1 %>
		</span>
	<%
		} else {
			%>
			<a href="#none" title="<%=i+1%>페이지" onclick="goPage(<%=i%>)"
			style="font-size: 7.5pt; color: #000000; font-weight: normal;">
			[<%=i+1 %>]</a>
			<%
		}
	}
	%>
	<!-- > -->
	<%
	if(screenEndPageIndex < totalPageCount){ // [11][12][13]
		%>
		<a href="#none" title="다음페이지" onclick="goPage(<%=screenEndPageIndex%>)">
			<img alt="" src="image/arrow_next.gif" style="width: 9px; height: 9px">
		</a>
		<% 
	}
	int end_page = 0;
	if(totalPageCount > 0) {
		end_page = totalPageCount -1 ; 
	}
	%>
	<!-- >> -->
	
	<a href="#none" title="마지막 페이지" onclick="goPage(<%=end_page%>)">
		<img alt="" src="image/arrow_end.gif" style="width: 9px; height: 9px">
	</a>
	
</div>