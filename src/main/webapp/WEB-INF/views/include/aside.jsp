<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<aside class="control-sidebar control-sidebar-dark">
	<!-- Create the tabs -->
	<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
		<li class="active"><a href="#chat-tab"
			data-toggle="tab"><i class="fa fa-wechat"></i></a></li>
			<!-- 
		<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
				class="fa fa-gears"></i></a></li>
			 -->
	</ul>
	<!-- Tab panes -->
	<h3 class="text-center"><spring:message code="message.include.aside.title"/></h3>
	<div class="tab-content" id="chat-content" style="overflow: auto; max-height:100%;">
		<!-- Home tab content -->
		<div class="tab-pane active" id="chat-tab">
			<div style="height:700px;">
				<ul class="control-sidebar-menu" id="chatdata">
					<!-- chatting data -->
				</ul>
			</div>
		</div>
		<!-- /.tab-pane -->
	</div>
	<hr>
	<input type="text" class="form-control" id="message" style="display:inline-block; width:160px;">
	<button type="button" class="btn btn-sm" id="btnSend" style="display:inline-block; float:right;">전송</button>
			
</aside>
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
<div class="control-sidebar-bg"></div>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script>

	var sock = new SockJS("<c:url value='/echo'/>");

	sock.onmessage = onMessage;

	$(function() {
		$('#btnSend').click(function() {
			sendMessage();
		});

		$('#message').keypress(function(event){
		     if ( event.which == 13 ) {
		         $('#btnSend').click();
		         return false;
		     }
		});
	});

	function sendMessage() {
		sock.send($('#message').val());
	}

	
	function onMessage(evt) {

		var data = evt.data;
		var sessionid = null;
		var message = null;

		var strArray = data.split('|');

		var currentuser_session = '${member.mbNick}';

		sessionid = strArray[0];
		message = strArray[1];

		if(sessionid == currentuser_session) {

			var printHTML = "<li>";
			printHTML += "<p class='text-aqua'>";
			printHTML += "[" + sessionid + "] -> " + message;
			printHTML += "</p>";
			printHTML += "</li>";

			$('#chatdata').append(printHTML);
			$("#chat-content").scrollTop($("#chat-content")[0].scrollHeight);
			$('#message').val('');
			
		} else {

			var printHTML = "<li>";
			printHTML += "<p class='text-white'>";
			printHTML += "[" + sessionid + "] -> " + message;
			printHTML += "</p>";
			printHTML += "</li>";

			$('#chatdata').append(printHTML);
			$("#chat-content").scrollTop($("#chat-content")[0].scrollHeight);
			$('#message').val('');

		}
	
	}
</script>