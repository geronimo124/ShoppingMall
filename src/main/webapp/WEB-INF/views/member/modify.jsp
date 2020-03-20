<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
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
			<p class="login-box-msg">Modify member info</p>

			<form id="formModify" action="modify" method="post">
				<div class="form-group has-feedback">
					<input type="text" name="mbId" value="${member.mbId }" class="form-control" readonly="readonly">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
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
					<input type="text" name="mbNm" value="${member.mbNm }" class="form-control" placeholder="Name" autocomplete="off" required>
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" name="mbNick" value="${member.mbNick }" class="form-control" placeholder="Nickname" autocomplete="off" required>
					<span class="glyphicon glyphicon glyphicon-heart form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback form-row">
					<c:set var="phone" value="${fn:split(member.mbPhone, '-')}" />
                    <c:forEach var="phones" items="${phone }" varStatus="g">
                    <c:if test="${g.count == 1 }">
                    <select class="form-control select2" style="width:31%; display:inline-block;" id="mbPhone1">
                        <c:if test="${phones eq '010' }"><option selected="selected" value="010">010</option></c:if>
                        <c:if test="${phones ne '010' }"><option value="010">010</option></c:if>
                        <c:if test="${phones eq '011' }"><option selected="selected" value="011">011</option></c:if>
                        <c:if test="${phones ne '011' }"><option value="011">011</option></c:if>
                        <c:if test="${phones eq '019' }"><option selected="selected" value="019">019</option></c:if>
                        <c:if test="${phones ne '019' }"><option value="019">019</option></c:if>
                    </select>
                    </c:if>
                    <c:if test="${g.count == 2 }">
                    <span>-</span><input type="text" class="form-control" value="${phones }" style="width:32%; display:inline-block;" id="mbPhone2" autocomplete="off" pattern="\d{3,4}" required />
                    </c:if>
                    <c:if test="${g.count == 3 }">
                    <span>-</span><input type="text" class="form-control" value="${phones }" style="width:32%; display:inline-block;" id="mbPhone3" autocomplete="off" pattern="\d{3,4}" required />
                    </c:if>
                    </c:forEach>
                    <span class="glyphicon glyphicon-phone form-control-feedback"></span>
                    <input type="hidden" id="mbPhone" name="mbPhone">
				</div>
				<div class="form-group has-feedback">
					<input type="email" name="mbEmail" class="form-control" value="${member.mbEmail }" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Email" autocomplete="off" required>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbZip" value="${member.mbZip }" name="mbZip" class="form-control" style="width:60%; display:inline-block;" readonly="readonly" placeholder="Zip" autocomplete="off" required>
					<input type="button" id="btnSearchZip" class="btn" style="width:35%; display:inline-block; float:right;" value="우편번호 찾기">
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbAddr" value="${member.mbAddr }" name="mbAddr" class="form-control" placeholder="Address" readonly="readonly" autocomplete="off" required>
					<span class="glyphicon glyphicon-home form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" id="mbDeaddr" value="${member.mbDeaddr }" name="mbDeaddr" class="form-control" placeholder="Detail Address" pattern="^[0-9가-힣-()\s]+$" autocomplete="off" required>
					<span class="glyphicon glyphicon-home form-control-feedback"></span>
				</div>
				<div id="iframeSearchZip" class="search-zip">
                	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" class="search-zip-close-img" id="imgSearchZip" alt="접기 버튼">
            	</div>
				
				<button type="submit" id="btnModify" class="btn btn-primary btn-block btn-flat text-center">Modify</button>
					<!-- /.col -->
			</form>
		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->

	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>
	
	<script>

		$(() => {

			$('#formModify').on('submit', function(event) {

				$('#mbPhone').val($('#mbPhone1').val() + '-' + $('#mbPhone2').val() + '-' + $('#mbPhone3').val());
				
				if($('#mbPw').val() != $('#reMbPw').val()) {
					event.preventDefault();
					alert('입력한 비밀번호가 다릅니다');
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
