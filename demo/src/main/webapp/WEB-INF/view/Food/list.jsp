<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<!DOCTYPE html>
<html>
<head>
   <%@ include file="./common/head.jspf" %>
   <style>td, th { text-align : center; }
   </style>
   <script>
   		function search() {
   			let field = document.getElementById('field').value;
   			let query = document.getElementById('query').value;
   			//console.log("search()", field, query);
   			location.href = '/demo/food/list?f=' + field + '&q=' + query;
   		}
   </script>
</head>
<body>
   <%@ include file="./common/top.jspf" %>
   
   <div class="container" style="margin-top:80px">
           <div class="row">
           <%@ include file="./common/aside.jspf" %>
           <!-- ================== Main =============== -->
           <div class="col-9">
           	<table class="table table-sm table-borderless">
           		<tr>
           			<td style="width: 52%; text-align: left;">
			              <h3>
			              	<strong>Food 게시판</strong>
				      	    <span style="font-size:0.6em;">
				      	    	<a href="/demo/food/write">
				      	    		<i class="ms-5 fa-regular fa-file-lines"></i> 메뉴 등록
				      	    	</a>
				      	    </span>
			              </h3>
           			</td>
           			<td stlye="width: 15%;">
			      		<select class="form-select" id="field" >  <!-- ↓필드가 타이틀이면  셀렉티드 아니면 '' -->
			                     <option value="foodType" ${field eq 'foodType' ? 'selected' : '' }>종류</option>
			                     <option value="content"  ${field eq 'content' ? 'selected' : '' }>내용</option>
			                     <option value="taste"  ${field eq 'taste' ? 'selected' : '' }>맛평가</option>
			                     <option value="nickName"  ${field eq 'nickName' ? 'selected' : '' }>닉네임</option>
		                 </select>
           			</td>
           			<td stlye="width: 25%;">
           				<input class="form-control" placeholder="검색할 내용" id="query" value="${query}"
           						onkeyup="if(window.event.keyCode == 13) search()">
           						<!-- keyup(keyup일때 데이터를 받음)일때 keyCode== 13(엔터키)이면 search()를 호출함 -->
           			</td>
           			<td stlye="width: 8%;">
           				<button class="btn btn-outline-primary" onclick="search()">검색</button>
           			</td>
           		</tr>           	
           	</table>
              <hr>
              <table class="table">
                 <tr class= "table-secondary">
                    <th style="width: 8%;">No</th>
                    <th style="width: 14%;">닉네임</th>
                    <th style="width: 14%;">종류</th>
                    <th style="width: 34%;">내용</th>
                    <th style="width: 20%;">작성시간</th>
                    <th style="width: 10%;">조회수</th>
                 </tr>
              <c:forEach var="food" items="${foodList}">
                 <tr>
                    <td>${food.id}</td>
                    <td>${food.nickName}</td>
                    <td><a href="/demo/food/detail/${food.id}">${food.foodType}</a></td>
                    <td>${food.content}</td>
                    <td>${fn:replace(fn:substring(food.modTime, 2, 16), 'T', ' ')}</td>
                    <td>${food.viewCount}</td>
                 </tr>
              </c:forEach>
              </table>
           </div>
           <!-- ================ Main ==================== -->
          </div>
       </div>

   <%@ include file="./common/bottom.jspf" %>
	
</body>
</html>