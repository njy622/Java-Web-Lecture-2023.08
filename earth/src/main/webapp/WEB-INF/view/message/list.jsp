<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../common/head.jspf" %>
</head>

<body>
  <%@ include file="../common/top.jspf" %>
  <%@ include file="../common/aside.jspf" %>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Message List</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/index">Home</a></li>
          <li class="breadcrumb-item">Message</li>
          <li class="breadcrumb-item active">List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Message List
            	<span class="ms-5"><a href="/message/write"><i class="bi bi-pencil-fill"></i> 쓰기</a></span>
              </h5>
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>발신</th>
                    <th>수신</th>
                    <th>상태</th>
                    <th>발신시각</th>
                    <th>액션</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="message" items="${messageList}">
                  <tr>
                    <td>${message.mid}</td>
                    <td>${empty message.fromName ? message.mfrom : message.fromName}</td>
                    <td>${message.mto}</td>
                    <td>${message.status}</td>
                    <c:set var="genTime" value="${fn:substring(message.genTime, 0, 19)}" />
                    <td>${fn:replace(genTime, 'T', ' ')}</td>
                    <td>
                    	<a href="#"><i class="bi bi-eye-fill"></i></a>
                    	<a href="#"><i class="bi bi-pencil-fill"></i></a>
                    	<a href="#"><i class="bi bi-trash-fill"></i></a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

  <%@ include file="../common/bottom.jspf" %>
</body>
</html>