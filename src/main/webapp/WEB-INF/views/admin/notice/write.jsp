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

				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">공지사항 등록</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
					<form id="formNotice" action="/admin/notice/write" method="post">
						<div class="form-group">
							<input class="form-control" id="ntTitle" name="ntTitle" placeholder="Title:">
						</div>
						<div class="form-group">
						<textarea class="form-control" id="ntContent" name="ntContent" rows="10" placeholder="Enter ..."></textarea>
						</div>
					</form>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<div class="pull-right">
							<button type="button" id="btnWrite" class="btn btn-primary">
								<i class="fa fa-envelope-o"></i> Write
							</button>
						</div>
						<button type="reset" id="btnDiscard" class="btn btn-default">
							<i class="fa fa-times"></i> Discard
						</button>
					</div>
					<!-- /.box-footer -->
				</div>
				<!-- /. box -->

			</section>

		</div>
	</div>
	<!-- /.content -->
	<%@include file="/WEB-INF/views/admin/include/footer.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/aside.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>

	<script>
		$(() => {

			$('#btnDiscard').on('click', () => {

				location.replace(self.location);

			});

			$('#btnWrite').on('click', () => {

				let formObj = $('#formNotice');
				formObj.submit();

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
	
			CKEDITOR.replace("ntContent", ckeditor_config);
		});
	</script>
</body>
</html>