<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/include/left.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header <small>Optional description</small>
				</h1>
				<%=application.getRealPath("/")%>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">장바구니 리스트</h3>
						<button type="button" id="btnCheckDelete" class="btn btn-sm btn-danger" style="float: right;">체크 삭제</button>
						<button type="button" id="btnCheckModify" class="btn btn-sm btn-info" style="float: right;">체크 수정</button>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered text-center">
							<tr>
								<th><input type="checkbox" class="checkbox" id="checkAll"></th>
								<th>사진</th>
								<th>상품명</th>
								<th>판매가</th>
								<th>수량</th>
								<th>적립금</th>
								<th>합계</th>
								<th>삭제</th>
							</tr>

							<c:forEach items="${basketList}" var="basketVO">

								<tr>
									<td><input type="checkbox" class="checkbox check"
										name="checkbox" value="${basketVO.pdNo }"></td>
									<td><a
										href="/product/read?pdNo=${basketVO.pdNo}">
											<img
											src="/product/displayFile?fileName=${basketVO.pdImg }"
											alt="Attachment">
									</a></td>
									<td>${basketVO.pdNm }</td>
									<td><fmt:parseNumber value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100}" integerOnly="true" pattern="#,###"/>
									(${basketVO.pdSale }%)</td>
									<td><input type="number" value="${basketVO.bskQty }"></td>
									<td><fmt:parseNumber value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty / 100}" integerOnly="true" /></td>
									<td><fmt:parseNumber value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty}" integerOnly="true" pattern="#,###" /></td>
									<td><button type="button" class="delbtn btn btn-danger"
											id="btnDelete">삭제</button></td>
								</tr>

							</c:forEach>

						</table>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@include file="/WEB-INF/views/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@include file="/WEB-INF/views/include/aside.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	<script>

		$(() => {

			$('#checkAll').on('click', function () { $('.check').prop('checked', this.checked) });
			
		});

	</script>
</body>
</html>