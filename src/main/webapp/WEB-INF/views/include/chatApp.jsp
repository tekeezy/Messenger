<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var stompClient = null;
var email = '${sessionScope.email}';
var name = '${sessionScope.name}';


$(document).ready(function() {
	<c:forEach items="${messages}" var="vo">
	if ("${vo.email}" == email) {
		showMessageRight("${vo.content}");
	} else {
		showMessageLeft("${vo.content}");
	}
	</c:forEach>
	connect();
    scroll();
});

function connect() {
    var socket = new SockJS('/webSocketHndlr');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/${chatNo}', function (message) {
        		var messageBody = JSON.parse(message.body);
        		if(messageBody.email == '${sessionScope.email}') {
            		showMessageRight(messageBody.content);
        		}
        		else {
        			showMessageLeft(messageBody.content);
        		}
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    //setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
	if($("#msg").val() == "") {
		msg.focus();
		return 0;
	}
    stompClient.send("/app/${chatNo}", {}, JSON.stringify({'name': 'test', 
    													   'content' : $("#msg").val(),
    													   'chatNo' : '${chatNo}',
    													   'email' : email}));
    var msg = document.getElementById("msg");
	msg.value = "";
	msg.focus();
    
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendMessage(); });
});

function showMessageLeft(msg) {

	var messageWindow = document.getElementById("messageWindow");
	var Box = document.createElement("div");
	var thumb = document.createElement("img");
	var contents = document.createElement("div");

	var name = document.createElement("div");
	var msgBox = document.createElement("li");
	//var detail = document.createElement("div");

	contents.appendChild(msgBox);
	thumb.src = "${pageContext.request.contextPath}/resources/upload/profile/${friend.profile}";
	thumb.className = "img-circle thumb";
	thumb.setAttribute("onclick", "thumb()");
	Box.appendChild(thumb);

	name.innerHTML = "${friend.name}";
	contents.insertBefore(name, contents.childNodes[0]);

	msgBox.className = "odd";

	contents.className = "contents";
	msgBox.innerHTML = msg;

	Box.appendChild(contents);
	messageWindow.appendChild(Box);
	scroll();
}

function showMessageRight(msg) {

	var messageWindow = document.getElementById("messageWindow");
	var msgBox = document.createElement("li");
	var Box = document.createElement("div");

	Box.className = "BoxRight";

		msgBox.className = "even";
	Box.appendChild(msgBox);
	msgBox.innerHTML = msg;

	messageWindow.appendChild(Box);
	scroll();
}

function scroll() {
	var scrollheight = document.body.scrollHeight;
	scrollheight = scrollheight + 20;
	document.body.scrollTop = scrollheight;
}
</script>