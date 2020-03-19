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
								<h3 class="box-title">Product List</h3>
							</div>


							<div class='box-body'>

								<select name="searchType">
									<option value="x"
										<c:out value="${cri.searchType == null?'selected':''}"/>>
										---</option>
									<option value="n"
										<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
										상품 이름</option>
									<option value="c"
										<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
										카테고리</option>
									<option value="u"
										<c:out value="${cri.searchType eq 'u'?'selected':''}"/>>
										상한가</option>
									<option value="d"
										<c:out value="${cri.searchType eq 'd'?'selected':''}"/>>
										하한가</option>
								</select> <input type="text" name='keyword' id="keywordInput"
									value='${cri.keyword }'>
								<button id='btnSearch'>검색</button>
								<button id='btnRegister'>상품 등록</button>
							</div>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
								<button type="button" id="btnCheckDelete" class="btn btn-sm btn-danger" style="float: right;">체크 삭제</button>
								<button type="button" id="btnCheckModify" class="btn btn-sm btn-info" style="float: right;">체크 수정</button>
							</div>
							<div class="box-body">
								<table class="table table-bordered text-center">
									<tr>
										<th></th>
										<th>번호</th>
										<th>썸네일</th>
										<th>카테고리</th>
										<th>상품 이름</th>
										<th>가격</th>
										<th>세일</th>
										<th>진열</th>
										<th>등록 날짜</th>
										<th>재고 수량</th>
										<th>수정</th>
										<th>삭제</th>
									</tr>

									<c:forEach items="${productList}" var="productVO">

										<tr>
											<td><input type="checkbox" class="checkbox check" name="checkbox" value="${productVO.pdNo }"></td>
											<td>${productVO.pdNo }</td>
											<td><a href="/admin/product/read${pageMaker.makeSearch(pageMaker.cri.page) }&pdNo=${productVO.pdNo}">
											<img src="/admin/product/displayFile?fileName=${productVO.pdImg }" alt="Attachment"></a></td>
											<td>${productVO.ctgyNm }</td>
											<td><a href="/admin/product/read${pageMaker.makeSearch(pageMaker.cri.page) }&pdNo=${productVO.pdNo}">
											${productVO.pdNm }</a></td>
											<td><input type="text" pattern="[0-9]+" class="text-center pdTag" style="width:50px;" value="${productVO.pdTag }"></td>
											<td><input type="text" pattern="[0-9]+" class="text-center pdSale" style="width:30px;" value="${productVO.pdSale }"></td>
											<c:if test="${productVO.pdStatus eq 'Y' }">
											<td><input type="checkbox" class="checkbox status" name="pdStatus" checked>
											</c:if>
											<c:if test="${productVO.pdStatus eq 'N' }">
											<td><input type="checkbox" class="checkbox status" name="pdStatus">
											</c:if>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${productVO.pdEnldt}" /></td>
											<td><input type="text" pattern="[0-9]+" class="text-center pdStock" style="width:30px;" value="${productVO.pdStock }"></td>
											<td><button type="button" class="modbtn btn btn-success" id="btnModify">수정</button></td>
											<td><button type="button" class="delbtn btn btn-danger" id="btnDelete">삭제</button></td>

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
			$('#btnRegister').on('click', () => {

				self.location = "register";

			});
			
			$('#btnSearch').on('click', () => {

				self.location = "list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword="
					+ $('#keywordInput').val();

			});

			$('.delbtn').each(function() {

				let pdNo = $(this).parent().parent().find('.checkbox').val();

				$(this).on('click', function() {

					$.ajax({
						   url:"delete/" + pdNo,
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
					
			$('.modbtn').each(function() {

				let pdNo = $(this).parent().parent().find('.checkbox').val();

				$(this).on('click', function() {

					self.location = "modify"
						+ '${pageMaker.makeQuery(1)}'
						+ "&searchType="
						+ $("select option:selected").val()
						+ "&keyword="
						+ $('#keywordInput').val()
						+ "&pdNo=" + pdNo;

				});


			});

			$('#btnCheckModify').on('click', function() {

				let modifyArr = [];

				$('tr .check:checked').each(function() {

					let status = 'N';
					
					if($(this).parent().parent().find('.status').is(':checked'))
						status = 'Y';
					
			        let data = {pdNo	: $(this).val(),
					        	pdTag   : $(this).parent().parent().find('.pdTag').val(), 
	                      	 	pdSale  : $(this).parent().parent().find('.pdSale').val(), 
	                        	pdStock  : $(this).parent().parent().find('.pdStock').val(),
	                        	pdStatus : status
	                };

	                modifyArr.push(data);
				});
				
				$.ajax({
			        type		: "post",
			        url 		: "modifyChecked",
			        data		: JSON.stringify(modifyArr),
			        contentType : "application/json",
			        success 	: function(data) {
			        	
						self.location = "list"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
			        }
			  	});
				/*
				$('tr .check:checked').each(function() {

					let status = 'N';
					
					if($(this).parent().parent().find('.status').is(':checked'))
						status = 'Y';

			        let data = {pdNo    : $(this).val(),
					        	pdTag   : $(this).parent().parent().find('.pdTag').val(), 
		                        pdSale  : $(this).parent().parent().find('.pdSale').val(), 
		                        pdStock  : $(this).parent().parent().find('.pdStock').val(),
		                        pdStatus : status
		         	};

		             $.ajax({
					        type		: "put",
					        url 		: "modifyChecked",
					        data		:  JSON.stringify(data), 
					        contentType : "application/json",
					        success 	: function(data) {

								self.location = "list"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword="
									+ $('#keywordInput').val();
					        }
					  });
				});
				*/
			});

			$('#btnCheckDelete').on('click', function() {

				let checkArr = [];
				
				$('tr .check:checked').each(function() {
					checkArr.push($(this).val());
				});

				$.ajax({
			        type		: "get",
			        url 		: "deleteChecked",
			        data		: {productList : checkArr},
			        success 	: function(data) {

						self.location = "list"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
			        }
			  	});
				
			});
			// 파일 업로드한거 삭제 요망
			/*
			$(".uploadedList .delbtn").each((index, item) => {
				
				$.ajax({
					   url:"/deleteFile",
					   type:"post",
					   data: {fileName:$(item).attr("href")},
					   dataType:"text",
					   success:() => {}

				 });
				
			});
			*/
		});
	</script>
</body>
</html>