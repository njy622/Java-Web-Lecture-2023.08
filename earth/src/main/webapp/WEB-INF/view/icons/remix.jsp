<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
      <h1>Remix Icons</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/index">Home</a></li>
          <li class="breadcrumb-item">Icons</li>
          <li class="breadcrumb-item active">Remix</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <p>
        Use the following pattern to add the Remix icons to anywhere in your project. <code>&lt;i class=&quot;<strong>ri-add-line</strong>&quot;&gt;&lt;/i&gt;</code> Replace the bold part with the below icon names.
        Check the <a href="https://remixicon.com/" target="_blank">Official website</a> for more info.
      </p>

	  <%@ include file="iconlist.jspf" %>

    </section>

  </main><!-- End #main -->

  <%@ include file="../common/bottom.jspf" %>
</body>
</html>