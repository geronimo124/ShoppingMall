<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
<link rel="stylesheet" href="/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="/"><b>Admin</b>LTE</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your session</p>

			<form action="login" method="post">
				<div class="form-group has-feedback">
					<input type="text" id="mbId" class="form-control" name="id" placeholder="Id">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" placeholder="Password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id="checkRemId"> Remember Me
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Sign
							In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<div class="social-auth-links text-center">
				<p>- OR -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i
					class="fa fa-facebook"></i> Sign in using Facebook</a> <a href="#"
					class="btn btn-block btn-social btn-google btn-flat"><i
					class="fa fa-google-plus"></i> Sign in using Google+</a>
			</div>
			<!-- /.social-auth-links -->

			<a href="#">I forgot my password</a><br> <a href="register"
				class="text-center">Register a new membership</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	<script src="/plugins/iCheck/icheck.min.js"></script>
	<script>
	  $(function () {
   	 $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
	</script>
	<script>

	var result = '${msg}';

	if(result == 'FAIL') {
		alert('아이디 또는 비밀번호가 다릅니다');
		location.replace(self.location);
	}

	if(result == 'DUPLICATE') {
		alert('이미 접속중인 아이디입니다.');
		location.replace(self.location);
	}
	</script>
	
	<script>
		function setCookie(cookieName, value, exdays){
		    var exdate = new Date();
		    exdate.setDate(exdate.getDate() + exdays);
		    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
		    document.cookie = cookieName + "=" + cookieValue;
		    alert('생성');
		}
		 
		function deleteCookie(cookieName){
		    var expireDate = new Date();
		    expireDate.setDate(expireDate.getDate() - 1);
		    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
		    alert('삭제');
		}
		 
		function getCookie(cookieName) {
		    cookieName = cookieName + '=';
		    var cookieData = document.cookie;
		    var start = cookieData.indexOf(cookieName);
		    var cookieValue = '';
		    if(start != -1){
		        start += cookieName.length;
		        var end = cookieData.indexOf(';', start);
		        if(end == -1)end = cookieData.length;
		        cookieValue = cookieData.substring(start, end);
		    }
		    return unescape(cookieValue);
		}
	
		$(document).ready(function() {
	
			// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
			var key = getCookie("key");
		    $("#mbId").val(key); 
		     
		    if($("#mbId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
		        $("#checkRemId").iCheck('check'); // ID 저장하기를 체크 상태로 두기.
		    }
		     
		    $("#checkRemId").on('ifChanged', function(){ // 체크박스에 변화가 있다면,
		        if($("#checkRemId").is(":checked")){ // ID 저장하기 체크했을 때,
		            setCookie("key", $("#mbId").val(), 7); // 7일 동안 쿠키 보관
		        }else{ // ID 저장하기 체크 해제 시,
		            deleteCookie("key");
		        }
		    });
		     
		    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		    $("#mbId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
		        if($("#checkRemId").is(":checked")){ // ID 저장하기를 체크한 상태라면,
		            setCookie("key", $("#mbId").val(), 7); // 7일 동안 쿠키 보관
		        }
		    });
		});

	</script>
</body>
</html>
