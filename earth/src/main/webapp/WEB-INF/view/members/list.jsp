<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      <h1>Member List</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/index">Home</a></li>
          <li class="breadcrumb-item">Members</li>
          <li class="breadcrumb-item active">List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Member List</h5>
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th>프로필</th>
                    <th>멤버 ID</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th data-type="date" data-format="YYYY-DD-MM">등록일자</th>
                    <th>프로필 경로</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="member" items="${memberList}">
                  <tr>
                    <td><img src="/file/profileDownload/${member.imgPath}" class="rounded-circle" height="28"></td>
                    <td>${member.mid}</td>
                    <td>${member.mname}</td>
                    <td>${member.email}</td>
                    <td>${member.regDate}</td>
                    <td>${member.imgPath}</td>
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