<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/head.jspf" %>
	<script>
		function upload() {
			const formData = new FormData();
			let title = $('#inputTitle').val();		 //제이쿼리 문법 .val() = > 입력한 값
			formData.append('title', title);		 // 이름이 같으면 한번만 작성해도됨
			let inputFileVal = $('#inputFile')[0];
			formData.append('file', inputFileVal.files[0]);
			$.ajax({
				//<----------- 서버로 전송하는 코드 ------------>
				type:'POST',
				url: '/sample/file/formAjax',
				data: formData,
				processData: false,  // 이미지 불러오려면
				contentType: false,  // 이 두가지를 false로 설정해야함
				//<---------여기까지 서버로 전송하는 코드----------->
				success: function(result) {
					let obj = JSON.parse(result);
					$('#title').html(obj.title);
					$('#image').attr({src: '/sample/file/download/' + obj.filename});
				}
			});
		}
	</script>
</head>
<body>
	<%@ include file="../common/top.jspf" %>
	<div class="container" style="margin-top:80px">
		<div class="row">
			<%@ include file="../common/aside.jspf" %> 
			<!-- ================ Main =================== -->
			<div class="col-9">
				<h3><strong>파일 업로드 by AJAX</strong></h3>
				<!-- http://localhost:8090/sample/file/formAjax -->
				<hr>
				<div class="row">
					<div class="col-4">
						<div class="card">
							<img class="card-img-top" id="image" src="/sample/img/home.jpg">
							<div class="card-body">
								<h4 class="card-title" id="title">Home 화면</h4>
							</div>
						</div>
					</div>
					<div class="col-8">
						<table class="table table-borderless">
							<tr>
								<td><label class="col-form-label">제목</label></td>
								<td><input class="form-control" type="text" id="inputTitle"></td>
								<!-- name은 서버가 받는값 / id는 ajax불러올때 -->
							</tr>
							<tr>
								<td><label class="col-form-label">파일</label></td>
								<td><input class="form-control" type="file" id="inputFile"></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;">
									<button class="btn btn-primary" onclick="upload()">제출</button>
								</td>
							</tr>
						</table>
					
					</div>
				</div>
			</div>
			<!-- ================ Main =================== -->
		</div>
	</div>
	<%@ include file="../common/bottom.jspf" %>
</html>