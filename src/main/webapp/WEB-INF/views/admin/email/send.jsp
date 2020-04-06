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
						<h3 class="box-title">Compose New Message</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
					<form id="formMail" action="/admin/email/send" method="post">
						<div class="form-group">
							<select id="receiveGroup" class="form-control select2"
											style="width: 49.8%; display: inline-block;" required>
								<option value="a" selected="selected">전체 전송</option>
								<option value="i">직접 입력</option>
							</select>
							<input class="form-control" style="width: 49.8%; display: inline-block;" id="receiveMail" name="receiveMail" readonly="readonly" placeholder="To:" value="To: All Members">
						</div>
						<div class="form-group">
							<input class="form-control" id="senderName" name="senderName" placeholder="From:">
						</div>
						<div class="form-group">
							<input class="form-control" id="subject" name="subject" placeholder="Subject:">
						</div>
						
						<div class="form-group">
						<textarea class="form-control" id="compose-textarea" name="message" rows="3" placeholder="Enter ..."></textarea>
						</div>
					</form>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<div class="pull-right">
							<button type="button" id="btnSend" class="btn btn-primary">
								<i class="fa fa-envelope-o"></i> Send
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
		var result = '${msg}';

		if (result == 'SUCCESS') {
			alert("메일 전송이 완료되었습니다.");
			location.replace(self.location);
		}
		
		if (result == 'FAIL') {
			alert("메일 전송이 실패하였습니다.");
			location.replace(self.location);
		}

		
				
	</script>

	<script>
		$(() => {

			$('#btnDiscard').on('click', () => {

				location.replace(self.location);

			});

			$('#receiveGroup').on('change', function() {
				if($(this).val() == 'a') {
					$('#receiveMail').attr('readonly', 'readonly');
					$('#receiveMail').val('To: All Members');
				}
				else {
					$('#receiveMail').removeAttr('readonly');
					$('#receiveMail').val('');
				}
			});

			$('#btnSend').on('click', () => {

				let formObj = $('#formMail');
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
	
			CKEDITOR.replace("compose-textarea", ckeditor_config);
		});
	</script>
</body>
</html>