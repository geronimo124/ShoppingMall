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
						<div class='box'>
							<div class="box-header with-border">
								<h3 class="box-title">Order List</h3>
							</div>


							<div class='box-body'>

								<select name="searchType">
									<option value="x"
										<c:out value="${cri.searchType == null?'selected':''}"/>>
										---</option>
									<option value="n"
										<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
										상품 이름</option>
									<option value="s"
										<c:out value="${cri.searchType eq 's'?'selected':''}"/>>
										배송 현황</option>
								</select>
									<input type="text" name='keyword' id="keywordInput" value='${cri.keyword }'>
								<button id='btnSearch'>검색</button>
							</div>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
								<button type="button" id="excel" class="btn btn-sm btn-success" onclick="ReportToExcelConverter()" style="float: right;">엑셀 출력</button>
								<button type="button" id="btnCheckDelete" class="btn btn-sm btn-danger" style="float: right;">체크 삭제</button>
								<button type="button" id="btnCheckModify" class="btn btn-sm btn-info" style="float: right;">체크 수정</button>
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
											<td><input type="checkbox" class="checkbox check" name="checkbox" value="${orderVO.ordNo }"></td>
											<td>${orderVO.ordNo }</td>
											<td>${orderVO.ordDt }</td>
											<td>${orderVO.pdNm }</td>
											<td>${orderVO.ordPrice }</td>
											<td><a href="/admin/order/detail?ordNo=${orderVO.ordNo }"><button type="button" class="btn">보기</button></a></td>
											<td>
												<select name="ordStatus" class="form-control select2 ordStatus" required>
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
												</select>	
											</td>
										</tr>

									</c:forEach>

								</table>
							</div>
							<!-- /.box-body -->


							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">

										<c:if test="${pageMaker.prev}">
											<li><a
												href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a
												href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
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

	<script>
		$(() => {

			$('#btnSearch').on('click', () => {

				self.location = "list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword="
					+ $('#keywordInput').val();

			});

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