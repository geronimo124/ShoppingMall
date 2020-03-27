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
								<th>번호</th>
								<th>주문일자</th>
								<th>상품명</th>
								<th>결제금액</th>
								<th>주문상세</th>
								<th>배송현황</th>
							</tr>

							<c:forEach items="${orderList}" var="orderVO">

								<tr>
									<td class="ordNo">${orderVO.ordNo }</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${orderVO.ordDt}" /></td>
									<td>${orderVO.pdNm }</td>
									<td>${orderVO.ordPrice }</td>
									<td><a href="/order/detail?ordNo=${orderVO.ordNo }"><button
												type="button" class="btn">보기</button></a></td>
									<td><c:choose>
											<c:when test="${orderVO.ordStatus eq 'R' }">
												배송 준비중
											</c:when>
											<c:when test="${orderVO.ordStatus eq 'D' }">
												배송중
											</c:when>
											<c:when test="${orderVO.ordStatus eq 'S' }">
												배송완료
											</c:when>
										</c:choose></td>
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


		});

	</script>
</body>
</html>