<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 표현언어</title>
	<style>td, th { padding: 3px; }</style>
</head>
<body style="margin: 40px;">
	<h3>회원 목록(Member List)</h3>
	<table class="table table-bordered text-center ">
		<tr><th>No.</th><th>아이디</th><th>이름</th><th>주소</th></tr>
	<c:forEach var="member" items="${memberList}" varStatus="loop">  
		<tr>
			<td class ="col-1">${loop.count}</td>
			<td class ="col-2">${member.id}</td>
			<td class ="col-2">${member.name}</td>
			<td class ="col-7">${member.addr}</td>
		</tr> 
	</c:forEach>
	</table>
</body>
</html>