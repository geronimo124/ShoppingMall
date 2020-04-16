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
						<h3 class="box-title">주문현황 리스트</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered text-center">
							<tr>
								<th>사진</th>
								<th>상품명</th>
								<th>수량</th>
								<th>결제 가격</th>
								<th>주문 날짜</th>
								<th>배송 상태</th>
								<th>리뷰</th>
							</tr>

							<c:forEach items="${orderList}" var="orderVO">

								<tr>
									<td class="hidden">${orderVO.pdNo }</td>
									<td><img
										src="/product/displayFile?fileName=${orderVO.pdImg }"
										alt="Attachment"></td>
									<td>${orderVO.pdNm }</td>
									<td>${orderVO.bskQty }</td>
									<td>${orderVO.pdTag }</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${order.ordDt}" /></td>
									<td><c:choose>
											<c:when test="${order.ordStatus eq 'R' }">
												배송 준비중
											</c:when>
											<c:when test="${order.ordStatus eq 'D' }">
												배송중
											</c:when>
											<c:when test="${order.ordStatus eq 'S' }">
												배송완료
											</c:when>
										</c:choose></td>
									<td><a class="btn btn-primary btn-xs review"
										data-toggle="modal" data-target="#reviewModal">리뷰</a></td>
								</tr>

							</c:forEach>

						</table>
					</div>
				</div>
				<div class="box box-default">
					<div class="box-header with-border">
						<h3 class="box-title">배송 정보</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<div class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label for="ordNm" class="col-sm-1 control-label">이름</label>

								<div class="col-sm-11">
									<input type="text" class="form-control" style="width: 430px;"
										readonly="readonly" id="ordNm" name="ordNm"
										value="${order.ordNm }">
								</div>
							</div>
							<div class="form-group form-row">
								<label class="col-sm-1 control-label">연락처</label>

								<div class="col-sm-11">
									<input type="text" class="form-control" style="width: 430px;"
										readonly="readonly" id="ordNm" name="ordNm"
										value="${order.ordPhone }">
								</div>
							</div>
							<div class="form-group form-row">
								<label for="btnSearchZip" class="col-sm-1 control-label">주소</label>

								<div class="col-sm-11">
									<div class="form-row">
										<input type="text" class="form-control"
											style="width: 80px; display: inline-block;" id="ordZip"
											name="ordZip" readonly="readonly" value="${order.ordZip }">
									</div>
									<input type="text" class="form-control" name="ordAddr"
										style="width: 430px; display: inline-block;"
										readonly="readonly" value="${order.ordAddr }"> <input
										type="text" class="form-control" name="ordDeaddr"
										style="width: 430px; display: inline-block;"
										readonly="readonly" value="${order.ordDeaddr }">
								</div>
							</div>
							<div class="form-group">
								<label for="mbNm" class="col-sm-1 control-label">주문 메세지</label>

								<div class="col-sm-11">
									<textarea name="ordMsg" rows="5" class="form-control"
										readonly="readonly">${order.ordMsg }</textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="text-center">
						<br />
						<button id="btnOrderList" type="button"
							class="btn btn-lg btn-primary text-center" style="width: 300px;">주문
							목록</button>
					</div>
					<br />
				</div>

				<!-- Modal -->
				<div id="reviewModal" class="modal modal-default fade" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"></h4>
							</div>
							<div class="modal-body" data-rno>
								<p>
									<select id="revGrade" name="revGrade" class="select2">
										<option value="1">★</option>
										<option value="2">★★</option>
										<option value="3">★★★</option>
										<option value="4">★★★★</option>
										<option value="5" selected="selected">★★★★★</option>
									</select> <input type="text" id="revTitle" name="revTitle"
										class="form-control"><br />
									<textarea rows="10" id="revContent" name="revContent" class="form-control"></textarea>
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="btnRegRev">등록</button>
								<button type="button" class="btn btn-info" id="btnModRev">수정</button>
								<button type="button" class="btn btn-danger" id="btnDelRev">삭제</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="pdNo" name="pdNo">
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

			$('#btnOrderList').on('click', () => {

				self.location = '/order/list';
				
			});

			$('.review').on('click', function() {

				$('#pdNo').val($(this).parents('tr').find('.hidden').html());

				$.ajax({
					type : 'get',
					url : '/review/get/' + '${order.ordNo}' + '/' + $('#pdNo').val(),
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "GET"
					},
					dataType : 'text',
					success : function(review) {

						if(review != "") {

							let data = JSON.parse(review);

							$('#revGrade').val(data.revGrade);
							$('#revTitle').val(data.revTitle);
							$('#revContent').val(data.revContent);

							$('.modal-body').attr('data-rno', data.revNo);

							$('#btnRegRev').hide();
							$('#btnModRev').show();
							$('#btnDelRev').show();

						} else {

							$('#revGrade').val('');
							$('#revTitle').val('');
							$('#revContent').val('');

							$('#btnRegRev').show();
							$('#btnModRev').hide();
							$('#btnDelRev').hide();
							
						}			
						
					}
				});

				
			});
			
			$('#btnRegRev').on('click', function() {

				let review = { orddtPdNo : $('#pdNo').val(),
							   orddtOrdNo : '${order.ordNo}',
							   mbId : '${member.mbId}',
							   revTitle : $('#revTitle').val(),
							   revWriter : '${member.mbNick}',
							   revContent : $('#revContent').val(),
							   revGrade : $('#revGrade').val()
				};

				$.ajax({
					   url:"/review",
					   type:"post",
					   data: JSON.stringify(review),
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "POST"
						},
					   dataType:"text",
					   success:(data) => {
							   alert('리뷰가 작성되었습니다.');
						   if(data == 'SUCCESS')
						   $("#reviewModal").modal('hide');
						}

				 });

			});

			$('#btnModRev').on('click', function() {

				$.ajax({
					type : 'put',
					url : '/review',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					data : JSON.stringify({
						revNo : $('.modal-body').attr('data-rno'),
						revTitle : $('#revTitle').val(),
						revWriter : '${member.mbNick}',
						revContent : $('#revContent').val(),
						revGrade : $('#revGrade').val()
					}),
					dataType : 'text',
					success : function(data) {
						if (data == 'SUCCESS') {
							alert("수정 되었습니다.");
						}
						$("#reviewModal").modal('hide');
						getPage("/review/" + '${productVO.pdNo}' + "/" + reviewPage);
					}
				});
			});

			$('#btnDelRev').on('click', () => {

				let revNo = $('.modal-body').attr('data-rno');

				$.ajax({
					type : 'delete',
					url : '/review/' + revNo,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType : 'text',
					success : function(result) {
						if (result == 'SUCCESS') {
							alert("리뷰가 삭제 되었습니다.");
						}
						$("#reviewModal").modal('hide');
						getPage("/review/" + '${productVO.pdNo}' + "/" + reviewPage);
					}
				});
			});
					
		});

	</script>
</body>
</html>