<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">주문 리스트</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered text-center">
							<tr>
								<th></th>
								<th>상품명</th>
								<th>수량</th>
								<th>판매가</th>
								<th>적립금</th>
							</tr>


							<c:forEach items="${basketList}" var="orderVO">

								<tr>
									<td><img
										src="/product/displayFile?fileName=${basketVO.pdImg }"
										alt="Attachment"></td>
									<td>${basketVO.pdNm }</td>
									<td>${basketVO.bskQty }</td>
									<td><fmt:parseNumber
											value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty}"
											integerOnly="true" pattern="#,###" /></td>
									<td><fmt:parseNumber
											value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty / 100}"
											integerOnly="true" /></td>
								</tr>

							</c:forEach>

						</table>
					</div>
				</div>
				<div class="box box-default">
					<div class="box-header with-border">
						<h3 class="box-title">주문자 정보</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label for="mbNm" class="col-sm-1 control-label">이름</label>

								<div class="col-sm-11">
									<input type="text" class="form-control" id="mbNm" readonly="readonly"
										value="${member.mbNm }">
								</div>
							</div>
							<div class="form-group">
								<label for="mbEmail" class="col-sm-1 control-label">이메일</label>

								<div class="col-sm-11">
									<input type="email" class="form-control" id="mbEmail"
										value="${member.mbEmail }">
								</div>
							</div>
							<div class="form-group form-row">
								<label for="mbPhone" class="col-sm-1 control-label">연락처</label>

								<div class="col-sm-11">
									<c:set var="phone" value="${fn:split(member.mbPhone, '-')}" />
									<c:forEach var="phones" items="${phone }" varStatus="g">
										<c:if test="${g.count == 1 }">
											<select class="form-control select2"
												style="width: 31%; display: inline-block;" id="mbPhone1">
												<c:if test="${phones eq '010' }">
													<option selected="selected" value="010">010</option>
												</c:if>
												<c:if test="${phones ne '010' }">
													<option value="010">010</option>
												</c:if>
												<c:if test="${phones eq '011' }">
													<option selected="selected" value="011">011</option>
												</c:if>
												<c:if test="${phones ne '011' }">
													<option value="011">011</option>
												</c:if>
												<c:if test="${phones eq '019' }">
													<option selected="selected" value="019">019</option>
												</c:if>
												<c:if test="${phones ne '019' }">
													<option value="019">019</option>
												</c:if>
											</select>
										</c:if>
										<c:if test="${g.count == 2 }">
											<span>-</span>
											<input type="text" class="form-control" value="${phones }"
												style="width: 32%; display: inline-block;" id="mbPhone2"
												autocomplete="off" pattern="\d{3,4}" required />
										</c:if>
										<c:if test="${g.count == 3 }">
											<span>-</span>
											<input type="text" class="form-control" value="${phones }"
												style="width: 32%; display: inline-block;" id="mbPhone3"
												autocomplete="off" pattern="\d{3,4}" required />
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</form>
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


	</script>
</body>
</html>