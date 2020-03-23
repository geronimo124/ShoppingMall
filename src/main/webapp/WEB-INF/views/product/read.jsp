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

				<div class="box box-primary">
					<div class="row">
						<div class="col-12 col-sm-6">
							<div class="col-12 text-center">
							<p></p>
								<img src="/product/displayFile?fileName=${productVO.pdImg }"
									class="product-image my-3" alt="Attachment">
							</div>
							<p></p>
						</div>
						<div class="col-12 col-sm-4">
							<h3 class="my-3">${productVO.pdNm }</h3>

							<hr>
							<h4>Available Colors</h4>
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
								<label class="btn btn-default text-center active"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off" checked=""> Green <br> <i
									class="fa fa-circle text-green"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option2"
									autocomplete="off"> Blue <br> <i
									class="fa fa-circle text-blue"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option3"
									autocomplete="off"> Purple <br> <i
									class="fa fa-circle text-purple"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option4"
									autocomplete="off"> Red <br> <i
									class="fa fa-circle text-red"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option5"
									autocomplete="off"> Orange <br> <i
									class="fa fa-circle text-orange"></i>
								</label>
							</div>

							<h4 class="mt-3">
								Size <small>Please select one</small>
							</h4>
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
								<label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">S</span> <br>
									Small
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">M</span> <br>
									Medium
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">L</span> <br>
									Large
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">XL</span> <br>
									Xtra-Large
								</label>
							</div>

							<div class="py-2 px-3 mt-4">
								<h2 class="mb-0">
									<fmt:formatNumber value="${productVO.pdTag }" pattern="#,###" />
									원
								</h2>
								<h4 class="mt-0">
									<small>Sale: ${productVO.pdSale }%</small>
								</h4>
								<h4>
									수량 : <input type="number" class="form-control-sm form-control text-center">			
								</h4>
							</div>

							<div class="my-4">
								<div id="btnOrder" class="btn btn-primary btn-lg btn-flat">
									<i class="fa fa-shopping-cart mr-2"></i>즉시 구매
								</div>

								<div id="btnBasket" class="btn btn-default btn-lg btn-flat">
									<i class="fa fa-cart-plus mr-2"></i>장바구니 담기
								</div>
							</div>
							<p></p>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<div class="box box-default">
					<div class="box-body text-center">${productVO.pdDetl}</div>
				</div>
					<!-- /.card -->
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

			$('#btnOrder').on('click', () => {

				self.location = '/product/order';

			});

			$('#btnBasket').on('click', () => {

				$.ajax({
					   url:"insertBasket/" + pdNo,
					   type:"delete",
					   dataType:"text",
					   success:() => {

							self.location = "list"
								+ '${pageMaker.makeQuery(1)}'
								+ "&searchType="
								+ $("select option:selected").val()
								+ "&keyword="
								+ $('#keywordInput').val();


						}

				 });
			

			});
				

		});
	
	</script>
</body>
</html>