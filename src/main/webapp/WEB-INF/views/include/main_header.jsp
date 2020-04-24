<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<header class="main-header">
	<!-- Logo -->
	<a href="/" class="logo"> <span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
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
				<li class="dropdown user user-menu"><a href="/?lang=en">
								<span class="hidden-xs"><spring:message code="message.include.main_header.language.en"/></span>
						</a></li>
				<li class="dropdown user user-menu"><a href="/?lang=ko">
								<span class="hidden-xs"><spring:message code="message.include.main_header.language.ko"/></span>
						</a></li>
				<c:choose>
					<c:when test="${sessionScope.member == null}">
						<!-- 로그인 X -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="/member/register"> <!-- class="dropdown-toggle" data-toggle="dropdown" -->
								<span class="hidden-xs"><spring:message code="message.include.main_header.register"/></span>
						</a>
						</li>
						<li class="dropdown user user-menu"><a href="/member/login">
								<span class="hidden-xs"><spring:message code="message.include.main_header.login"/></span>
						</a></li>
					</c:when>
					<c:otherwise>
						<!-- 일반회원 -->
						<li class="dropdown user user-menu"><a href="#"
							onclick="logout();"> <span class="hidden-xs"><spring:message code="message.include.main_header.logout"/></span>
						</a>
							<form id="formLogout" action="/member/logout" method="post"></form></li>
						<li class="dropdown user user-menu"><a href="/member/modify">
								<span class="hidden-xs"><spring:message code="message.include.main_header.modify"/></span>
						</a></li>
						<li class="dropdown user user-menu"><a href="/basket/list">
								<span class="hidden-xs"><spring:message code="message.include.main_header.basket"/></span>
						</a></li>
						<li class="dropdown user user-menu"><a href="/order/list">
								<span class="hidden-xs"><spring:message code="message.include.main_header.order"/></span>
						</a></li>
					</c:otherwise>
				</c:choose>
				<!-- Control Sidebar Toggle Button -->
				<!-- Messages: style can be found in dropdown.less-->
				<li class="dropdown messages-menu"><a href="#" id="btnMsg"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-envelope-o"></i> <span class="label label-success" id="msgCountNum"></span>
				</a>
					<ul class="dropdown-menu">
						<li class="header" id="msgCount"></li>
						<div id="msgDiv">
						
						
						
						
						</div>
						<li class="footer"><a href="#modalNewMsg" data-toggle="modal"
							data-target="#modalNewMsg"><spring:message code="message.include.main_header.btnMessage"/></a></li>
					</ul></li>
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-wechat"></i></a></li>
			</ul>
		</div>
	</nav>
</header>
<!-- New Message modal -->
<div class="modal fade" id="modalNewMsg">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close btnClose" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="message.include.main_header.sendTitle"/></h4><br>
				<input type="text" id="msgTarget" class="form-control" name="msgTarget" placeholder="id...">
			</div>
			<div class="modal-body">
				<input type="text" id="msgContent" class="form-control" name="msgContent" placeholder="content...">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left btnClose"
					data-dismiss="modal"><spring:message code="message.include.main_header.btnClose"/></button>
				<button type="button" id="btnSend" class="btn btn-primary"><spring:message code="message.include.main_header.btnSendMessage"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- Read Message modal -->
<div class="modal fade" id="modalReadMsg">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close btnClose" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="message.include.main_header.readTitle"/></h4><br>
				<input type="text" id="readMsgSender" class="form-control" readonly="readonly">
			</div>
			<div class="modal-body">
				<input type="text" id="readMsgContent" class="form-control" readonly="readonly">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left btnClose"
					data-dismiss="modal"><spring:message code="message.include.main_header.btnClose"/></button>
				<button type="button" id="btnReply" class="btn btn-primary"><spring:message code="message.include.main_header.btnReplyMessage"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
	<script src="/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<script id="msgTemplate" type="text/x-handlebars-template">
		{{#each .}}
			<li class="msgLi" data-mno={{msgNo}} onclick="clickMsg(this)" data-toggle="modal"
							data-target="#modalReadMsg">
				<ul class="menu">
					<li><a href="#">
						<div class="pull-left">
							<img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
						</div>
						<h4>{{msgSender}}<small><i class="fa fa-clock-o"></i>{{prettifyDate msgSddt}}</small></h4>
						<p>{{msgContent}}</p>
					</a></li>
				</ul>
			</li>
        {{/each}}
	</script>
	<script>

			var clickMsg = function(msg) {

				$.getJSON('/msg/' + msg.getAttribute('data-mno'), function(data) {

					$('#readMsgSender').val(data.msgSender);
					$('#readMsgContent').val(data.msgContent);

				});
				
			}

			var printMsgData = function(msgArr, target, templateObject) {

				let template = Handlebars.compile(templateObject.html());

				Handlebars.registerHelper("prettifyDate", function(timeValue) {
					var dateObj = new Date(timeValue);
					var year = dateObj.getFullYear();
					var month = dateObj.getMonth() + 1;
					var date = dateObj.getDate();
					return year + "/" + month + "/" + date;
				});
					

				let html = template(msgArr);
				$('.msgLi').remove();
				target.after(html);
			}

			function logout() {
				$('#formLogout').submit();
			}
	
			$(() => {

				$.getJSON('/msg/list/' + '${member.mbId}', function(data) {
					printMsgData(data, $("#msgDiv"), $('#msgTemplate'));

					$("#msgCount").html('You have ' + data.length + ' messages');
					$('#msgCountNum').html(data.length);
				});

				$('#btnReply').on('click', function() {

					$('#modalReadMsg').modal('hide');
					$("#msgTarget").val($('#readMsgSender').val());
					$('#modalNewMsg').modal('show');


				});
				
				
				$('.btnClose').on('click', () => {
					$('#msgTarget').val('');
					$('#msgContent').val('');

					$.getJSON('/msg/list/' + '${member.mbId}', function(data) {
						printMsgData(data, $("#msgDiv"), $('#msgTemplate'));

						$("#msgCount").html('You have ' + data.length + ' messages');
						$('#msgCountNum').html(data.length);
					});
				});
	
				$('#btnSend').on('click', () => {
	
					$.ajax({
						type : 'post',
						url : '/msg/send',
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "post"
						},
						data : JSON.stringify({msgTarget : $('#msgTarget').val(),
											   msgSender : '${member.mbId}',
											   msgContent : $('#msgContent').val()}),
						dataType : 'text',
						success : function(data) {
							if (data == 'SUCCESS')
								alert("메세지가 전송되었습니다.");
							else if(data == 'FAIL')
								alert('해당하는 사용자가 없습니다.');
							$("#modalNewMsg").modal('hide');
							$("#msgTarget").val('');
							$("#msgContent").val('');
						}
					});
	
				});
	
			});
	</script>