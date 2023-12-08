<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="./common/head.jspf" %>
	 <style>
        th { text-align: center; width: 14.28%; }
        td { height: 100px; }
    </style>
</head>
<body>
	<%@ include file="./common/top.jspf" %>
	
    <div class="container" style="margin-top:80px">
       <div class="row">
       		<%@ include file="./common/aside.jspf" %>
       		<!-- ================= Main ==================== -->
       		<div class="col-9">
       				<h3><strong>샘플</strong></h3>
       				<hr>
       			<%@ include file="./jstl_core_sample.jsp" %>
       		
       		</div>
   			<!-- ================= Main ==================== -->
       </div>
   </div>
	<%@ include file="./common/bottom.jspf" %>
</body>
</html>