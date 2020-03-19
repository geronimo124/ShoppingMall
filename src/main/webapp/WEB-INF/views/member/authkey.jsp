<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
<body class="hold-transition lockscreen">
	<!-- Automatic element centering -->
	<div class="lockscreen-wrapper">
		<div class="lockscreen-logo">
			<a href="/"><b>Admin</b>LTE</a>
		</div>
		<!-- User name -->
		<div class="lockscreen-name">${temp.mbNick }</div>

		<!-- START LOCK SCREEN ITEM -->
		<div class="lockscreen-item">
			<!-- lockscreen image -->
			<div class="lockscreen-image">
				<img src="/dist/img/face-128x128.jpg" alt="User Image">
			</div>
			<!-- /.lockscreen-image -->

			<!-- lockscreen credentials (contains the form) -->
			<form class="lockscreen-credentials">
				<div class="input-group">
					<input type="password" class="form-control" placeholder="Auth key">

					<div class="input-group-btn">
						<button type="button" class="btn">
							<i class="fa fa-arrow-right text-muted"></i>
						</button>
					</div>
				</div>
			</form>
			<!-- /.lockscreen credentials -->

		</div>
		<!-- /.lockscreen-item -->
		<div class="help-block text-center">Enter your auth key to
			continue your register membership</div>
		<div class="text-center">
			<a href="login">Or sign in as a different user</a>
		</div>
		<div class="lockscreen-footer text-center">
			Copyright &copy; 2020 <b><a href="#" class="text-black">Company</a></b><br>
			All rights reserved
		</div>
	</div>
	<!-- /.center -->
	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>
</body>
</html>
