<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value="js/vendor/sockjs-0.3.4.js"/>"></script>
</head>
<body>

<div style="margin-top: 80px;">
    <h4>Chat</h4>
    <div class="chat_room_list" style="position: relative;">
        <div class="alert alert-info _chat_room" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
            <img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
            aaaaaa</div>
        <div class="alert alert-info _chat_room" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
            <img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
            bbbbbb</div>
        <div class="alert alert-info _chat_room" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
            <img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
            ccccc</div>
    </div>
</div>
<div class="content container-fluid bootstrap snippets" >
    <div class="row row-broken">
        <div class="col-sm-3 col-xs-12">
            <div class="col-inside-lg decor-default chat" style="overflow: hidden; outline: none;" tabindex="5000">
                <div class="chat-users">
                    <h6>Online</h6>
                    <div id="chatUserList">
                    <div class="user">
                        <div class="avatar">
                            <img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
                            <div class="status off"></div>
                        </div>
                        <div class="name">User name</div>
                        <div class="mood">User mood</div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-xs-12 chat" style="overflow: scroll; outline: none;" tabindex="5001">
            <div class="col-inside-lg decor-default">
                <div class="chat-body">
                    <h6>Mini Chat</h6>
                    <div id="chatMsgList">
                    <div class="answer left">
                        <div class="avatar">
                            <img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
                            <div class="status offline"></div>
                        </div>
                        <div class="name">Alexander Herthic</div>
                        <div class="text">
                            Lorem ipsum dolor amet, consectetur adipisicing elit Lorem ipsum dolor amet, consectetur adipisicing elit Lorem ipsum dolor amet, consectetur adiping elit
                        </div>
                        <div class="time">5 min ago</div>
                    </div>
                    <div class="answer right">
                        <div class="avatar">
                            <img src="http://bootdey.com/img/Content/avatar/avatar2.png" alt="User name">
                            <div class="status offline"></div>
                        </div>
                        <div class="name">Alexander Herthic</div>
                        <div class="text">
                            Lorem ipsum dolor amet, consectetur adipisicing elit Lorem ipsum dolor amet, consectetur adipisicing elit Lorem ipsum dolor amet, consectetur adiping elit
                        </div>
                        <div class="time">5 min ago</div>
                    </div>
                    </div>
                    <div class="answer-add">
                        <input placeholder="Write a message" id="message">
                        <span class="answer-btn answer-btn-1"></span>
                        <span class="answer-btn answer-btn-2" id="sendBtn"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var userList = {};

    $(document).ready(function () {
        $("#sendBtn").click(function () {
            sendMessage();
        });
    });

    //websocket을 지정한 URL로 연결
    var sock = new SockJS("<c:url value="/echo.do"/>");
    //websocket 서버에서 메시지를 보내면 자동으로 실행된다.
    sock.onmessage = onMessage;
    //websocket 과 연결을 끊고 싶을때 실행하는 메소드
    sock.onclose = onClose;
    sock.onopen = onOpen;


    function sendMessage() {
        var id = "TempId";  //로그인된 사용자 닉네임으로 대체
        //websocket으로 메시지를 보내겠다.
        sock.send($("#message").val());

    }

    //evt 파라미터는 websocket이 보내준 데이터다.
    function onMessage(evt) {  //변수 안에 function자체를 넣음.
        var data = JSON.parse(evt.data);

        if(data.setId != null) {
            console.log(data);
            if(userList && userList[data.setId]) {  //이미 리스트에 있는 사용자는 아무 액션도 하지 않는다.
                return ;
            } else {    //채팅 참석자 추가 액션
                userList[data.setId] = true;
                $("#chatUserList").append(' <div class="user" id="'+data.setId+'"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status off"></div> </div> <div class="name">'+data.setId+'</div> <div class="mood"></div> </div>');
                if(data.isOwner == "false"){    //참석자가 내가 아닌 경우에만
                    getUserOnline();
                }
                return ;
            }
        } else if (data.removeId != null) {
            $("#"+data.removeId).remove();
            return ;
        }

        if(userList[data.id]) {
            $("#chatMsgList").append('<div class="answer left"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.id+'</div><div class="text">'+data.msg+'</div><div class="time">5 min ago</div></div>')
        } else {
            $("#chatMsgList").append('<div class="answer right"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar2.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.id+'</div><div class="text">'+data.msg+'</div><div class="time">5 min ago</div></div>')
        }
        /* sock.close(); */
    }

    function onClose(evt) {
        $("#data").append("연결 끊김");
    }

    function onOpen() {
        getUserOnline();
    }

    function getUserOnline() {
        sock.send("{'setID:'dd'}");
    }

</script>
</html>
