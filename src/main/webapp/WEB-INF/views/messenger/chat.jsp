<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="../include/chatheader.jsp" %>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
<%@include file="../include/chatApp.jsp" %>

<div class="panel panel-default">
	<div class="panel panel-heading" style="text-align: center; position:fixed; top:0; left:0; z-index:10; width:100%; padding : 3px" >${friend_name}
		<a href="/messenger"><i class="fa fa-chevron-left fa-5x" style="float: left; font-size:15px; margin-top: 10px;" >&nbsp뒤로가기</i></a>
			<ul class="nav navbar-top-links navbar-right" style="float:right;">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<i class = "fa fa-ellipsis-h fa-fw" style="font-size:18px"></i>
				</a>
			</li>
		</ul>
	</div>
</div>
	<div id="msgpanel" style="margin-top: 45px; height: 100%;">
		<div class="container" style="margin-bottom: 40px">
			<ul id="messageWindow" class="chat-box"></ul>
		</div>
		<div style="position:fixed; bottom:0;">
			<div class="input-group">
						<input type="text" id="msg" name="msg" class="form-control input-sm" style="height: 35px" onkeyup="if(event.keyCode == 13){sendMessage();}" >
						<span class="input-group-btn">
							<button class="btn btn-warning btn-sm" style="height: 35px" id="send" >전송</button>
						</span>
			</div>
		</div>	
	</div>





<%@include file="../include/footer.jsp" %>
