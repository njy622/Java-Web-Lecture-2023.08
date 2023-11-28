<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="height" value="${600 / numberOfWeeks}"></c:set>
<c:set var="todaySdate" value="${fn:substring(today,0,4)}${fn:substring(today,5,7)}${fn:substring(today,8,10)}"></c:set>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/head.jspf" %>
 	 <%@ include file="../common/heading.jsp" %>
    <style>
        th { text-align: center; width: 14.28%;}
    </style>
    <script src="/js/calendar.js"></script>
</head>
<body>
	<%@ include file="../common/top.jspf" %>
	<div class="container" style="margin-top:80px">
		<div class="row">
			<%@ include file="../common/aside.jspf" %> 
			 <!-- =================== main =================== -->
            <div class="col-sm-9">
            	<h3><strong>일정표</strong></h3>
            	<hr>
                <div class="d-flex justify-content-between">
                    <div>${today}</div>
                    <div>
                        <a href="/schedule/calendar/left2"><i class="fa-solid fa-angles-left"></i></a>
                        <a href="/schedule/calendar/left"><i class="fa-solid fa-angle-left ms-2"></i></a>
                        <span class="badge bg-primary mx-2">${year}.${month}</span>
                        <a href="/schedule/calendar/right"><i class="fa-solid fa-angle-right me-2"></i></a>
                        <a href="/schedule/calendar/right2"><i class="fa-solid fa-angles-right"></i></a>
                    </div>
                    <div>
                    	<a href="#" onclick="addAnniversary()"><i class="fa-solid fa-pen me-3"></i></a>
                    	<a href="/schedule/list/1"><i class="fa-solid fa-table-list"></i></a>
                    </div>
                </div>
                <table class="table table-bordered mt-2">
                    <tr>
                        <th class="text-danger">일</th>
                        <th>월</th><th>화</th><th>수</th><th>목</th><th>금</th>
                        <th class="text-primary">토</th>
                    </tr>
                <c:forEach var="week" items="${calendar}">
                    <tr>
                    <c:forEach var="day" items="${week}">
                        <td style="height: ${height}px; ${todaySdate eq day.sdate ? 'background-color: #efffff;' : ''}" onclick="cellClick(${day.sdate})">
                            <div class="d-flex justify-content-between">
                           	<c:if test="${day.isOtherMonth eq 1}">
                               	<div class="${(day.date eq 0 or day.isHoliday eq 1) ? 'text-danger' : day.date eq 6 ? 'text-primary' : ''}" 
                               		 style="opacity: 0.5;"><strong>${day.day}</strong></div>
	                        	<div style="opacity: 0.5;">
		                        <c:forEach var="name" items="${day.annivList}" varStatus="loop">
		                        	${loop.first ? '' : '&middot;'} ${name}
	                        	</c:forEach>
	                        	</div>
                           	</c:if>
                           	<c:if test="${day.isOtherMonth eq 0}">
                               	<div class="${(day.date eq 0 or day.isHoliday eq 1) ? 'text-danger' : day.date eq 6 ? 'text-primary' : ''}">
                               		<strong>${day.day}</strong></div>
                               	<div>
		                        <c:forEach var="name" items="${day.annivList}" varStatus="loop">
		                        	${loop.first ? '' : '&middot;'} ${name}
	                        	</c:forEach>
	                        	</div>
                           	</c:if>
                            </div>
	                        <c:forEach var="sched" items="${day.schedList}" varStatus="loop">
	                        	<div class="${loop.first ? 'mt-1' : ''}" style="font-size: 12px;" onclick="schedClick(${sched.sid})">
		                        	${fn:substring(sched.startTime, 11, 16)}
		                        <c:if test="${sched.isImportant eq 1}">
		                        	<strong>${sched.title}</strong>
		                        </c:if>
		                        <c:if test="${sched.isImportant ne 1}">
		                        	${sched.title}
		                        </c:if>
	                        	</div>
	                        </c:forEach>
                        </td>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </table>
            </div>
            <!-- =================== main =================== -->
		</div>
	</div>
	<%@ include file="../common/bottom.jspf" %>
</body>
</html>