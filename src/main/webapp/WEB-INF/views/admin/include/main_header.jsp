<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header class="main-header">
	<!-- Logo -->
	<a href="/admin/home" class="logo"> <span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"> <b>Admin</b>LTE
	</span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account Menu -->
				<li class="dropdown user user-menu"><a>
						<span class="hidden-xs">최근 접속시간 : 
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${admin.admCondt}" /></span>
				</a></li>
				<li class="dropdown user user-menu"><a href="#" onclick="logout();">
						<span class="hidden-xs">로그아웃</span></a>
				<form id="formLogout" action="/admin/logout" method="post"></form>
				</li>
				<li class="dropdown user user-menu"><a href="/admin/order/list">
						<span class="hidden-xs">주문조회</span>
				</a></li>

				<!-- Control Sidebar Toggle Button -->
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li>
			</ul>
		</div>
	</nav>
</header>
<script>
		function logout() {
			$('#formLogout').submit();
		}
</script>