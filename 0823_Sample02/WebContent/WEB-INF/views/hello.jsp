<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
존나졸리다
<br>
<%
String name = (String)request.getAttribute("name");
out.println("name : " + name);
%>

<h3>${name }</h3>
</body>
</html>