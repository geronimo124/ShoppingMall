<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
<!-- daterange picker -->
<link rel="stylesheet"
	href="/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/admin/include/left.jsp"%>

		<div class="content-wrapper">
			<!-- Main content -->
			<section class="conten	t">
				<div class="row">
					<!-- left column -->


					<div class="col-md-12">
						<!-- general form elements -->
						<div class='box'>
							<div class="box-header with-border">
								<h3 class="box-title">Order List</h3>
							</div>




							<!-- /.box-header -->
							<!-- form start -->
							<form class="form-horizontal" id="formSearch" action="/admin/order/list" method="post">
								<div class="box-body">
									<div class="form-group">
										<label for="searchNum" class="col-sm-1 control-label">검색어</label>

										<div class="col-sm-11 row">
											<select id="searchNum" name="searchNum"
												class="form-control select col-sm-1"
												style="width: 150px; display: inline-block;">
												<option value="x"
													<c:out value="${cri.getValue('searchNum') == null?'selected':''}"/>>---</option>
												<option value="ordNo"
													<c:out value="${cri.getValue('searchNum') == 'ordNo'?'selected':''}"/>>주문번호</option>
												<option value="ordPriceUp"
													<c:out value="${cri.getValue('searchNum') == 'ordPriceUp'?'selected':''}"/>>결제금액(이상)</option>
												<option value="ordPriceDown"
													<c:out value="${cri.getValue('searchNum') == 'ordPriceDown'?'selected':''}"/>>결제금액(이하)</option>
											</select> <input type="text" name="searchNumKey" class="form-control col-sm-5"
												style="width: 500px; display: inline-block;" value="${cri.getValue('searchNumKey') }">
										</div>
									</div>
									<div class="form-group">
										<label for="orderDate" class="col-sm-1 control-label">기간</label>

										<div class="col-sm-11">
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">
														<i class="fa fa-calendar"></i>
													</div>
													<input type="text" class="form-control" name="orderDate" id="orderDate"
														style="width: 1000px;" value="${cri.getValue('orderDate') }">
												</div>
												<!-- /.input group -->
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="searchProduct" class="col-sm-1 control-label">상품</label>

										<div class="col-sm-11 row">
											<select id="searchProduct" name="searchProduct"
												class="form-control select col-sm-1"
												style="width: 150px; display: inline-block;">
												<option value="x"
													<c:out value="${cri.getValue('searchProduct') == null?'selected':''}"/>>---</option>
												<option value="pdNm"
													<c:out value="${cri.getValue('searchProduct') == 'pdNm'?'selected':''}"/>>상품명</option>
												<option value="ctgyNm"
													<c:out value="${cri.getValue('searchProduct') == 'ctgyNm'?'selected':''}"/>>카테고리</option>
											</select> <input type="text" class="form-control col-sm-5" name="searchProductKey"
												style="width: 500px; display: inline-block;" value="${cri.getValue('searchProductKey') }">
										</div>
									</div>
									<div class="form-group">
										<label for="orderStatus" class="col-sm-1 control-label">주문상태</label>
										<div class="col-sm-11 row">
											<div class="checkbox" style="width:200px; display:inline-block;">
												<label style="width:150px;"><input type="checkbox" id="checkAllStatus" name="orderStatus" value="A"
												<c:out value="${cri.getValue('searchOrderStatus') == null || cri.getValue('searchOrderStatus') == 'A' ?'checked':''}"/>> 전체</label>
											</div>
											<div class="checkbox" style="width:200px; display:inline-block;">
												<label style="width:150px;"><input type="checkbox" class="status" value="R" name="orderStatus"
												<c:out value="${cri.getValue('searchOrderStatus') == null || cri.getValue('searchOrderStatus').indexOf('R') > -1 ?'checked':''}"/>> 배송 준비중</label>
											</div>
											<div class="checkbox" style="width:200px; display:inline-block;">
												<label style="width:150px;"><input type="checkbox" class="status" value="D" name="orderStatus"
												<c:out value="${cri.getValue('searchOrderStatus') == null || cri.getValue('searchOrderStatus').indexOf('D') > -1 ?'checked':''}"/>> 배송중</label>
											</div>
											<div class="checkbox" style="width:200px; display:inline-block;">
												<label style="width:150px;"><input type="checkbox" class="status" value="S" name="orderStatus"
												<c:out value="${cri.getValue('searchOrderStatus') == null || cri.getValue('searchOrderStatus').indexOf('S') > -1 ?'checked':''}"/>> 배송완료</label>
											</div>
										</div>
										<input type="hidden" id="searchOrderStatus" name="searchOrderStatus" value="A">
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer text-center">
									<button id="btnSearch" type="button" class="btn btn-primary">검색</button>
								</div>
								<!-- /.box-footer -->
								<input type="hidden" id="page" name="page" value="${cri.page }">
								<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
							</form>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
								<button type="button" id="excel" class="btn btn-sm btn-success"
									onclick="ReportToExcelConverter()" style="float: right;">엑셀
									출력</button>
								<button type="button" id="btnCheckDelete"
									class="btn btn-sm btn-danger" style="float: right;">체크
									삭제</button>
								<button type="button" id="btnCheckModify"
									class="btn btn-sm btn-info" style="float: right;">체크
									수정</button>
							</div>
							<div class="box-body">
								<table class="table table-bordered text-center" id="orderList">
									<tr>
										<th><input type="checkbox" class="checkbox" id="checkAll"></th>
										<th>번호</th>
										<th>주문일자</th>
										<th>상품명</th>
										<th>결제금액</th>
										<th>주문상세</th>
										<th>배송현황</th>
									</tr>

									<c:forEach items="${orderList}" var="orderVO">

										<tr>
											<td><input type="checkbox" class="checkbox check"
												name="checkbox" value="${orderVO.ordNo }"></td>
											<td>${orderVO.ordNo }</td>
											<td>${orderVO.ordDt }</td>
											<td>${orderVO.pdNm }</td>
											<td>${orderVO.ordPrice }</td>
											<td><a
												href="/admin/order/detail?ordNo=${orderVO.ordNo }"><button
														type="button" class="btn">보기</button></a></td>
											<td><select name="ordStatus"
												class="form-control select2 ordStatus" required>
													<c:if test="${orderVO.ordStatus eq 'R' }">
														<option value="R" selected="selected">배송 준비중</option>
														<option value="D">배송중</option>
														<option value="S">배송완료</option>
													</c:if>
													<c:if test="${orderVO.ordStatus eq 'D' }">
														<option value="R">배송 준비중</option>
														<option value="D" selected="selected">배송중</option>
														<option value="S">배송완료</option>
													</c:if>
													<c:if test="${orderVO.ordStatus eq 'S' }">
														<option value="R">배송 준비중</option>
														<option value="D">배송중</option>
														<option value="S" selected="selected">배송완료</option>
													</c:if>
											</select></td>
										</tr>

									</c:forEach>

								</table>
							</div>
							<!-- /.box-body -->


							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">

										<c:if test="${pageMaker.prev}">
											<li><a id="prev">&laquo;</a></li>
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a class="idx">${idx }</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a id="next">&raquo;</a></li>
										</c:if>

									</ul>
								</div>

							</div>
							<!-- /.box-footer-->
						</div>
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


	<!-- date-range-picker -->
	<script src="/bower_components/moment/min/moment.min.js"></script>
	<script
		src="/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script>

		var formObj = $('#formSearch');

		function formSearchSubmit() {
	
			let status = '';
			
			$('.status:checked').each(function() {
				status += $(this).val();
			});
	
			if(status == 'RDS')
				$('#searchOrderStatus').val('A');
			else
				$('#searchOrderStatus').val(status);
	
			formObj.submit();
		}
		
		$(() => {

			$('#orderDate').daterangepicker({
				startDate : '1990/01/01',
				locale : {
					format : 'YYYY/MM/DD'
				}
			});

			$('.idx').on('click', function() {

				$('#page').val($(this).text());
				formObj.submit();
				
			});

			$('#prev').on('click', () => {

				$('#page').val('${pageMaker.startPage - 1}');
				formSearchSubmit();
				
			});
			
			$('#next').on('click', () => {

				$('#page').val('${pageMaker.endPage + 1}');
				
				formSearchSubmit();
				
			});

			
			$('#btnSearch').on('click', () => {
				
				$('#page').val('1');
				formSearchSubmit();

			});

			if($('#checkAllStatus').is(':checked'))
				$('.status').each(function() { $(this).prop('checked', true);});


		    $('#checkAllStatus').on('click', function () { $('.status').prop('checked', this.checked) });

			$('#checkAll').on('click', function () { $('.check').prop('checked', this.checked) });	

			$('#btnCheckModify').on('click', function() {

				let modifyArr = [];

				$('tr .check:checked').each(function() {
					
			        let data = {ordNo	: $(this).val(),
					        	ordStatus   : $(this).parent().parent().find('.ordStatus').val() 
	                };

	                modifyArr.push(data);
				});

				$.ajax({
			        type		: "post",
			        url 		: "modifyChecked",
			        data		: JSON.stringify(modifyArr),
			        contentType : "application/json",
			        success 	: function(data) {

						if(data == 'SUCCESS')
							alert('선택한 주문이 수정되었습니다.');
			        	
						self.location = "list"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
			        }
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
			        data		: {orderList : checkArr},
			        success 	: function(data) {

						if(data == 'SUCCESS')
							alert('선택한 주문이 삭제되었습니다.');
						
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

	<script src="/plugins/table2excel/jquery.table2excel.js"></script>
	<script>
	
		function ReportToExcelConverter() { 
			
			$("#orderList").table2excel({ 
				exclude: ".noExl", 
				name: "Excel Document Name", 
				filename: "orderList" +'.xls', 
				fileext: ".xls", 
				exclude_img: true, 
				exclude_links: true, 
				exclude_inputs: true 
			}); 
		};

	</script>
</body>
</html>