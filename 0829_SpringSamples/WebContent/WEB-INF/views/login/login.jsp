<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<!-- cookie 넣기 -->
<script type="text/javascript" src="./jquery.cookie.js"></script>


<style type="text/css">
#login_wrap {
   margin:10px;
   position: relative;
   bottom: -215px;
}
#login_wrap th {
   font-weight:bold;
}
#main_wrap { 
   width:800px; 
   margin-left:auto; 
   margin-right:auto; padding:0px; 
}         
#content_wrap { 
   width: 100%; 
   height: 500px; 
   background-image:url("image/saitama.png"); 
   background-repeat:no-repeat; 
   background-position:top center;  
}
         
.login_title_warp {
   width:500px; 
   color:#FFFFFF; 
   text-align:center; 
   background-color:#3e5fba; 
   border:solid 1px #EFEFEF; 
   font-weight:bold; 
   height:60px;
    position: relative;
    bottom: -230px;
}

/* table셋팅 */
.content_table { width:98%; border-bottom:1px solid #EFEFEF; border-right:1px solid #EFEFEF; border-collapse:collapse; margin-left:auto; margin-right:auto;  clear:both; }
.content_table td, .content_table th { text-align:center; border-top:1px solid #EFEFEF; border-left:1px solid #EFEFEF; padding:0.3em; }
.content_table th { background-color:#4D6BB3; color:#FFFFFF; line-height: 1.7em; font-weight:normal;}
.content_table td { padding-left:5px; text-align:left; line-height: 1.7em; }
.content_table td.contents { width:100%; height:400px; overflow:auto; }
.content_table th, .content_table td { vertical-align:middle; }

.content_table select { height:19px; border:#CCCCCC solid 1px; vertical-align:middle; line-height: 1.8em; padding-left:0px; }
.content_table select option { margin-right:10px; }

</style>

</head>
<body>

<div id="main_wrap">
   <div id="middle_wrap">
      <div id="content_wrap">
         
         <div style="width: 502px; height: 166px; margin-left: auto; margin-right: auto;
                  position: relative; top: 100px;">
                        
<!--          <div class="login_title_warp">
            <div style="margin-top: 12px">
               <h2>My Home Page</h2>
            </div>
         </div>    -->
            
         <div id="login_wrap">
            <form action="loginAf.do" name="frmForm" id="_frmForm" method="post">
               
               <table class="content_table" style="width: 75%">
               <colgroup>
                  <col style="width:30%">
                  <col style="width:70%">
               </colgroup>
               
               <tbody>
               	<tr>
                  <th style="background: #eeeeee; color: #3e5fba;">아이디</th>
                  <td>&nbsp;<input type="text" id="_userid" name="id" data-msg="ID를" 
                              size="20px" title="아이디" style="border: 1px solid #dddddd"> 
                     <input type="checkbox" id="chk_save_id">ID 저장
                  </td>
              	</tr>
              	
              	<tr>
              	   <th style="background: #eeeeee; color: #3e5fba;">비밀번호</th>
              		<td>&nbsp;<input type="password" id="_pwd" name="pwd" data-msg="PWD를" 
                              size="20px" title="비밀번호" style="border: 1px solid #dddddd"></td>
              	</tr>
              	
              	<tr>
              		<td colspan="2" style="height: 50px; text-align: center;">
              			<span>
							<a href="#none" id="_btnLogin" title="로그인">
								<img alt="" src="./image/login_btn.jpg">
							</a>              		
							<a href="account.do" id="_btnRegi" title="회원가입">
								<img alt="" src="./image/regi.jpg">
							</a>              		
              			</span>
              	</tr>
               </tbody>
               
               </table>
               
            </form>
         </div>   
            
            
            
         </div>      
      </div>   
   </div>
</div>



<script type="text/javascript">
$("#_btnLogin").click(function() {   
   if( $("#_userid").val().trim() == "" ){
      alert("id를 입력해 주십시오");
      $("#_userid").focus();
   }
   else if( $("#_pwd").val().trim() == "" ){
      alert("password를 입력해 주십시오");
      $("#_pwd").focus();
   }
   else{
      $("#_frmForm").attr({"action":"loginAf.do","target":"_self"}).submit();   
   }   
});

$("#btnRegi").click(function() {
	location.href = "account.do";
});

</script>
<!-- ID 저장 쿠키 펑션 -->
<script>

   $(document).ready(function(){
       var userInputId = getCookie("userInputId");//저장된 쿠기값 가져오기
    $("input[name='id']").val(userInputId); 
     
    if($("input[name='id']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩
                                           // 아이디 저장하기 체크되어있을 시,
        $("#chk_save_id").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#chk_save_id").change(function(){ // 체크박스에 변화가 발생시
        if($("#chk_save_id").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("input[name='id']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("input[name='id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#chk_save_id").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            var userInputId = $("input[name='id']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }
    });
});
 
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}
</script>

</body>
</html>