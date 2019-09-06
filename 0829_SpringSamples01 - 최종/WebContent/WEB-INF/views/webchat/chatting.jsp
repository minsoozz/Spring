<%@page import="bit.com.a.model.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
span{
size: 20cm;
}
#my{
color: red;
text-align: right;
position: relative;
}
#you{
color: blue;
text-align: left;t
position: relative;
right: 60px;
}
</style>
채팅명:<input type="text" id="nickname" value="${login.id }" readonly="readonly">

<input type="button" id="enterBtn" value="입장" onclick="connect()">

<input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
<br><br>

<h1>대화창</h1>
<div id="chatArea">
	<span id="charMessageArea">
	</span>
</div>
<input type="text" id="message">
<input type="button" id="sendBtn" value="전송" onclick="send()">

<script>

// 웹 소켓 변수
var wsocket;

// 입장 버튼을 클릭시에 호출 함수
function connect(){
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
		// 이미 소켓이 생성된 경우
		alert("이미 입장하셨습니다");
		return;
		
	}
	
	//Web Socket 생성
	   wsocket = new WebSocket("ws://localhost:8091/SpringSamples/echo.do")
	   alert("wsocket:" + wsocket);
	
	   wsocket.onopen = onOpen;
	   wsocket.onmessage = onMessage;
	   wsocket.onclose = onClose;
	   
}
function disconnect(){
	
	var nickname = $("#nickname").val();
	var msg = nickname +"님이 퇴장하셨습니다 <br>";
	wsocket.send("msg:"+msg);
	wsocket.close()	;
}

// onopen(연결), onmessage(메시지), onclose(종료)
function onOpen(evt) {
	appendMessage("연결되었습니다");
	var nickname = $("#nickname").val();
	
	var msg = nickname +"님이 입장하셨습니다 <br>";
	wsocket.send("msg:"+msg);
}

// 처음 들어온 관문, 서버로부터 메시지가 도착 했을 때 호출
function onMessage(evt) {
	var data = evt.data; 
	if(data.substring(0,4) == "msg:"){
		appendMessage(data.substring(4));
	}
}

function onClose(evt) {
	appendMessage("연결이 끊겼습니다");
}

function send() {
	
	var nickname = $("#nickname").val();
	var msg = $("#message").val();
	// 실제로 보내는 부분은 이거입니다 ~ 전송부분
	
	wsocket.send("msg:"+nickname+":"+msg);
	$("#message").val("");

}

function appendMessage(msg) {
	
	var nickname = $("#nickname").val();
	
	var username = msg.substring(0, msg.indexOf(':'));
	
	if(nickname == username){
	
		$("#charMessageArea").append("<span id='my'>" + msg + "</span> <br>");
				
	} else{
		$("#charMessageArea").append("<span id='you'>" + msg + "</span> <br>");
			
	}
	
	
	// 메시지를 추가하고 개행
	
	var chatAreaHeight = $("#charArea").height();
	
	var maxScroll = $("#charMessageArea").height() - chatAreaHeight;
	
	$("#chatArea").scrollTop(maxScroll);
}

$(document).ready(function() {
    $("#message").keydown(function(key) {
        if (key.keyCode == 13) {
        	$("#sendBtn").click();
        }
    });	
})

</script>