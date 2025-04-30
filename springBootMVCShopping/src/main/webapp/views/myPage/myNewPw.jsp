<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myNewPw.jsp</title>
<script src="https://code.jquery.com/jquery-1.8.1.js"></script>
<script type="text/javascript">
$(function(){
	$("#frm").submit(function(){
		if($("#newPw").val() != $("#newPwCon").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("#newPwCon").val("");
			$("#newPw").val("");
			$("#newPw").focus();
			return false;
		}
	});
});
</script>
</head>
<body>
비밀번호 변경<br/>

<ul>
	<li><a href='<c:url value="/"/>'>홈</a></li>
	<li><a href='memberMyPage.my'>내정보 보기</a></li>
	<li><a href='memberUdate.my'>내정보 수정</a></li>
	<li><a href="memberPwUpdate.my">비밀번호 번경</a></li>
	<li><a>회원 탈퇴</a></li>
</ul>

<form action="memberPwPro.my" method="post" id="frm">
현재 비밀번호: <input type="password" name="oldPw" id="oldPw" required="required"/>
			<span style="color:red">${pwErr }</span><br/>
새 비밀번호: <input type="password" name="newPw" id="newPw" required="required"/><br/>
새 비밀번호 확인: <input type="password" name="newPwCon" id="newPwCon" required="required"/><br/>
<input type="submit" value="비밀번호 변경"/>
</form>


</body>
</html>