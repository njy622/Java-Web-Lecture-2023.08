<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<%@include file="../common/head.jspf" %>
 	<style>
 		th, td {text-align: center;}
 	</style>
</head>
<body>
   <
   
   <div class="container" style="margin-top: 250px;">
   		<div class="row">
   			<div class="col-4"></div>
   			<div class="col-4">
   				<a  style="margin-right: 40px" href="/onnana/home"><img src="/onnana/img/onna.png" height="70"></a>
				<hr>
   				<h3><strong>사용자 등록</strong></h3>
				<hr>
				<form action="/onnana/user/register" method="post">
					<table class=table table-borderless">
						<tr>
							<td style="width: 35%"><label class="col-form-label">사용자 ID</label></td>
							<td style="width: 65%"><input type="text" name="uid" class="form-control"></td>
						</tr>
						<tr>
							<td><label class="col-form-label">패스워드</label></td>
							<td><input type="password" name="pwd" class="form-control"></td>
						</tr>
						<tr>
							<td><label class="col-form-label">패스워드 확인</label></td>
							<td><input type="password" name="pwd2" class="form-control"></td>
						</tr>
						<tr>
							<td><label class="col-form-label">이름</label></td>
							<td><input type="text" name="uname" class="form-control"></td>
						</tr>
						<tr>
							<td><label class="col-form-label">이메일</label></td>
							<td><input type="text" name="email" class="form-control"></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" class="btn btn-primary" value="제출">
								<input type="reset" class="btn btn-secondary" value="취소">
							</td>
						</tr>
					</table>
				</form>
   			</div>
   			<div class="col-4"></div>
   		</div>
   </div>
   <%@ include file="../common/bottom.jspf" %>
</body>
</html>