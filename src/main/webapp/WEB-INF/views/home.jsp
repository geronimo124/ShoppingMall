<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
				<div class="album py-5 bg-light">

					<div class="row">
						<c:forEach items="${productList}" var="productVO">
							<c:if test="${productVO.pdStatus eq 'Y' }">
								<div class="col-md-4">
									<div>
										<img src="/product/displayFile?fileName=${productVO.pdImg }" width="100%" height="100%" />
									
										<!-- 
										<a
											href="/product/read${pageMaker.makeSearch(pageMaker.cri.page) }&pdNo=${productVO.pdNo}">
											<img src="/product/displayFile?fileName=${productVO.pdImg }"
											alt="Attachment">
										</a>
									 -->
										<div>
											<p><strong>${productVO.pdNm }</strong></p>
											<div
												class="d-flex justify-content-between align-items-center">
												${productVO.pdTag }원 <small class="text-muted">Sale
													: ${productVO.pdSale }%</small>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>

				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<!-- 하위 카테고리 목록 -->

				<!-- 상품 리스트 -->

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
</body>
</html>