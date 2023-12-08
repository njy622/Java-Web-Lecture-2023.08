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
   			location.href = '/demo/blog/list?f=' + field + '&q=' + query;
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
			              	<strong>블로그 목록</strong>
				      	    <span style="font-size:0.6em;">
				      	    	<a href="/demo/blog/write">
				      	    		<i class="ms-5 fa-regular fa-file-lines"></i> 글쓰기
				      	    	</a>
				      	    </span>
			              </h3>
           			</td>
           			<td stlye="width: 15%;">
			      		<select class="form-select" id="field" >  <!-- ↓필드가 타이틀이면  셀렉티드 아니면 '' -->
			                     <option value="title" ${field eq 'title' ? 'selected' : '' }>제목</option>
			                     <option value="content"  ${field eq 'content' ? 'selected' : '' }>본문</option>
			                     <option value="penName"  ${field eq 'penName' ? 'selected' : '' }>필명</option>
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
                    <th style="width: 8%;">ID</th>
                    <th style="width: 14%;">필명</th>
                    <th style="width: 48%;">제목</th>
                    <th style="width: 20%;">작성시간</th>
                    <th style="width: 10%;">조회수</th>
                 </tr>
              <c:forEach var="blog" items="${blogList}">
                 <tr>
                    <td>${blog.bid}</td>
                    <td>${blog.penName}</td>
                    <td><a href="/demo/blog/detail/${blog.bid}">${blog.title}</a></td>
                    <td>${fn:replace(fn:substring(blog.modTime, 2, 16), 'T', ' ')}</td>
                    <td>${blog.viewCount}</td>
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