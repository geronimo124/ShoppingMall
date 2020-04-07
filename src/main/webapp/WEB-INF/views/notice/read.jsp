<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/include/left.jsp"%>

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
								<input class="form-control" id="ntTitle" name="ntTitle"
									placeholder="Title:" value="${noticeVO.ntTitle }" readonly="readonly">
							</div>
							<div class="form-group">
								<textarea class="form-control" id="ntContent" name="ntContent"
									rows="10" placeholder="Enter ..." readonly="readonly">${noticeVO.ntContent }</textarea>
							</div>
						</form>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<div class="pull-right">
							<button type="reset" id="btnDiscard" class="btn btn-default">
								<i class="fa fa-times"></i> Discard
							</button>
						</div>
					</div>
					<!-- /.box-footer -->

					<form role="form" id="hiddenForm">
						<input type="hidden" name="ntNo" value="${noticeVO.ntNo }" /> <input
							type="hidden" name="page" value="${cri.page }" /> <input
							type="hidden" name="perPageNum" value="${cri.perPageNum }" /> <input
							type='hidden' name='searchType' value="${cri.searchType}">
						<input type="hidden" name="keyword" value="${cri.keyword }" />
					</form>
				</div>
				<!-- /. box -->

			</section>

		</div>
	</div>
	<!-- /.content -->
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	<%@include file="/WEB-INF/views/include/aside.jsp"%>
	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>

	<script>
		$(() => {

			$('#btnDiscard').on('click', () => {

				let hiddenFormObj = $('#hiddenForm');
				
				hiddenFormObj.attr('action', '/notice');
				hiddenFormObj.attr('method', 'get');
				hiddenFormObj.submit();

			});

			
		});
	</script>

</body>
</html>