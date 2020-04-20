<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<!-- 

				Notifications Menu
				<li class="dropdown notifications-menu">
					Menu toggle button
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span class="label label-warning">10</span>
					</a>
					<ul class="dropdown-menu">
						<li class="header">You have 10 notifications</li>
						<li>
							Inner Menu: contains the notifications
							<ul class="menu">
								<li>
									start notification
									<a href="#"> <i class="fa fa-users text-aqua"></i> 5 new members joined today
									</a>
								</li>
								end notification
							</ul>
						</li>
						<li class="footer">
							<a href="#">View all</a>
						</li>
					</ul>
				</li>
				Tasks Menu
				<li class="dropdown tasks-menu">
					Menu Toggle Button
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span class="label label-danger">9</span>
					</a>
					<ul class="dropdown-menu">
						<li class="header">You have 9 tasks</li>
						<li>
							Inner menu: contains the tasks
							<ul class="menu">
								<li>
									Task item
									<a href="#"> Task title and progress text
										<h3>
											Design some buttons <small class="pull-right">20%</small>
										</h3> The progress bar
										<div class="progress xs">
											Change the css width attribute to simulate progress
											<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
												<span class="sr-only">20% Complete</span>
											</div>
										</div>
									</a>
								</li>
								end task item
							</ul>
						</li>
						<li class="footer">
							<a href="#">View all tasks</a>
						</li>
					</ul>
				</li> -->
				<!-- User Account Menu -->
				<c:choose>
					<c:when test="${sessionScope.member == null}">
						<!-- 로그인 X -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="/member/register"> <!-- class="dropdown-toggle" data-toggle="dropdown" -->
								<span class="hidden-xs">회원가입</span>
						</a>
						</li>
						<li class="dropdown user user-menu"><a href="/member/login">
								<span class="hidden-xs">로그인</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<!-- 일반회원 -->
						<li class="dropdown user user-menu"><a href="#"
							onclick="logout();"> <span class="hidden-xs">로그아웃</span>
						</a>
							<form id="formLogout" action="/member/logout" method="post"></form></li>
						<li class="dropdown user user-menu"><a href="/member/modify">
								<span class="hidden-xs">회원 정보 수정</span>
						</a></li>
						<li class="dropdown user user-menu"><a href="/basket/list">
								<span class="hidden-xs">장바구니</span>
						</a></li>
						<li class="dropdown user user-menu"><a href="/order/list">
								<span class="hidden-xs">주문조회</span>
						</a></li>
					</c:otherwise>
				</c:choose>

				<!-- <ul class="dropdown-menu">
						The user image in the menu
						<li class="user-header">
							<p>
								Alexander Pierce - Web Developer <small>Member since Nov. 2012</small>
							</p>
						</li>
						<li class="user-header">
							<p>
								Alexander Pierce - Web Developer <small>Member since Nov. 2012</small>
							</p>
						</li>
						
						Menu Body
						<li class="user-body">
							<div class="row">
								<div class="col-xs-4 text-center">
									<a href="#">Followers</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">Sales</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">Friends</a>
								</div>
							</div>
							/.row
						</li>
						Menu Footer
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">Profile</a>
							</div>
							<div class="pull-right">
								<a href="#" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul> -->
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
							data-target="#modalNewMsg">Send a Message</a></li>
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
				<h4 class="modal-title">Send a message</h4>
				To : <input type="text" id="msgTarget" class="form-control" name="msgTarget" placeholder="id...">
			</div>
			<div class="modal-body">
				<input type="text" id="msgContent" class="form-control" name="msgContent" placeholder="content...">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left btnClose"
					data-dismiss="modal">Close</button>
				<button type="button" id="btnSend" class="btn btn-primary">Send</button>
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
				<h4 class="modal-title">Read a message</h4>
				From : <input type="text" id="readMsgSender" class="form-control" readonly="readonly">
			</div>
			<div class="modal-body">
				<input type="text" id="readMsgContent" class="form-control" readonly="readonly">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left btnClose"
					data-dismiss="modal">Close</button>
				<button type="button" id="btnReply" class="btn btn-primary">Reply</button>
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

			/*
			$.ajax({
				type : 'post',
				url : 'https://openapi.naver.com/v1/datalab/shopping/categories',
				headers : {
					"Access-Control-Allow-Origin" : "*",
					"X-Naver-Client-Id" : "rD9HMUHFlI40eg948HHo",
					"X-Naver-Client-Secret" : "wMC5hT3cyq",
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "post"
				},
				contentType : 'application/x-www-form-urlencoded',
				data : {
					"startDate": "2017-08-01",
					"endDate": "2017-09-30",
					"timeUnit": "month",
					"category": [
					      {"name": "패션의류", "param": [ "50000000"]},
					      {"name": "화장품/미용", "param": [ "50000002"]}
					],
					"device": "pc",
					"gender": "f",
					"ages": [ "20",  "30"]
				},
				dataType : 'text',
				success : function(data) {
					alert(data);
				}
			});
			*/

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