<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form name="frmForm" id="_frmForm" action="pdsupload.do" method="post"
	enctype="multipart/form-data">

<table class="list_table">
<tr>
	<th>아이디</th>
	<td style="text-align: left;">
		<input type="text" name="id" readonly="readonly"
			value="${login.id }" size="50">
	</td>
</tr>

<tr>
	<th>제목</th>
	<td style="text-align: left;">
		<input type="text" name="title" size="50">
	</td>
	
</tr>

<tr>
	<th>파일 업로드</th>
	<td style="text-align: left;">
		<input type="file" name="fileload" style="width: 400px">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td style="text-align: left;">
		<textarea rows="10" cols="50" name="content"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2" style="height: 50px;text-align: center;">
		<a href="#none" id="_btnPds" title="자료 올리기">
			<img alt="" src="image/bwrite.png">
		</a>
	</td>
</tr>

</table>

</form>        
<script type="text/javascript">
$("#_btnPds").click(function() {
	
	$("#_frmForm").submit();
});

</script>
