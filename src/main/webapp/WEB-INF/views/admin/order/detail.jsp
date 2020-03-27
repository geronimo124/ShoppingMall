<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/admin/include/left.jsp"%>

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
							</tr>

							<c:forEach items="${orderList}" var="orderVO">

								<tr>
									<td><img
											src="/product/displayFile?fileName=${orderVO.pdImg }"
											alt="Attachment">
									</td>
									<td>${orderVO.pdNm }</td>
									<td>${orderVO.bskQty }</td>
									<td>${orderVO.pdTag }</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.ordDt}" /></td>
									<td>						
										<c:choose>
											<c:when test="${order.ordStatus eq 'R' }">
												배송 준비중
											</c:when>
											<c:when test="${order.ordStatus eq 'D' }">
												배송중
											</c:when>
											<c:when test="${order.ordStatus eq 'S' }">
												배송완료
											</c:when>
										</c:choose>
									</td>
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
										<input type="text" class="form-control" style="width: 430px;" readonly="readonly"
											id="ordNm" name="ordNm" value="${order.ordNm }">
									</div>
								</div>
								<div class="form-group form-row">
									<label class="col-sm-1 control-label">연락처</label>

									<div class="col-sm-11">
										<input type="text" class="form-control" style="width: 430px;" readonly="readonly"
											id="ordNm" name="ordNm" value="${order.ordPhone }">
									</div>
								</div>
								<div class="form-group form-row">
									<label for="btnSearchZip" class="col-sm-1 control-label">주소</label>

									<div class="col-sm-11">
										<div class="form-row">
											<input type="text" class="form-control"
												style="width: 80px; display: inline-block;" id="ordZip" name="ordZip"
												readonly="readonly" value="${order.ordZip }">
										</div>
										<input type="text" class="form-control" name="ordAddr"
											style="width: 430px; display: inline-block;"
											readonly="readonly" value="${order.ordAddr }"> <input
											type="text" class="form-control" name="ordDeaddr"
											style="width: 430px; display: inline-block;" readonly="readonly"
											value="${order.ordDeaddr }">
									</div>
								</div>
								<div class="form-group">
									<label for="mbNm" class="col-sm-1 control-label">주문 메세지</label>

									<div class="col-sm-11">
										<textarea name="ordMsg" rows="5" class="form-control" readonly="readonly">${order.ordMsg }</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="text-center">
						<br />
						<button id="btnOrderList" type="button" class="btn btn-lg btn-primary text-center" style="width:300px;">주문 목록</button>
						</div>
						<br />
					</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@include file="/WEB-INF/views/admin/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@include file="/WEB-INF/views/admin/include/aside.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>
	<script>

		$(() => {

			$('#btnOrderList').on('click', () => {

				self.location = '/admin/order/list';
				
			});
		});

	</script>
</body>
</html>