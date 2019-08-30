<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Tiles를 사용하기 위해 link 시켜준다 -->
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 화면 배치용 jsp -->

<table border='1' style="width:100%; height:100%; bordercolor:Gray; ">

<tr align="center"> <!-- header-->
	<td height="10%" colspan="2">
		<tiles:insertAttribute name="header"/>
	</td>
</tr>
<tr>
	<td width="30%" align="left" valign="top"><!-- menu -->
		<tiles:insertAttribute name="menu"/>
	</td>
	
	<td><!-- main -->
		<tiles:insertAttribute name="content"/>
	</td>
</tr>

<tr>
	<td height="10%" colspan="2"><!-- footer  -->
		<tiles:insertAttribute name="footer"/>
	</td>
</tr>

</table>


</body>
</html>