<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<head>
<link rel="stylesheet" href="../../plugins/iCheck/square/blue.css">
<style>
/* API ZIP */
.search-zip {
    display: none;
    border: 1px solid;
    width: 320px;
    height: 400px;
    margin: 5px 0;
    position: relative;
}

/* API ZIP close img */
.search-zip-close-img {
    cursor: pointer;
    position: absolute;
    right: 0px;
    top: -1px;
    z-index: 1;
}
</style>
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="register-logo">
			<a href="/"><b>Admin</b>LTE</a>
		</div>

		<div class="register-box-body">
			<p class="login-box-msg">Register a new membership</p>

			<form id="formRegister" action="register" method="post">
				<div class="form-group has-feedback">
					<input type="text" id="mbId" name="mbId" class="form-control" style="width:60%; display:inline-block;" placeholder="ID" autocomplete="off" required>
					<input type="button" id="btnCheckId" class="btn" style="width:35%; display:inline-block; float:right;" value="중복확인">
					<input type="hidden" id="checkId">
				</div>
				<div class="form-group has-feedback">
					<input type="password" id="mbPw" name="mbPw" class="form-control" placeholder="Password" required>
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" id="reMbPw" class="form-control" placeholder="Retype password" required>
					<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" name="mbNm" class="form-control" placeholder="Name" autocomplete="off" required>
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" name="mbNick" class="form-control" placeholder="Nickname" autocomplete="off" required>
					<span class="glyphicon glyphicon glyphicon-heart form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback form-row">
                    <select class="form-control select2" style="width:31%; display:inline-block;" id="mbPhone1">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="019">019</option>
                    </select>
                    <span>-</span><input type="text" class="form-control" style="width:32%; display:inline-block;" id="mbPhone2" autocomplete="off" pattern="\d{3,4}" required />
                    <span>-</span><input type="text" class="form-control" style="width:32%; display:inline-block;" id="mbPhone3" autocomplete="off" pattern="\d{3,4}" required />
                    <span class="glyphicon glyphicon-phone form-control-feedback"></span>
                    <input type="hidden" id="mbPhone" name="mbPhone">
				</div>
				<div class="form-group has-feedback">
					<input type="email" name="mbEmail" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Email" autocomplete="off" required>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbZip" name="mbZip" class="form-control" style="width:60%; display:inline-block;" readonly="readonly" placeholder="Zip" autocomplete="off" required>
					<input type="button" id="btnSearchZip" class="btn" style="width:35%; display:inline-block; float:right;" value="우편번호 찾기">
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbAddr" name="mbAddr" class="form-control" placeholder="Address" readonly="readonly" autocomplete="off" required>
					<span class="glyphicon glyphicon-home form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbDeaddr" name="mbDeaddr" class="form-control" placeholder="Detail Address" pattern="^[0-9가-힣-()\s]+$" autocomplete="off" required>
					<span class="glyphicon glyphicon-home form-control-feedback"></span>
				</div>
				<div id="iframeSearchZip" class="search-zip">
                	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" class="search-zip-close-img" id="imgSearchZip" alt="접기 버튼">
            	</div>
				
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id="terms"> I agree to the <a
								href="#">terms</a>
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" id="btnRegister" class="btn btn-primary btn-block btn-flat">Register</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<div class="social-auth-links text-center">
				<p>- OR -</p>
				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i
					class="fa fa-facebook"></i> Sign up using Facebook</a> <a href="#"
					class="btn btn-block btn-social btn-google btn-flat"><i
					class="fa fa-google-plus"></i> Sign up using Google+</a>
			</div>

			<a href="login" class="text-center">I already have a membership</a>
		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->

	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	
	<script>

		var result = '${msg}';
	
		if(result == 'FAIL') {
			alert('닉네임 또는 이메일이 중복됩니다.');
			location.replace(self.location);
		}
	
		$(() => {

			$('#btnCheckId').on('click', () => {

				$.ajax({
					   url:"checkId/" + $('#mbId').val(),
					   type:"get",
					   dataType:"text",
					   success:(data) => {

							if(data == "SUCCESS") {
								alert('사용 가능한 아이디입니다.');
								$('#checkId').val($('#mbId').val());
							} else {
								alert('아이디가 중복됩니다.');
								$('#mbId').val('');
							}
						}

				 });

			});
			
			$('#formRegister').on('submit', function(event) {

				$('#mbPhone').val($('#mbPhone1').val() + '-' + $('#mbPhone2').val() + '-' + $('#mbPhone3').val());
				
				if($('#mbPw').val() != $('#reMbPw').val()) {
					event.preventDefault();
					alert('입력한 비밀번호가 다릅니다');
				}

				if(!$('#terms').is(':checked')) {
					event.preventDefault();
					alert('약관에 동의해야 합니다');
				}

				if($('#checkId').val() != $('#mbId').val()) {
					event.preventDefault();
					alert('아이디 중복확인은 필수입니다.');
				}
			});

		});

	</script>
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(() => {
			$('#imgSearchZip').on('click', () => $('#iframeSearchZip').css('display', 'none'));
			$('#btnSearchZip').on('click', () => {
                const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
                new daum.Postcode({
                    oncomplete: data => {

                        let addr = "";

                        if (data.userSelectedType === 'R') {
                            addr = data.roadAddress;
                        } else {
                            addr = data.jibunAddress;
                        }

                        $('#mbZip').val(data.zonecode);
                        $('#mbAddr').val(addr);
                        $('#mbDeaddr').focus();

                        $('#iframeSearchZip').css('display', 'none');

                        document.body.scrollTop = currentScroll;

                    },

                    width: '100%',
                    height: '100%'
                }).embed($('#iframeSearchZip').get(0));

                $('#iframeSearchZip').css('display', 'block');
            });
			
		});
	</script>
	<script src="../../plugins/iCheck/icheck.min.js"></script>
	<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>
</body>
</html>
