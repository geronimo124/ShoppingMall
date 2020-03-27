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
								<th>수정</th>
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
									<td class="sum"><fmt:parseNumber value="${basketVO.pdTag}" pattern="#,###"/>
									(${basketVO.pdSale }%)</td>
									<td><input class="text-center" type="number" value="${basketVO.bskQty }"></td>
									<td class="mile"><fmt:parseNumber value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty / 100}" integerOnly="true" /></td>
									<td class="total"><fmt:parseNumber value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty}" integerOnly="true" pattern="#,###" /></td>
									<td><button type="button" class="modbtn btn btn-info"
											id="btnModify">수정</button>
										<button type="button" class="delbtn btn btn-danger"
											id="btnDelete">삭제</button>
									</td>
								</tr>

							</c:forEach>

						</table>
					</div>
				</div>
				<div class="box box-info">
					<div class="box-body row text-center">
						<div class="col-md-4">
							<strong>총 상품 금액<br />
							<span id="sum"></span></strong>
						</div>
						<div class="col-md-4">
							<strong>총 적립금<br />
							<span id="mile"></span></strong>
						</div>
						<div class="col-md-4">
							<strong>결제 예정 금액<br />
							<span id="total"></span></strong>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 text-right">
							<button id="btnCheckOrder" type="button" class="btn btn-lg btn-default" style="width:300px;">선택상품 주문</button>
						</div>
						<div class="col-md-6 text-left">
							<button id="btnOrder" type="button" class="btn btn-lg btn-primary" style="width:300px;">전체 주문</button>
						</div>
					</div>
					<br />
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

			let orderMsg = '${orderMsg}';

			if(orderMsg == 'FAIL')
				alert('주문할 상품이 없습니다.');

			$('.checkbox').prop('checked', true);
			
			let sum = 0, mile = 0, total = 0;
			$('.check').each(function() {
				sum += parseInt($(this).parent().parent().find('.sum').html());
				mile += parseInt($(this).parent().parent().find('.mile').html());
				total += parseInt($(this).parent().parent().find('.total').html());
			});
			$('#sum').html(sum);
			$('#mile').html(mile);
			$('#total').html(total);

			
			$('.checkbox').on('change', function() {

				let sum = 0, mile = 0, total = 0;

				$('.check:checked').each(function() {
					sum += parseInt($(this).parent().parent().find('.sum').html());
					mile += parseInt($(this).parent().parent().find('.mile').html());
					total += parseInt($(this).parent().parent().find('.total').html());
				});

				$('#sum').html(sum);
				$('#mile').html(mile);
				$('#total').html(total);
			});

			$('#checkAll').on('click', function () { 
				$('.check').prop('checked', this.checked) 

			});

			$('.delbtn').each(function() {

				let pdNo = $(this).parent().parent().find('.checkbox').val();

				$(this).on('click', function() {

					$.ajax({
						   url:"delete/" + pdNo,
						   type:"delete",
						   dataType:"text",
						   success:(data) => {
								self.location = "/basket/list";
							}

					 });

				});

			});

			$('.modbtn').each(function() {

				let pdNo = $(this).parent().parent().find('.checkbox').val();
				
				$(this).on('click', function() {

					$.ajax({
						   url:"modify",
						   type:"post",
						   data:{pdNo : pdNo,
							   	 bskQty : $(this).parent().parent().find("input[type='number']").val()},
						   dataType:"text",
						   success:(data) => {
								self.location = "/basket/list";
							}

					 });

				});

			});

			$('#btnCheckDelete').on('click', function() {

				let checkArr = [];
				
				$('tr .check:checked').each(function() {
					checkArr.push($(this).val());
				});

				$.ajax({
			        type		: "POST",
			        url 		: "deleteChecked",
			        data		: {basketList : checkArr},
			        success 	: function(data) {

						self.location = "/basket/list";
			        }
			  	});
				
			});

			$('#btnCheckModify').on('click', function() {

				let modifyArr = [];

				$('tr .check:checked').each(function() {
					
			        let data = {pdNo	: $(this).val(),
			        		    bskQty  : $(this).parent().parent().find("input[type='number']").val()
	                };

	                modifyArr.push(data);
				});
				
				$.ajax({
			        type		: "post",
			        url 		: "modifyChecked",
			        data		: JSON.stringify(modifyArr),
			        contentType : "application/json",
			        success 	: function(data) {
			        	
						self.location = "/basket/list";
			        }
			  	});
			});

			$('#btnCheckOrder').on('click', () => {

				let queryString = "?";
				
				$('tr .check:checked').each(function() {
					queryString += "&pdNo=" + $(this).val();
				});

				if(queryString == "?")
					alert('주문할 상품이 없습니다.');
				else
					self.location = "/order/insert" + queryString;

			});

			$('#btnOrder').on('click', () => {

				let queryString = "?";

				$('tr .check').each(function() {
					queryString += "&pdNo=" + $(this).val();
				});

				if(queryString == "?")
					alert('주문할 상품이 없습니다.');
				else
					self.location = "/order/insert" + queryString;
				
			});

		});

	</script>
</body>
</html>