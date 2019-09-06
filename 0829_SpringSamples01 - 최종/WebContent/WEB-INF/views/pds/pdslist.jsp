<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  

<!-- 검색 -->
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">
<form action="" name="frmForm1" id="_frmFormSearch" method="post">
<table style="margin-left: auto; margin-right: auto; margin-top: 3px ; margin-bottom: 3px">
<tr>
	<td>검색:</td>
	<td style="padding-left: 5px">
	<select id="_s_category" name="s_category">
		<option value="" selected="selected">선택</option>
		<option value="title" <c:out value="${s_category == 'title'? 'selected':'' }"/>>제목</option>
		<option value="content"<c:out value="${s_category == 'content'? 'selected':'' }"/>>내용</option>
		<option value="writer"<c:out value="${s_category == 'writer'? 'selected':'' }"/>>작성자</option>
	</select>
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_s_keyword" name="s_keyword" value="${s_keyword }">
	<td style="padding-left: 5px ">
		<span class="button blue">
			<button type="button" id="_btn_Search" name="btn_Search">검색</button>
		</span>
</table>

<input type="hidden" name="pageNumber" id="_pageNumber" value="0">
<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage ? 0 : recordCountPerPage)}">
</form>
</div>




<table class="list_table" style="width: 85%">
<colgroup>
<col width="50"><col width="100">
<col width="300"><col width="50">
<col width="50"><col width="50">
<col width="100"><col width="50">
</colgroup>
<thead>
<tr>
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운로드 수</th><th>작성일</th><th>삭제</th>
</tr>
</thead>

<tbody>
<c:forEach var="pds" items="${pdslist }" varStatus="vs">
<tr class="_hover_tr">
	<td>${vs.count }</td>
	<td>${pds.id }</td>
	<td style="text-align: left;">
		<a href="pdsdetail.do?seq=${pds.seq }">
			${pds.title }
		</a>
	</td>
	<td>
		<input type="button" name="btnDown" value="다운로드"
			onclick="filedowns('${pds.filename}','${pds.seq }')">
	</td>
	<td>${pds.readcount }</td>
	<td id="downCnt${pds.seq }">${pds.downcount }</td>
	<td>
		<font size="1">${pds.regdate }</font>
	</td>
	<td>
		<img alt="" src="image/del.png" data_file_seq="${pds.seq }"
			class="btn_fileDelte">
	</td>
</tr>
</c:forEach>
</tbody>
</table>

<!-- 추가버튼 -->
<div id="button.wrap">
	<span class="button blue">
		<button type="button" id="_btnAdd">자료 추가</button>
	</span>
</div>



<!-- 다운로드 버튼 클릭시 , 기억하세요.. -->
<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>

<!-- 페이징 -->
<div id="paging_wrap">
	<jsp:include page="/WEB-INF/views/pds/paging.jsp" flush="false"> 	
		<jsp:param name="pageNumber" value="${pageNumber }" />
		<jsp:param name="totalRecordCount" value="${totalRecordCount }" />
		<jsp:param name="pageCountPerScreen" value="${pageCountPerScreen }" /> 
		<jsp:param name="recordCountPerPage" value="${recordCountPerPage }" />
	</jsp:include>

</div>

<script type="text/javascript">
function goPage( pageNumber ) { /* pageNumber는 현재 페이지를 뜻한다 */
	$("#_pageNumber").val(pageNumber);
	
	$("#_frmFormSearch").attr("action","pdslist.do").submit();
}

$("#_btn_Search").click(function() {
	$("#_frmFormSearch").attr("action","pdslist.do").submit();
});

function filedowns(filename, seq) {
	
	var seq = seq;
	
	$.ajax({
		type:"get",
		url:"downcount.do",
		data:"seq="+seq,
		success:function(data){
			
			var result = parseInt( data );
			var cntId = 'downCnt' + seq;
			
			var originVal = $("#" + cntId).text();
			
			if( result > 0){
				var num = parseInt(originVal) + 1 ;
			
				$("#" + cntId).text(num);
			}
		},
		error:function(e){
			alert("에러 :  " + e);
		}
		
	});
	
	
	var doc = document.file_Down	// name은 이렇게 접근이 가능하다
	doc.filename.value = filename;
	doc.seq.value = seq;
	doc.submit();
	
	

};	


$("#_btnAdd").click(function() {
	location.href="pdswrite.do";
	
});

$(".btn_fileDelte").click(function() {
	var seq = $(this).attr("data_file_seq");
	location.href="pdsdelete.do?seq="+seq;
});




</script>
