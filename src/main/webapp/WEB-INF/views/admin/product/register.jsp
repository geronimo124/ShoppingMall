<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/admin/include/left.jsp"%>

		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- left column -->
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">REGISTER PRODUCT</h3>
							</div>
							<!-- /.box-header -->

							<form id='registerForm' action="register" role="form"
								method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<label style="display: block;">카테고리 선택</label>
										<select
											id="mainCategory" name="ctgyPtcd" class="form-control select2"
											style="width: 49.8%; display: inline-block;" required>
											<option selected="selected">1차 카테고리</option>
											<c:forEach items="${mainCateList }" var="categoryVO">
												<option value="${categoryVO.ctgyCd }">${categoryVO.ctgyNm }</option>
											</c:forEach>
										</select> <select id="subCategory" name="ctgyCd"
											class="form-control select2"
											style="width: 49.8%; display: inline-block;" required>

										</select> <input type="hidden" id="ctgyNm" name="ctgyNm" />
										<input type="hidden" id="ctgyPtnm" name="ctgyPtnm" />
									</div>
									<div class="form-group">
										<label for="pdNm">상품 이름</label> <input type="text" name='pdNm'
											id="pdNm" class="form-control" placeholder="Enter Name"
											required>
									</div>
									<div class="form-group" style="width: 49.8%; display: inline-block;">
										<label for="pdTag">상품 가격</label> <input type="text"
											name='pdTag' id="pdTag" class="form-control"
											placeholder="Enter Price" pattern="[0-9]+" required>
									</div>
									<div class="form-group" style="width: 49.8%; display: inline-block;">
										<label for="pdSale">할인율</label> <input type="text"
											name='pdSale' id="pdSale" class="form-control" value="0"
											pattern="[0-9]+" required>
									</div>
									<div class="form-group" style="width: 49.8%; display: inline-block;">
										<label for="pdStock">재고 수량</label> <input type="text"
											name='pdStock' id="pdStock" class="form-control"
											placeholder="Enter Stock" pattern="[0-9]+" required>
									</div>
									<div class="form-group" style="width: 49.8%; display: inline-block;">
										<label for="pdStatus" style="display: block;">진열 상태</label> 
										<select
											name="pdStatus" class="form-control select2" required>
											<option value="Y" selected="selected">Y</option>
											<option value="N">N</option>
										</select>										
									</div>
									<div class="form-group">
										<label for="pdDetl">상품 내용</label>
										<textarea class="form-control" id="pdDetl" name="pdDetl"
											rows="3" placeholder="Enter ..."></textarea>
									</div>
									<div class="form-group">
										<label for="file">대표 이미지 설정</label> <input type="file"
											id="file" name="file" /> <input type="text"
											name='image' id="image" class="form-control"
											placeholder="Empty" readonly="readonly">
											
									</div>
								</div>

								<!-- /.box-body -->

								<div class="box-footer">

									<button type="submit" class="btn btn-primary">등록</button>
									<button type="button" id="btnCancel" class="btn btn-primary">취소</button>

								</div>
							</form>


						</div>
						<!-- /.box -->
					</div>
					<!--/.col (left) -->

				</div>
				<!-- /.row -->
			</section>

		</div>
	</div>
	<!-- /.content -->
	<%@include file="/WEB-INF/views/admin/include/footer.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/aside.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>

	<script>
		var result = '${msg}';

		if (result == 'SUCCESS') {
			alert("처리가 완료되었습니다.");
			location.replace(self.location);
		}

		function checkImageType(fileName){
			
			var pattern = /jpg|gif|png|jpeg/i;
			
			return fileName.match(pattern);

		}

		function getFileInfo(fullName){
				
			var fileName,imgsrc, getLink;
			
			var fileLink;
			
			if(checkImageType(fullName)){
				imgsrc = "/displayFile?fileName=" + fullName;
				fileLink = fullName.substr(14);
				
				var front = fullName.substr(0,12); // /2015/07/01/ 
				var end = fullName.substr(14);
				
				getLink = "/displayFile?fileName=" + front + end;
				
			}else{
				imgsrc ="/resources/dist/img/file.png";
				fileLink = fullName.substr(12);
				getLink = "/displayFile?fileName=" + fullName;
			}
			fileName = fileLink.substr(fileLink.indexOf("_")+1);
			
			return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
			
		}
				
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

	<script id="cateTemplate" type="text/x-handlebars-template">
		<option selected="selected">2차 카테고리</option>		
		{{#each .}}
			<option value="{{ctgyCd}}">{{ctgyNm}}</option>
		{{/each}}
	</script>

	<script>
		$(() => {

			$('#file').on('change', () => {

				$('#image').val($('#file').val().substr(12));

			});
			
			
			$('#btnCancel').on('click', () => {

				// 파일 업로드한거 삭제 요망
				/*
				$(".uploadedList .delbtn").each((index, item) => {
					
					$.ajax({
						   url:"/deleteFile",
						   type:"post",
						   data: {fileName:$(item).attr("href")},
						   dataType:"text",
						   success:() => {}

					 });
					
				});
				*/

				self.location = "/admin/product/list";
				
			});
			
			$('#mainCategory').on('change', function() {

				let mainCategory = $(this).val();
				let cateTemplate = Handlebars.compile($("#cateTemplate").html());
				
				$.ajax({
					   url:"getSubCateList/" + mainCategory,
					   type:"get",
					   dataType:"json",
					   success:function(subCategories){
							  
							  let html = cateTemplate(subCategories);

							  $('#subCategory option').remove();
							  $("#subCategory").append(html);
					   }
				   });
				   
			});

			$('#subCategory').on('change', function() {

				$('#ctgyNm').val($('#subCategory option:selected').html());
				$('#ctgyPtnm').val($('#mainCategory option:selected').html());
				
			});
			
		});
	</script>

	<script src="/ckeditor/ckeditor.js"></script>
	<script>
		$(() => {
			var ckeditor_config = {
					resize_enabled : false,
					enterMode : CKEDITOR.ENTER_BR,
					shiftEnterMode : CKEDITOR.ENTER_P,
					toolbarCanCollapse : true,
					removePlugins : "elementspath", 
					filebrowserUploadUrl: '/admin/product/editor/imageUpload'};
	
			CKEDITOR.replace("pdDetl", ckeditor_config);
		});
	</script>
</body>
</html>