define(['handlebars'],function (Handlebars) {

    var sRoomId = '',
        chatList = {},
        userList = {},
        sock = null,
        loginUserId = '';







    function _init() {
        $.ajax({
            method: 'GET',
            url: '/getAllChatRoomByUserId.do',
            data: {}
        }).done(function (oData) {
            var result = $.parseJSON(oData);
            loginUserId = result.loginUserId;
            _makeChatRoomHtml(result);
            _attachEvent();
        });
    }

    function _makeChatRoomHtml(result) {
        $.each(result.data, function (nIndex, data) {
            var template = Handlebars.compile($("#chat_list_html").html());
            console.log($("#chat_list_html").html())
            /*html.push('<a href="#chat/'+data.roomId+'" class="list-group-item" data-value="'+data.roomId+'">');
            html.push('<img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">');
            html.push('<span class="roomName chat_font">'+data.roomName+'</span>');
            html.push('<span class="roomUserCount chat_font">('+data.roomUserCount+')</span>');
            html.push('<button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>');
            html.push('</a>');*/
            chatList[data.roomId] = data;
            $('#chat_room_list').append(template(data));
        });
    }
    
    function _attachEvent() {
        $('.card-link').on('click', function (oEvent) {
            var target = oEvent.target;
            var roomId = $(target).attr('data-index');

            $('.title').html(chatList[roomId].roomName);
            $('.chat_list_div').hide();
            $('.chat_messenger_main').show();
            _setChatMessengerMainView(roomId);
        });
    }
    
    function _setChatMessengerMainView(roomId) {
        if(roomId) {
            sRoomId = roomId;
        }

        $.each(chatList[roomId].roomUserList, function (nIndex, data) {
            $("#chatUserList").append(' <div class="user" id="'+data+'"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status off"></div> </div> <div class="name">'+data+'</div> <div class="mood"></div> </div>');
        });

        $.ajax({
            method: 'GET',
            url: '/getAllMessengersByRoomId.do',
            data: {roomId: roomId}
        }).done(function (oData) {
            var result = $.parseJSON(oData);
            var userEmail = "hyos810@naver.com"; //TODO: session을 어떻게 가져오지.. 우선 패스

            setSocketNetwork();

            $.each(result, function (nIndex, data) {
                if(data.messengerSendUserId != userEmail) {
                    $("#chatMsgList").append('<div class="answer left"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.messengerSendUserId+'</div><div class="text">'+data.messengerContent+'</div><div class="time">'+data.meesengerSendTime+'</div></div>');
                } else {
                    $("#chatMsgList").append('<div class="answer right"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.messengerSendUserId+'</div><div class="text">'+data.messengerContent+'</div><div class="time">'+data.meesengerSendTime+'</div></div>');
                }
            });
        });
    }

    function setSocketNetwork() {
        //websocket을 지정한 URL
        sock = new SockJS("http://localhost:8080/echo.do");
        //websocket 서버에서 메시지를 보내면 자동으로 실행된다.
        sock.onmessage = onMessage;
        //websocket 과 연결을 끊고 싶을때 실행하는 메소드
        sock.onclose = onClose;
        sock.onopen = onOpen;

        $("#sendBtn").click(function () {
            sendMessage();
        });
    }

    function sendMessage() {
        //websocket으로 메시지를 보내겠다.
        var content = $("#message").val();

        if(!content.trim()) {
            return ;
        }

        var data = {};
        data.content = content;
        data.roomId = sRoomId;
        data.id = loginUserId;

        //try catch.. 문을 어떻게 하는지 모르겠네. 알아보기
       sock.send(JSON.stringify(data));
        
        
        $.ajax({
            method:'POST',
            url:'/insertChatMessage.do',
            data: {
                content: content,
                roomId: sRoomId
            }
        }).success(function () {
            
        }).fail(function () {
            alert('send failed');
        });
    }

    //evt 파라미터는 websocket이 보내준 데이터다.
    function onMessage(evt) {  //변수 안에 function자체를 넣음.

        var data = JSON.parse(evt.data);

        if(data.setId != null) {
            if(userList && userList[data.setId] || data.setId == loginUserId) {  //이미 리스트에 있는 사용자는 아무 액션도 하지 않는다.
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

        var date = new Date();

        if(data.id !== loginUserId) {  //TODO: 세션 id를 보고 판단하도록..
            $("#chatMsgList").append('<div class="answer left"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.id+'</div><div class="text">'+data.msg+'</div><div class="time">' + date + '</div></div>')
        } else {
            $("#chatMsgList").append('<div class="answer right"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar2.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.id+'</div><div class="text">'+data.msg+'</div><div class="time">'+ date +'</div></div>')
        }
        /* sock.close(); */
    }

    function onClose(evt) {
        alert('연결이 끊어졌습니다.');
        $("#data").append("연결 끊김");
    }

    function onOpen() {
        getUserOnline();
    }

    function getUserOnline() {
        var oSendData = {};
        oSendData.setId = loginUserId;
        oSendData.roomId = sRoomId;

        sock.send(JSON.stringify(oSendData));
    }



    return {
        init: _init
    }
})
