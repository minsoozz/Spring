<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">

<form name="frmForm1" id="_frmForm" method="post"> <!-- 검색 -->
<table style="margin-left: auto; margin-right: auto; margin-top: 3px ;margin-bottom: 3px; border: 0; padding: 0">
<tr>
	<td>검색 : </td>
	<td style="padding-left:5px">
		<input type="text" id="_s_keyword" name="s_keyword" value="${empty s_keyword?'':s_keyword }">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="_btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>
</form>


</div>


<div id="_youtube_">
	<iframe id="_youtube" width="640" height="360"
		src="http://www.youtube.com/embed/" frameborder="1" allowfullscreen=""></iframe>
</div>
<br><br>

<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 70px">
	<col style="width: auto;">
	<col style="width: 100px">
	<col style="width: 50px">
</colgroup>

<thead>
	<tr>
		<th>순서</th><th>제목</th><th>유튜브고유번호</th><th>저장</th>
	</tr>
</thead>

<tbody>
<c:if test="${empty yulist }">
	<tr>
		<td colspan="4">
			목록이 없습니다
		</td>
	</tr>
</c:if>
<c:forEach items="${yulist }" var="youlist" varStatus="vs">
<tr class="_hover_tr">
	<td>${vs.count }</td>
	<td style="text-align: left;" id="_v${youlist.vname }ed2" onclick="getyoutube2('${youlist.vname}')">
		<div class="c_vname" vname='${youlist.vname }'>${youlist.title }</div>
	</td>
	<td>${youlist.vname }</td>
	<!-- 기존 방식과 다르다 -->
	<td onclick="getyoutube('${login.id}','${youlist.vname }')">
		<img alt="" src="image/save.png" class="ck_seq" vname="${youlist.vname }" id="_v${youlist.vname }ed"
			loginid='${login.id }' title="${youlist.title }" keyword='${empty s_keyword?"":s_keyword }'/>
	</td>
	 
</tr>
</c:forEach>

</tbody>

</table>


<script type="text/javascript">
$(document).ready(function() {
	$("#_youtube_").hide();
	
	$("._hober_tr").mouseover(function() {
		$(this).children().css("background-color","#f0f5ff");
	}).mouseout(function() {
		$(this).children().css("background-color","#ffffff");
	});
});

$("#_btnSearch").click(function() {
	$("#_frmForm").attr({"target":"_self","action:":"yutobe.do"}).submit();
						/* self 현재창 , blank 새창 */

});

$(".c_vname").click(function() {
	$("#_youtube_").show();
	$("#_youtube").attr("src","http://www.youtube.com/embed/" + $(this).attr("vname"));
});

$(".ck_seq").click(function() {
	var id = $(this).attr("loginid");
	var vname = $(this).attr("vname");
	var title = $(this).attr("title");
	var category = $(this).attr('keyword');
	$.ajax({
		type:'post',
		url:"<%=application.getContextPath()%>/youtubesave.do",
		async: true,
		data:"id="+id+"&vname="+vname+"&title="+title+"&category="+category,
		success:function(msg){
			alert("성공적으로" + msg.vname);
		},
		error:function(e){
			alert(e);
		}
	});

});

function getyoutube2(vname) {
	$("#_v" + vname + "ed2").css("background-color","#ff0000");
}

</script>
