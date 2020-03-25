<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<body class="hold-transition lockscreen">
	<!-- Automatic element centering -->
	<div class="lockscreen-wrapper">
		<div class="lockscreen-logo">
			<a href="/"><b>Admin</b>LTE</a>
		</div>
		<!-- User name -->
		<div class="lockscreen-name">${temp }</div>

		<!-- START LOCK SCREEN ITEM -->
		<div class="lockscreen-item">
			<!-- lockscreen image -->
			<div class="lockscreen-image">
				<img src="/dist/img/face-128x128.jpg" alt="User Image">
			</div>
			<!-- /.lockscreen-image -->

			<!-- lockscreen credentials (contains the form) -->
			<form class="lockscreen-credentials" method="post" action="authkey">
				<div class="input-group">
					<input type="password" name="mbAuth" class="form-control" placeholder="Auth key">
					<input type="hidden" name="mbId" value="${temp }">
					<div class="input-group-btn">
						<button type="submit" id="btnAuth" class="btn">
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
	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	<script>

		var result = '${msg}';
	
		if(result == 'FAIL') {
			alert('인증키가 다릅니다');
			location.replace(self.location);
		}
		
	</script>
</body>
</html>
