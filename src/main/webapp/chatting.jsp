<%@ page import="kr.corearoad.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/vendor/sockjs-0.3.4.js"/>
</head>
<body>

<div style="margin-top: 80px;">
    <h4 class="title">Chat</h4>
    <div class="chat_room_list">
        <div class="list-group chat_room_instance">
        </div>
    </div>
</div>
<div class="content container-fluid bootstrap snippets chat_messenger_main" style="display: none;">
    <div class="row row-broken">
        <div class="col-sm-3 col-xs-12">
            <div class="col-inside-lg decor-default chat" style="overflow: hidden; outline: none;" tabindex="5000">
                <div class="chat-users">
                    <h6>Jingermans</h6>
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
</html>
