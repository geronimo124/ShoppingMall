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

				<div class="row">
					<section class="col-md-8 connectedSortable">
						<!-- Custom tabs (Charts with tabs)-->
						<div class="nav-tabs-custom">
							<!-- Tabs within a box -->
							<ul class="nav nav-tabs pull-right">
								<li class="pull-left header"><i class="fa fa-inbox"></i>
									Sales</li>
							</ul>
							<div class="tab-content no-padding">
								<!-- Morris chart - Sales -->
								<div class="chart tab-pane active" id="curve_chart"
									style="position: relative; height: 450px;"></div>
							</div>
						</div>
					</section>

					<div class="col-md-4">
						<!-- /.info-box -->
						<div class="info-box bg-white">
							<span class="info-box-icon"><i
								class="ion ion-tshirt-outline"></i></span>

							<div class="info-box-content">
							
								<span class="info-box-text">Best Sellers</span> <span
									class="info-box-number" id="ctgyPtnm0"></span>
								<div class="progress">
									<div class="progress-bar" style="width: 20%"></div>
								</div>
								<span class="progress-description" id="pdNm0"></span>

							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
						<div class="info-box bg-white">
							<span class="info-box-icon"><i
								class="ion ion-tshirt-outline"></i></span>

							<div class="info-box-content">
							
								<span class="info-box-text">Best Sellers</span> <span
									class="info-box-number" id="ctgyPtnm1"></span>
								<div class="progress">
									<div class="progress-bar" style="width: 20%"></div>
								</div>
								<span class="progress-description" id="pdNm1"></span>

							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
						<div class="info-box bg-white">
							<span class="info-box-icon"><i
								class="ion ion-tshirt-outline"></i></span>

							<div class="info-box-content">
							
								<span class="info-box-text">Best Sellers</span> <span
									class="info-box-number" id="ctgyPtnm2"></span>
								<div class="progress">
									<div class="progress-bar" style="width: 20%"></div>
								</div>
								<span class="progress-description" id="pdNm2"></span>

							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
						<div class="info-box bg-white">
							<span class="info-box-icon"><i
								class="ion ion-tshirt-outline"></i></span>

							<div class="info-box-content">
							
								<span class="info-box-text">Best Sellers</span> <span
									class="info-box-number" id="ctgyPtnm3"></span>
								<div class="progress">
									<div class="progress-bar" style="width: 20%"></div>
								</div>
								<span class="progress-description" id="pdNm3"></span>

							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
						<div class="info-box bg-white">
							<span class="info-box-icon"><i
								class="ion ion-tshirt-outline"></i></span>

							<div class="info-box-content">
							
								<span class="info-box-text">Best Sellers</span> <span
									class="info-box-number" id="ctgyPtnm4"></span>
								<div class="progress">
									<div class="progress-bar" style="width: 20%"></div>
								</div>
								<span class="progress-description" id="pdNm4"></span>

							</div>
							<!-- /.info-box-content -->
						</div>
					</div>
					</div>
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
			
			$.get( "/admin/stat/getBestSellers/", function(data) {
				let i;
				for(i = 0; i < data.length; i++) {
					$('#ctgyPtnm' + i).text(data[i]['ctgyPtnm']);
					$('#pdNm' + i).text(data[i]['pdNm']);
				}
			});
			
			$.get( "/admin/stat/getSalesGraph", function(data) {

				let arr = [0, 0, 0, 0, 0, 0, 0];
				let i;
				for(i = 0; i < data.length; i++)
					arr[data[i]['DIFF']]= data[i]['SUM'];

				let graphData = [[], [], [], [], [], [], [], []];
				let date = new Date();
				date.setDate(date.getDate() - 6);

				graphData[0][0] = 'Date';
				graphData[0][1] = 'Sales';
				for(i = 0; i < 7; i++) {
					graphData[i+1][0] = (date.getMonth() + 1) + ' / ' + date.getDate();
					graphData[i+1][1] = arr[6-i];
					date.setDate(date.getDate() + 1);
				}
				
				google.charts.load('current', {'packages':['corechart']});
				google.charts.setOnLoadCallback(drawChart);

				function drawChart() {
					var data = google.visualization.arrayToDataTable(graphData);
			        var options = {
					        title: 'Sales per date',
					        curveType: 'function',
					        legend: { position: 'bottom' }
			        };

			        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

			        chart.draw(data, options);
				}
				
			});
		});
	
	</script>

	<script src="https://www.gstatic.com/charts/loader.js"></script>

</body>

</html>