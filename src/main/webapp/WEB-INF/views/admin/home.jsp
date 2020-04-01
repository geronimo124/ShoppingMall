<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

			<!-- Main content -->
			<section class="content container-fluid">
				<div class="row">
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-aqua">
							<div class="inner">
								<h3 id="newOrder">0</h3>

								<p>New Orders</p>
							</div>
							<div class="icon">
								<i class="ion ion-bag"></i>
							</div>
							<a href="/admin/order/list" class="small-box-footer">More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3>
									53<sup style="font-size: 20px">%</sup>
								</h3>

								<p>Bounce Rate</p>
							</div>
							<div class="icon">
								<i class="ion ion-stats-bars"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-yellow">
							<div class="inner">
								<h3 id="newMember">0</h3>

								<p>User Registrations</p>
							</div>
							<div class="icon">
								<i class="ion ion-person-add"></i>
							</div>
							<a href="/admin/member/list" class="small-box-footer">More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner">
								<h3>65</h3>

								<p>Unique Visitors</p>
							</div>
							<div class="icon">
								<i class="ion ion-pie-graph"></i>
							</div>
							<a href="#" class="small-box-footer">More info <i
								class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
					<!-- ./col -->
				</div>


				<section class="col-lg-7 connectedSortable">
					<!-- Custom tabs (Charts with tabs)-->
					<div class="nav-tabs-custom">
						<!-- Tabs within a box -->
						<ul class="nav nav-tabs pull-right">
							<li class="active"><a href="#revenue-chart"
								data-toggle="tab">Area</a></li>
							<li><a href="#sales-chart" data-toggle="tab">Donut</a></li>
							<li class="pull-left header"><i class="fa fa-inbox"></i>
								Sales</li>
						</ul>
						<div class="tab-content no-padding">
							<!-- Morris chart - Sales -->
							<div class="chart tab-pane active" id="revenue-chart"
								style="position: relative; height: 300px;"></div>
							<div class="chart tab-pane" id="sales-chart"
								style="position: relative; height: 300px;"></div>
						</div>
					</div>
				</section>
					<!-- /.nav-tabs-custom -->
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
		<%@include file="/WEB-INF/views/admin/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@include file="/WEB-INF/views/admin/include/aside.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>
	<script>
		var result = '${msg}';

		if(result == 'SUCCESS') {
			alert('${sessionScope.admin}');
			location.replace(self.location);
		}
	</script>
	<script>
		function logout() {
			$('#formLogout').submit();
		}
	</script>

	<script>

		$(() => {

			$.get( "/admin/stat/getCountNewOrders/" + '${admin.admId}', function(data) {
				  $( "#newOrder" ).html( data );
			});
			
			$.get( "/admin/stat/getCountNewMembers/" + '${admin.admId}', function(data) {
				  $( "#newMember" ).html( data );
			});
		});
	
	</script>
</body>

</html>