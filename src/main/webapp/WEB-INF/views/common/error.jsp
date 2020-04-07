<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/include/left.jsp"%>

		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content-header">
				<h1>Error Page</h1>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="error-page">
					<h1 class="text-red text-center">ERROR</h1>
				</div>
				<div class="error-page">
					<p>
						${exception.message }
					</p>
				</div>
				<!-- /.error-page -->

			</section>
		</div>
	</div>
	<!-- /.content -->
	<%@include file="/WEB-INF/views/include/footer.jsp"%>
	<%@include file="/WEB-INF/views/include/aside.jsp"%>
	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>

</body>
</html>