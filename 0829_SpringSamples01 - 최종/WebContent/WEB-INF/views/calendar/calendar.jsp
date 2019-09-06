<%@page import="bit.com.a.model.CalendarDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
// nvl함수
public boolean nvl(String msg){
   return msg == null || msg.trim().equals("")?true:false;
}
// 날짜를 클릭하면, 그날의 일정이 모두 보이게 하는 callist.jsp로 이동하는 함수
public String callist(int year, int month, int day){
   String str = "";
   
   str += String.format("<a href='%s?year=%d&month=%d&day=%d'>",
                  "callist.jsp", year, month, day);
   str += String.format("%2d", day);  //2칸으로 잡아라
   str += "</a>";
   
   /*
   <a href='callist.jsp?year=2019&month=07&day=31'>31</a>
   */
   return str;
}
// 일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp 이동시키는 함수
public String showPen(int year, int month, int day){
   String str = "";
   
   String image = "<img src='image/pen.gif'>";
   
   str = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>",
                  "calwrite.jsp", year, month, day,image);
   /*
   <a href='calwrite.jsp?year=2019&month=07&day=31'>
      <img src='image/pen.gif'>
   </a>
   */
   return str;
   
}
// 1 -> 01 로 바꿔줌
public String two(String msg){
   return msg.trim().length() < 2?"0"+msg:msg.trim();
}
// 각 날짜별로 테이블을 생성하는 함수
public String makeTable(int year, int month, int day, List<CalendarDto> list){
   String str = "";
   // 20190731
   String dates = (year +"") + two(month+"") + two(day+"");   //0붙여주는 함수 위에꺼 씀
   
   str += "<table>";
   str +="<col width='98'>";
   
   for(CalendarDto dto : list){
      if(dto.getRdate().substring(0, 8).equals(dates)){
         str += "<tr bgcolor='green'>";
         str += "<td>";
         
         str += "<a href='caldetail.jsp?seq=" + dto.getSeq() + "'>";
         
         str += "<font style='font-size:8;color:red'>";
         
         str += dot3(dto.getTitle());
         
         
         str += "</font>";
         
         
         str += "</a>";
         
         str += "</td>";
         str += "</tr>";
      	System.out.println(dto.getRdate().substring(0, 8));
      }
      
   }
   
   
   str += "</table>";
   
   return str;
   
   
}
// 제목이 너무 길면 제목+...으로 처리하는 함수 예) 김사장님과 점심약속이 있음 -> 김사장님과 점심...
public String dot3(String msg){
   String str = "";
   if(msg.length() >= 10){
      str = msg.substring(0, 10);
      str += "...";
   }else{
      str = msg.trim();
   }
   return str;
   
   
}
%> 


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar.jsp</title>
</head>
<body>

<%
Calendar cal = (Calendar)request.getAttribute("cal");

String syear = request.getParameter("year");
String smonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);

if(nvl(syear) == false){   //파라미터가 넘어온 경우
   year = Integer.parseInt(syear);
}
int month = cal.get(Calendar.MONTH) + 1;
if(nvl(smonth) == false){
   month = Integer.parseInt(smonth);
} 

// 요일 
int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
// <<      year--
String pp = String.format("<a href='%s?year=%d&month=%d'>" 
                     +"<img src='image/left.gif'></a>" ,
                     "Calendar.do", year-1, month);
// <      month--
String p = String.format("<a href='%s?year=%d&month=%d'>" 
                     +"<img src='image/prec.gif'></a>" ,
                     "Calendar.do", year, month-1);
// >      month++
String n = String.format("<a href='%s?year=%d&month=%d'>" 
                     +"<img src='image/next.gif'></a>" ,
                     "Calendar.do", year, month+1);
// >>      year++
String nn = String.format("<a href='%s?year=%d&month=%d'>" 
      +"<img src='image/last.gif'></a>" ,
      "Calendar.do", year+1, month);

List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("CalList");

%>

<div align="center">

<table border="1" style="color: gray;">
<col width="100"><col width="100"><col width="100"><col width="100">
<col width="100"><col width="100"><col width="100">

<tr height="100">
   <td colspan="7" align="center">
      <%=pp %>&nbsp;<%=p %>
      <font color="black" style="font-size: 50px">
         <%=String.format("%d년&nbsp;&nbsp;%d월",year, month) %>
      </font>
      <%=n %>&nbsp;<%=nn %>
   </td>
</tr>

<tr height="100">
	<td align="center">일</td>
	<td align="center">월</td>
	<td align="center">화</td>
	<td align="center">수</td>
	<td align="center">목</td>
	<td align="center">금</td>
	<td align="center">토</td>
</tr>	
<tr height="100" align="left" valign="top">
<%
// 위쪽 빈칸
	for(int i = 1; i< dayOfWeek; i++){
	%>
	<td>&nbsp;</td>	
	<%
}
%>
<!-- 날짜 -->
<%
int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
for(int i = 1; i <= lastDay; i++){
%>
	<td><%=callist(year, month, i) %>&nbsp;<%=showPen(year,month,i) %>	
 		<%=makeTable(year, month, i, list) %> 
	</td>
	<%
	if((i+dayOfWeek -1)% 7 == 0 && i != lastDay){
	%>
	</tr><tr height="100" align="left" valign="top">			
<% 				
	}
	%>

<%	
}
%>

<%-- 밑칸 --%>
<%
for(int i = 0; i < (7- (dayOfWeek + lastDay-1)%7)%7;i++){
%>
	<td>&nbsp;</td>
<% 	
}
%>


</table>


</div>



</body>
</html>