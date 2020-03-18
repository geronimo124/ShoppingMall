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
								<h3 class="box-title">READ PRODUCT</h3>
							</div>
							<!-- /.box-header -->

								<div class="box-body">
									<div class="form-group">
										<label style="display: block;">상품 카테고리</label>
										<input type="text" class="form-control" style="width: 49.8%; display: inline-block;" 
										value="${productVO.ctgyPtnm }" readonly="readonly">
										<input type="text" class="form-control" style="width: 49.8%; display: inline-block;" 
										value="${productVO.ctgyNm }" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="pdNm">상품 이름</label> <input type="text" name='pdNm'
											id="pdNm" class="form-control" value="${productVO.pdNm }"
											readonly="readonly">
									</div>
									<div class="form-group">
										<label for="pdTag">상품 가격</label> <input type="text"
											name='pdTag' id="pdTag" class="form-control"
											value="${productVO.pdTag }" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="pdSale">할인율</label> <input type="text"
											name='pdSale' id="pdSale" class="form-control" value="${productVO.pdSale }"
											readonly="readonly">
									</div>
									<div class="form-group">
										<label for="pdStock">재고 수량</label> <input type="text"
											name='pdStock' id="pdStock" class="form-control"
											value="${productVO.pdStock }" readonly="readonly">
									</div>
									<div class="form-group">
										<label for="pdDetl">상품 내용</label>
										<textarea class="form-control" id="pdDetl" name="pdDetl"
											rows="3" readonly="readonly">${productVO.pdDetl }</textarea>
									</div>
									<div class="form-group">
										<label for="file">대표 이미지 설정</label> <input type="text"
											name='pdImg' id="pdImg" class="form-control" value="${productVO.pdImg }"
											readonly="readonly">
									</div>
								</div>

								<!-- /.box-body -->

								<div class="box-footer">

									<button type="button" id="btnModify" class="btn btn-primary">수정</button>
									<button type="button" id="btnCancel" class="btn btn-primary">취소</button>

								</div>

							<form role="form" id="hiddenForm">
								<input type="hidden" name="pdNo" value="${productVO.pdNo }" />
								<input type="hidden" name="page" value="${cri.page }" />
								<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
								<input type='hidden' name='searchType' value="${cri.searchType}">
								<input type="hidden" name="keyword" value="${cri.keyword }" />
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
		$(() => {

			let hiddenFormObj = $('#hiddenForm');

			$('#btnModify').on('click', () => {

				hiddenFormObj.attr('action', '/admin/product/modify');
				hiddenFormObj.attr('method', 'get');
				hiddenFormObj.submit();
				
			});
			
			$('#btnCancel').on('click', () => {

				hiddenFormObj.attr('action', '/admin/product/list');
				hiddenFormObj.attr('method', 'get');
				hiddenFormObj.submit();
				
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