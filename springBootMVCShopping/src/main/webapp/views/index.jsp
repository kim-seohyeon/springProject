<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
hk shoppingmall<br/>
<a href="mailling">메일링</a> | <a href="library">자료실</a>

<c:if test="${!empty auth }">

<ul>
	<c:if test="${auth.grade == 'emp' }">
	<li><a href="member/memberList">회원관리</a></li>
	<li><a href="employee/empList">직원관리</a></li>
	<li><a href="empMyPage.my">내정보 보기</a></li>
	<li><a href="goodsList.goods">상품관리</a></li>
	
	</c:if>
	<c:if test="${auth.grade == 'mem' }">
		<li><a href="memberMyPage.my">내정보 보기</a></li>
		<li><a href="cartList.item">장바구니</a></li>
	
	</c:if>
	<li><a href="login/logout">로그아웃</a></li>
	
</ul>
</c:if>
<c:if test="${empty auth }">

<form:form modelAttribute="loginCommand" action="/login/login" method="post">
<table border = 1>
	<tr><td colspan=2>
		자동 로그인<input type="checkbox" name="autoLogin"/> | 
		아이디 저장<input type="checkbox" name="idStore"/></td>
	</tr>
	<tr>
		<td><form:input path="userId"/>
			<form:errors path="userId"/></td>
		<td rowspan=2><input type="submit" value="로그인"/></td>
		
	</tr>
	<tr>
		<td><form:password path="userPw"/>
			<form:errors path="userPw"/></td>
	</tr>
	<tr><td colspan=2>
		<a href="#">아이디</a>/
		<a href="/help/findPassword">비밀번호 찾기</a>|
		<a href="/register/userAgree">회원가입</a></td>
	</tr>
	
</table>
</form:form>
</c:if>

</body>
</html>